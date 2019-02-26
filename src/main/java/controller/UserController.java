package controller;

import model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.user.UserService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Value("${user.existent}")
    private String  existent;
    @Value("${user.nonexistent}")
    private String nonexistent;

    @RequestMapping("/login")
    public String login() {
        return "user/login";
    }

    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:login";
    }

    @RequestMapping(value = "/check")
    @ResponseBody
    public String userCheck(User user, HttpSession session) {
        int userCheck = userService.userCheck(user);
        if (userCheck == Integer.parseInt(existent)) {
           session.setAttribute("user",userService.userInfo(user.getUserName()));
            return existent;
        }
        return nonexistent;
    }

    @RequestMapping("/register")
    public String register() {
        return "user/register";
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public String registerUser(User user) {
        System.out.println(user);
        return "OK";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
