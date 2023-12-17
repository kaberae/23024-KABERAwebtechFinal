package com.missworld.missweb.Controller;

import com.missworld.missweb.Models.Users;
import com.missworld.missweb.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/adminDashboard/users")
    public String listUsers(@RequestParam("missid") long missid,Model model){
        List<Users> users = userService.findAllUsers();
        model.addAttribute("missid",missid);
        model.addAttribute("users",users);
        return "AdminUserList";
    }

    @GetMapping("/loginUser")
    public String login(Model model){
        return "loginUser";
    }

    @PostMapping("/loginUser")
    public String confirmLogin(@RequestParam("email") String email, @RequestParam("password") String password){
        String results = userService.logConfirmation(email,password);

        return results;
    }

    @GetMapping("/logout")
    public String logout(){
        return "redirect:/loginUser";
    }
    @GetMapping("/signup")
    public String signup(Model model){
        Users user = new Users();
        model.addAttribute("user",user);
        return "U_Signup";
    }

    @PostMapping("/signup")
    public String signupSave(Users user){
        userService.saveUser(user);
        return "redirect:/loginUser";
    }
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") Users user,@RequestParam("missid") long missid){
        userService.saveUser(user);
        return "redirect:/loginUser?missid="+missid;
    }
    @GetMapping("/")
    public String homePage(Model model){
        Users users = new Users();
        model.addAttribute("users",users);
        return "Index";
    }


    @GetMapping("/adminDashboard/users/InsertUser")
    public String insertUser(@RequestParam("missid") long missid,Model model){
        Users user = new Users();
        model.addAttribute("user",user);
        model.addAttribute("missid",missid);
        return "AddUser";
    }

    @PostMapping("/adminDashboard/users/InsertUser")
    public String insertUser(@RequestParam("missid") long missid,@ModelAttribute("user") Users user){
        userService.saveUser(user);
        return "redirect:/adminDashboard/users?missid="+missid;
    }

    @GetMapping("/adminDashboard/users/{userId}/edit")
    public String editUser(@RequestParam("missid") long missid,@PathVariable("userId") long userId, Model model){
        Users user = userService.findById(userId);
        model.addAttribute("user",user);
        model.addAttribute("missid",missid);
        return "EditUser";
    }


    @PostMapping("/adminDashboard/users/{userId}/edit")
    public String updateUser(@RequestParam("missid") long missid,@PathVariable("userId") long userId, @ModelAttribute("user") Users user){
        user.setUserId(userId);
        userService.updateUser(user);
        return "redirect:/adminDashboard/users?missid="+missid;
    }

    @GetMapping("/adminDashboard/users/{userId}/delete")
    public String deleteUser(@RequestParam("missid") long missid,@PathVariable("userId") long userId){
        userService.deleteById(userId);
        return "redirect:/adminDashboard/users?missid="+missid;
    }

    @GetMapping("/dashBoard/profile")
    public String dashBoardProfile(@RequestParam("missid") long missid, Model model){
        Users user = userService.findById(missid);
        model.addAttribute("missid",missid);
        model.addAttribute("user",user);
        return "U_profile";
    }

    @GetMapping("/adminDashboard/profile")
    public String adminDashboardProfile(@RequestParam("missid") long missid, Model model){
        Users user = userService.findById(missid);
        model.addAttribute("missid",missid);
        model.addAttribute("user",user);
        return "Profile";
    }
}
