package com.ptit.managerbank.service.imp;

import com.ptit.managerbank.common.*;
import com.ptit.managerbank.dto.AccountBankDTO;
import com.ptit.managerbank.dto.BillDepositDTO;
import com.ptit.managerbank.dto.CustomerDTO;
import com.ptit.managerbank.dto.request.AccountBankRequestDTO;
import com.ptit.managerbank.dto.request.SavingAccountRequestDTO;
import com.ptit.managerbank.dto.request.TransferRequest;
import com.ptit.managerbank.model.*;
import com.ptit.managerbank.repository.*;
import com.ptit.managerbank.service.AccountBankService;
import com.ptit.managerbank.service.mapper.AccountBankMapper;
import com.ptit.managerbank.service.mapper.AccountBankRequestMapper;
import com.ptit.managerbank.service.mapper.BillDepositMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Component
public class AccountBankServiceImpl extends BaseComponent implements AccountBankService {
    @Autowired
    AccountBankMapper accountBankMapper;
    @Autowired
    AccountBankRepository accountBankRepository;
    @Autowired
    AccountBankRequestMapper accountBankRequestMapper;

    @Override
    public AccountBankDTO findAccountBankById(Integer id) {
        Optional<AccountBank> optionalAccountBank = accountBankRepository.findById(id);
        if (optionalAccountBank.isPresent()) {
            return accountBankMapper.toDto(optionalAccountBank.get());
        }
        return null;
    }

    @Override
    public AccountBankDTO findAccountBankByCode(String code) {
        Optional<AccountBank> optionalAccountBank = accountBankRepository.findFirstByCode(code);
        if (optionalAccountBank.isPresent()) {
            accountBankMapper.toDto(optionalAccountBank.get());
        } else {
            return null;
        }
        return null;
    }

    @Override
    public AccountBankDTO saveAccountBank(AccountBankDTO accountBankDTO) {
        String code = Constants.BANK_CODE + StringUtils.randomNumberString();
        Optional<Customer> customer=customerRepository.findById(accountBankDTO.getCustomer().getId());
        AccountBank accountBank = accountBankMapper.toEntity(accountBankDTO);
        if(customer.isPresent()){
        accountBank.setCustomer(customer.get());
        } else {
            accountBank.setCustomer(null);
        }
        accountBank = accountBankRepository.save(accountBank);
        code += (accountBank.getId());
        accountBank.setCode(code);
        accountBank = accountBankRepository.save(accountBank);
        return accountBankMapper.toDto(accountBank);
    }

    @Override
    public AccountBankDTO updateAccountBank(AccountBankDTO accountBankDTO) {
        AccountBank accountBank = accountBankMapper.toEntity(accountBankDTO);
        accountBank = accountBankRepository.save(accountBank);
        return accountBankMapper.toDto(accountBank);
    }

    @Override
    public void deleteAccountBank(Integer id) {
        accountBankRepository.deleteById(id);
    }

    @Override
    public Page<AccountBankDTO> getAccountBankByCode(String code,String type, Pageable pageable) {
        if (!Objects.isNull(code)) {
            code = code.trim().replace("%", "!%").replace("_", "!_");
        } else {
            code="%";
        }

        Page<AccountBank> accountBankPageable = accountBankRepository.findByCodeAndType(code,type, pageable);
        if (accountBankPageable != null && !accountBankPageable.isEmpty()) {
            accountBankPageable.getContent();
        }
        return accountBankPageable.map(accountBankMapper::toDto);


    }

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public ResponseData validateAccountBank(AccountBankRequestDTO accountBankDTO) {
        ResponseData responseData = new ResponseData();
        CustomerDTO customerDTO = accountBankDTO.getCustomerDTO();
        if (!Objects.isNull(customerDTO)) {
            Optional<Customer> optionalCustomer = customerRepository.findById(customerDTO.getId());
            if (!optionalCustomer.isPresent()) {
                responseData.setMessage(getText("customer.existed"));
            } else {
                Set<AccountBank> accountBanks = accountBankRepository.findByCustomerAndType(optionalCustomer.get(), TypeAccountBank.CREDIT_ACCOUNT_BANK.toString());
                if (accountBanks.size() >= Constants.MAX_ACCOUNT_BANK) {
                    responseData.setMessage(getText("account.limit"));
                }
            }

        }
        if (!Objects.isNull(responseData.getMessage())) {
            responseData.setErrorCode(Constants.ERROR_CODE.VALIDATE_FAIL);
            return responseData;
        }
        return null;

    }

    @Override
    public ResponseData validateSavingAccountBank(SavingAccountRequestDTO accountBankDTO) {
        ResponseData responseData = new ResponseData();
        CustomerDTO customerDTO = accountBankDTO.getCustomerDTO();
        if (!Objects.isNull(customerDTO)) {
            Optional<Customer> optionalCustomer = customerRepository.findById(customerDTO.getId());
            if (!optionalCustomer.isPresent()) {
                responseData.setMessage(getText("customer.existed"));
            } else {
                Set<AccountBank> accountBanks = accountBankRepository.findByCustomerAndType(optionalCustomer.get(), TypeAccountBank.SAVE_ACCOUNT_BANK.toString());
                if (accountBanks.size() >= Constants.MAX_SAVING_ACCOUNT_BANK) {
                    responseData.setMessage(getText("account.limit"));
                }
            }

        }
        if (!Objects.isNull(responseData.getMessage())) {
            responseData.setErrorCode(Constants.ERROR_CODE.VALIDATE_FAIL);
            return responseData;
        }
        return null;
    }

