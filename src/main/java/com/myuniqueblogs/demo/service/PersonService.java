package com.myuniqueblogs.demo.service;

import com.myuniqueblogs.demo.dao.LoginDao;
import com.myuniqueblogs.demo.dao.RegisterUserDAO;
import com.myuniqueblogs.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {
    private final LoginDao loginDao;
    private final RegisterUserDAO registerUserDAO;

    @Autowired
    public PersonService(@Qualifier("mysqlLoginDao") LoginDao loginDao, @Qualifier("mysqlRegisterDao") RegisterUserDAO registerUserDAO) {
        this.loginDao = loginDao;
        this.registerUserDAO = registerUserDAO;
    }

    public String addNewUser(Person user) {
        try {
            boolean isAdded = registerUserDAO.register(user);
            return isAdded ? "User Added Successfully" : "Can't add user.";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public Person loginUser(String username, String password) {
        try {
            Optional<Person> res = loginDao.login(username, password);
            if (res.isPresent()) {
                System.out.println("User Logged Successfully");
                return res.get();
            }
        } catch (Exception e) {
            throw e;
        } finally {
            System.out.println("Can't login user");
        }
        return null;
    }
}
