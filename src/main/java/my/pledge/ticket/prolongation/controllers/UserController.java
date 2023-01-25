package my.pledge.ticket.prolongation.controllers;

import my.pledge.ticket.prolongation.models.User;
import my.pledge.ticket.prolongation.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class UserController {
    @Autowired
    private UsersRepo usersRepo;

    @GetMapping("/")
    public String main(){
        return "main";
    }

    @GetMapping("/auth")
    public String auth(){
        return "auth";
    }

    @PostMapping("/auth")
    public String getAuth(@RequestParam String login,
                        @RequestParam String pass,
                        Model model){
        Iterable<User> users = usersRepo.findAll();
        for (User user : users) {
            if(user.getLogin().equals(login) && user.getPass().equals(pass)){
                model.addAttribute("user",user);
                return "lk";
            }
        }
        return "badRes";
    }

    @GetMapping("/reg")
    public String getRegForm(){
        return "reg";
    }

    @PostMapping("/reg")
    public String addUser(@RequestParam String login,
                        @RequestParam String pass,
                        @RequestParam String name,
                        Model model){
        User user = new User(login,pass,name);
        usersRepo.save(user);
        model.addAttribute("user",user);
        return "lk";
    }
    @GetMapping("/exit")
    public String exit(){
        return "redirect:/";
    }
}