    @Override
    public ResponseData validateUpdateAccountBank(AccountBankDTO accountBankDTO) {
        ResponseData responseData = new ResponseData();
        CustomerDTO customerDTO = accountBankDTO.getCustomer();
        if (!Objects.isNull(customerDTO)) {
            Optional<Customer> optionalCustomer = customerRepository.findById(customerDTO.getId());
            if (!optionalCustomer.isPresent()) {
                responseData.setMessage(getText("customer.existed"));
            }

        }
        if (!Objects.isNull(responseData.getMessage())) {
            responseData.setErrorCode(Constants.ERROR_CODE.VALIDATE_FAIL);
            return responseData;
        }
        return null;
    }

    @Autowired
    BillDepositRepository billDepositRepository;
    @Autowired
    BillDepositMapper billDepositMapper;

    @Transactional
    @Override
    public BillDepositDTO deposit(String code, Double amount) {
        Optional<AccountBank> optionalAccountBank = accountBankRepository.findFirstByCode(code);
        if (!optionalAccountBank.isPresent()) {
            return null;
        }
        AccountBank accountBank = optionalAccountBank.get();
        Double balance = accountBank.getBalance();
        accountBank.setBalance(balance + amount);
        accountBankRepository.save(accountBank);
        BillDeposit billDeposit = new BillDeposit();
        billDeposit.setAccountBankSend(accountBank);
        billDeposit.setAmount(amount);
        billDeposit.setCode(StringUtils.generateCodeFree());
        billDepositRepository.save(billDeposit);
        return billDepositMapper.toDto(billDeposit);
    }

    @Autowired
    BillWithDrawRepository billWithDrawRepository;

    @Transactional
    @Override
    public ResponseData withdraw(String code, Double amount) {
        Optional<AccountBank> optionalAccountBank = accountBankRepository.findFirstByCode(code);
        if (!optionalAccountBank.isPresent()) {
            return ResponseData.ofFail(getText("account.withdraw.limit"));
        }
        AccountBank accountBank = optionalAccountBank.get();
        double balance = accountBank.getBalance();
        if (TypeAccountBank.CREDIT_ACCOUNT_BANK.toString().equals(accountBank.getType())) {
            if (accountBank.getCreditLimit() + balance - amount > 0) {
                accountBank.setBalance(balance - amount);
            } else {
                return ResponseData.ofFail(getText("account.withdraw.limit"));
            }
        } else {
            if (balance - amount > accountBank.getMinBalance()) {
                accountBank.setBalance(balance - amount);
            } else {
                return ResponseData.ofFail(getText("account.withdraw.limit"));
            }

        }
        BillWithdraw billWithdraw = new BillWithdraw();
        billWithdraw.setAccountBankReceive(accountBank);
        billWithdraw.setAmount(amount);
        billWithdraw.setCode(StringUtils.generateCodeFree());
        billWithdraw = billWithDrawRepository.save(billWithdraw);
        return ResponseData.ofSuccess("account.withdraw.success", billWithdraw);

    }
    @Autowired
    PaymentRepository paymentRepository;

    @Transactional
    @Override
    public ResponseData transfer(TransferRequest transferRequest) {
        Double amount=transferRequest.getAmount();
        Optional<AccountBank> optionalCodeReceive = accountBankRepository.findFirstByCode(transferRequest.getAccountCodeReceive());
        if (!optionalCodeReceive.isPresent()) {
            return ResponseData.ofFail(getText("account.bank.receive.existed"));
        }
        AccountBank accountBankReceive = optionalCodeReceive.get();

        Optional<AccountBank> optionalCodeSend = accountBankRepository.findFirstByCode(transferRequest.getAccountCodeSend());
        if (!optionalCodeSend.isPresent()) {
            return ResponseData.ofFail(getText("account.bank.send.existed"));
        }
        AccountBank accountBankSend = optionalCodeSend.get();
        Payment payment=new Payment();
        if (TypeAccountBank.SAVE_ACCOUNT_BANK.equals(accountBankSend.getType())) {
            double balanceSend = accountBankSend.getBalance();
            if (balanceSend - transferRequest.getAmount() > accountBankSend.getMinBalance()) {
                accountBankSend.setBalance(balanceSend - amount);
                accountBankReceive.setBalance(accountBankReceive.getBalance() + amount);

                payment.setAccountBankReceive(accountBankReceive);
                payment.setAccountBankSend(accountBankSend);
                payment.setAmount(amount);
                payment.setCode(StringUtils.generateCodeFree());
                payment=paymentRepository.save(payment);
            } else {
                return ResponseData.ofFail(getText("account.withdraw.limit"));
            }
        } else {
            return ResponseData.ofFail(getText("account.bank.send.invalid"));
        }

        return ResponseData.ofSuccess(getText("account.bank.transfer.success"),payment);
    }


}

