package com.easybit.accounts.service;

import com.easybit.accounts.Dto.CustomerDTO;

public interface IAccountService {
    /**
     * @param customerDto -customerDto object
     */

    void createAccount(CustomerDTO customerDto);
    /**
     * @param mobileNumber-enter mobile number
     * @return get customer details
     */
    CustomerDTO fetchDetails(String mobileNumber);
    /**
     * @param customerDTO-customerDto object
     * @return boolean indicating if the update of Account details is successful or not
     */
    boolean updateAccount(CustomerDTO customerDTO);


    /**
     *
     * @param mobileNumber-Input Mobile Number
     * @return boolean indicating if the delete account details success or not
     */
    boolean deleteAccount(String mobileNumber);
}
