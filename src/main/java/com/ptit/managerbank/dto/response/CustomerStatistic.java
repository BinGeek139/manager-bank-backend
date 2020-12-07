package com.ptit.managerbank.dto.response;

import com.ptit.managerbank.dto.CustomerDTO;
import com.ptit.managerbank.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerStatistic {
   private CustomerDTO customer;
   private  Double sumAmount;
}
