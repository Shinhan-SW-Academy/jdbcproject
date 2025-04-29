package com.shinhan.service;

import com.shinhan.dao.UserDAO;
import com.shinhan.dto.UserDTO;

public class UserService {
    UserDAO userDAO = new UserDAO();

    public UserDTO insertUser(UserDTO user) {
        return userDAO.insertUser(user);
    }

    public void updateById(UserDTO user) {
        userDAO.updateById(user);
    }

    public void deleteById(String userId) {
        userDAO.deleteById(userId);
    }

    public UserDTO login(String userId, String userPw) {
        return userDAO.login(userId, userPw);
    }

    public UserDTO selectById(String userId) {
        return userDAO.selectById(userId);
    }
}
