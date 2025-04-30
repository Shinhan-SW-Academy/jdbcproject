package com.shinhan.service;

import com.shinhan.dao.AccountDAO;
import com.shinhan.dto.AccountDTO;
import com.shinhan.dto.OrderDTO;

import java.util.List;

public class AccountService {
    AccountDAO accountDAO = new AccountDAO();

    public AccountDTO.UserAccountDTO selectuAccount(String userId) {
        return accountDAO.selectuAcc(userId);
    }

    public AccountDTO.BusinessAccountDTO selectbAccount(String businessId) {
        return accountDAO.selectbAcc(businessId);
    }

    public void deposit(String userId, Integer amount) {
        accountDAO.deposit(userId, amount);
    }
}
