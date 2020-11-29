package com.ptit.managerbank.service.imp;

import com.ptit.managerbank.common.BaseComponent;
import com.ptit.managerbank.common.Constants;
import com.ptit.managerbank.common.ResponseData;
import com.ptit.managerbank.dto.StaffDTO;
import com.ptit.managerbank.model.Position;
import com.ptit.managerbank.model.Staff;
import com.ptit.managerbank.repository.PositionRepository;
import com.ptit.managerbank.repository.StaffRepository;
import com.ptit.managerbank.service.StaffService;
import com.ptit.managerbank.service.mapper.StaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Objects;
import java.util.Optional;
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

    @Override
    public StaffDTO saveStaff(StaffDTO staffDTO) {
        Staff staff=staffMapper.toEntity(staffDTO);
        staff=staffRepository.save(staff);
        return  staffMapper.toDto(staff);
    }

    @Override
    public StaffDTO updateStaff(StaffDTO staffDTO) {
        Staff staff=staffMapper.toEntity(staffDTO);
        staff=staffRepository.save(staff);
        return  staffMapper.toDto(staff);
    }

    @Override
    public void deleteStaff(Integer id) {
        staffRepository.deleteById(id);
    }

    @Override
    public Page<StaffDTO> getStaffByName(String name, Pageable pageable) {
        if (!Objects.isNull(name)) {
            name = name.trim().replace("%", "!%").replace("_", "!_");
        }
        Page<Staff> staffPageable=staffRepository.findByFullName(name,pageable);
        if(staffPageable != null && !staffPageable.isEmpty()){
            staffPageable.getContent();
        }
        return  staffPageable.map(staffMapper::toDto);


    }
    @Autowired
    PositionRepository positionRepository;
    @Override
    public ResponseData validateStaff(StaffDTO staffDTO) {
        ResponseData responseData=new ResponseData();
        if(!Objects.isNull(staffDTO.getPositions())){
            staffDTO.getPositions().forEach(positionDTO -> {
                Optional<Position> optionalPosition =positionRepository.findById(positionDTO.getId());
                if(!optionalPosition.isPresent()){
                    responseData.setMessage(getText("position.existed"));
                    return;
                }
            });
        }
        if(!Objects.isNull(responseData.getMessage())){
            responseData.setErrorCode(Constants.ERROR_CODE.VALIDATE_FAIL);
            return  responseData;
        }
        return null;

    }
}
