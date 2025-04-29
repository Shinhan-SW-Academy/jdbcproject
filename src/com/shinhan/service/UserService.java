package com.shinhan.service;

import com.shinhan.dao.UserDAO;
import com.shinhan.dto.UserDTO;

public class UserService {
    UserDAO userDAO = new UserDAO();

    public int insertUser(UserDTO user) {
        return userDAO.insertUser(user);
    }

    public int updateById(UserDTO user) {
        return userDAO.updateById(user);
    }

    public int deleteById(String userId) {
        return userDAO.deleteById(userId);
    }
}
