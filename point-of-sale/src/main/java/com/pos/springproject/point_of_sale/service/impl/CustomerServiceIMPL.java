package com.pos.springproject.point_of_sale.service.impl;

import com.pos.springproject.point_of_sale.dto.request.CustomerSaveRequestDTO;
import com.pos.springproject.point_of_sale.dto.request.CustomerUpdateRequestDTO;
import com.pos.springproject.point_of_sale.entity.Customer;
import com.pos.springproject.point_of_sale.repository.CustomerRepo;
import com.pos.springproject.point_of_sale.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceIMPL implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public String addCustomer(CustomerSaveRequestDTO customerSaveRequestDTO) {
        Customer customer = new Customer(
                customerSaveRequestDTO.getCustomerName(),
                customerSaveRequestDTO.getCustomerAddress(),
                customerSaveRequestDTO.getCustomerSalary(),
                customerSaveRequestDTO.getContactNumbers(),
                customerSaveRequestDTO.getNic(),
                false
        );

        if(!customerRepo.existsById(customer.getCustomerId())) {
            customerRepo.save(customer); //entity name = customer
            return customer.getCustomerName()+ " saved";
        }else {
            System.out.println("Customer id already exists");
            return "customer id already exists";
        }
    }

    @Override
    public String updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO) {
        if(customerRepo.existsById(customerUpdateRequestDTO.getCustomerId())){
            Customer customer = customerRepo.getById(customerUpdateRequestDTO.getCustomerId());
            customer.setCustomerName(customerUpdateRequestDTO.getCustomerName());
            customer.setCustomerAddress(customerUpdateRequestDTO.getCustomerAddress());
            customer.setCustomerSalary(customerUpdateRequestDTO.getCustomerSalary());
            customer.setContactNumbers(customerUpdateRequestDTO.getContactNumbers());
            customer.setNic(customerUpdateRequestDTO.getNic());
            customer.setActiveState(customerUpdateRequestDTO.isActiveState());

            customerRepo.save(customer);
        } else {
            System.out.println("This customer not in database");
        }
        return null;
    }
}
