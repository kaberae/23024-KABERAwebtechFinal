package com.missworld.missweb.Services.Implementation;

import com.missworld.missweb.Models.Users;
import com.missworld.missweb.Repositories.UserRepository;
import com.missworld.missweb.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Users> findAllUsers() {
       return (List<Users> )userRepository.findAll();
    }

    @Override
    public Users saveUser(Users user) {
        return userRepository.save(user);
    }

    @Override
    public Users findById(long userId) {
        Users user = userRepository.findById(userId).get();
        return user;
    }

    @Override
    public void updateUser(Users user ) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public String logConfirmation(String email, String password) {
        try {
            Users user = userRepository.findByEmailAndPassword(email, password);
            if (user != null && user.getRole().equals("ADMIN")) {
                return "redirect:/adminDashboard?missid="+user.getUserId();
            } else {
                return "redirect:/dashBoard?missid="+user.getUserId();
            }
        } catch (Exception ex) {
            return "redirect:/loginUser?error=Invalid credentials";
        }
    }
}
