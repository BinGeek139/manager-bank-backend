package com.ptit.managerbank.service.imp;

import com.ptit.managerbank.common.BaseComponent;
import com.ptit.managerbank.common.Constants;
import com.ptit.managerbank.common.ResponseData;
import com.ptit.managerbank.common.TypeAccountBank;
import com.ptit.managerbank.dto.PositionDTO;
import com.ptit.managerbank.dto.StaffDTO;
import com.ptit.managerbank.model.AccountBank;
import com.ptit.managerbank.model.BillDeposit;
import com.ptit.managerbank.model.Position;
import com.ptit.managerbank.model.Staff;
import com.ptit.managerbank.repository.AccountBankRepository;
import com.ptit.managerbank.repository.BillDepositRepository;
import com.ptit.managerbank.repository.PositionRepository;
import com.ptit.managerbank.repository.StaffRepository;
import com.ptit.managerbank.service.StaffService;
import com.ptit.managerbank.service.mapper.PositionMapper;
import com.ptit.managerbank.service.mapper.StaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

@Service
public class StaffServiceImpl extends BaseComponent implements StaffService  {
    @Autowired
    StaffMapper staffMapper;
    @Autowired
    StaffRepository staffRepository;

    @Override
    public StaffDTO findStaffById(Integer id) {
        Optional<Staff> optionalStaff=staffRepository.findById(id);
        if(optionalStaff.isPresent()){
            return staffMapper.toDto(optionalStaff.get());
        }
        return null;
    }
    @Autowired
    PasswordEncoder bCryptPasswordEncoder;
    @Override
    public StaffDTO saveStaff(StaffDTO staffDTO) {
        String pass=bCryptPasswordEncoder.encode(staffDTO.getPassword());
        staffDTO.setPassword(pass);
        Staff staff=staffMapper.toEntity(staffDTO);

        staff=staffRepository.save(staff);
        return  staffMapper.toDto(staff);
    }

    @Override
    public StaffDTO updateStaff(StaffDTO staffDTO) {
        String pass=bCryptPasswordEncoder.encode(staffDTO.getPassword());
        staffDTO.setPassword(pass);
        Staff staff=staffMapper.toEntity(staffDTO);
        staff=staffRepository.save(staff);
        return  staffMapper.toDto(staff);
    }

    @Override
    public boolean deleteStaff(Integer id) {

        try {
            staffRepository.deleteById(id);
            return true;

        }catch (EmptyResultDataAccessException e){
                return false;
        }
    }

    @Override
    public Page<StaffDTO> getStaffByName(String name, Pageable pageable) {
        if (!Objects.isNull(name)) {
            name = name.trim().replace("%", "!%").replace("_", "!_");
        } else {
            name="%";
        }
        Page<Staff> staffPageable=staffRepository.findByFullName(name,pageable);
        if(staffPageable != null && !staffPageable.isEmpty()){
            staffPageable.getContent();
        }
        return  staffPageable.map(staffMapper::toDto);


    }
    @Autowired
    PositionRepository positionRepository;
    @Autowired
    PositionMapper positionMapper;
    @Override
    public ResponseData validateStaff(StaffDTO staffDTO) {
        ResponseData responseData=new ResponseData();
        if(!Objects.isNull(staffDTO.getPositions())){
            Set<PositionDTO> positions=new HashSet<>();
            staffDTO.getPositions().forEach(positionDTO -> {
                Optional<Position> optionalPosition =positionRepository.findById(positionDTO.getId());
                if(!optionalPosition.isPresent()){
                    responseData.setMessage(getText("position.existed"));
                    return;
                } else {
                    positions.add(positionMapper.toDto(optionalPosition.get()));
                }
            });
            staffDTO.setPositions(positions);
        }
        if(!Objects.isNull(responseData.getMessage())){
            responseData.setErrorCode(Constants.ERROR_CODE.VALIDATE_FAIL);
            return  responseData;
        }
        return null;

    }
    @Transactional
    @Override
    public boolean checkPositionByUsername(String username, String namePosition) {
        Staff staff=staffRepository.findFirstByUserName(username);
        Set<Position> positions=staff.getPositions();
        if( Objects.isNull(positions) || positions.size() ==0 ){
            return false;
        } else {
            for (Position position : positions) {
                if(position.getName().equals(namePosition)){
                    return true;
                }
            }
        }
        return  false;
    }

    @Autowired
    AccountBankRepository accountBankRepository;
    @Autowired
    BillDepositRepository billDepositRepository;
    @Override
    public Double payrollBusinessman(Integer id,Date start,Date end) {
       Optional<Staff> optionalStaff=staffRepository.findById(id);
       Staff staff=optionalStaff.get();
        List<AccountBank> accountBankCredit=accountBankRepository.findAccountCreatedby(staff.getUserName(),
                                                                                        TypeAccountBank.CREDIT_ACCOUNT_BANK.toString(),
                                                                                        start,end);
        double payroll=0;
        payroll+=(accountBankCredit.size()*Constants.PROFIT_EACH_ACCOUNT_CREDIT);
        List<AccountBank> accountBankSaving=accountBankRepository.findAccountCreatedby(staff.getUserName(),
                TypeAccountBank.SAVE_ACCOUNT_BANK.toString(),
                start,end);
        for (AccountBank accountBank:accountBankSaving
             ) {
            List<BillDeposit> billDeposits=billDepositRepository.findByAccountBankSend(accountBank.getId());
            if(billDeposits.size() > 0){
                payroll+=(billDeposits.get(0).getAmount()*Constants.PROFIT_EACH_ACCOUNT_SAVING);
            }

        }

        return payroll;
    }


}
