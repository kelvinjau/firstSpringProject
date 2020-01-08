package com.kueq.controller;

import com.kueq.model.User;
import com.kueq.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/user") // This means URL's start with /demo (after Application path)
public class MainController {
    //@Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data

    @Autowired//property based injection
    private UserRepository userRepository;

//    @Autowired
//    UserService userservice;

//    @Autowired //setter based injection
//    public void setNotificationService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Autowired //setter based injection
//    public MainController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }



   // @GetMapping
//    @DeleteMapping
//    @PutMapping
//    @RequestMapping
//    @PatchMapping
    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String email) { //taking request param
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return n.getName()+"Saved";
    }


    @PutMapping
    public User updateUser(@RequestBody User user){
        return userRepository.save(user);
    }

//    @GetMapping
//    public Iterable<User> getUsers(){
//        return userRepository.findAll();
//    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }


    @GetMapping(path="/one")
    public @ResponseBody User user (@RequestParam String name){ //request body
        // This returns a JSON or XML with the users
        User namee = userRepository.findByName(name);
        return namee;
    }


//    @Value("${pageController.msg}") //gets value from config file and parse it to a variable
//    private String pageControllerMgs;


//    @ExceptionHandler(Exception.class)
//    public  String handleException(HttpServletRequest req, Exception exc , Model model){
//        model.addAttribute("Error Message", exc.getMessage());
//        return  "Error";
//    }




}