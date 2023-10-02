package com.example.crud_fstack.controller;

import com.example.crud_fstack.exception.UserNotFoundException;
import com.example.crud_fstack.model.User;
import com.example.crud_fstack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @PostMapping(path="/user")
    User newUser(@RequestBody User newuser){
        return userRepository.save(newuser);
    }
    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    User getUser(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

//    @GetMapping("user/{name}")
//    List<User> getName(@PathVariable String name){
//        return userRepository.findByName(name);
//    }

    @PutMapping("user/{id}")
    User updateUser(@RequestBody User newuser, @PathVariable Long id){
        return userRepository.findById(id)
                .map(user -> {
                    user.setEmail(newuser.getEmail());
                    user.setName(newuser.getName());
                    user.setUsername(newuser.getUsername());
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User with id"+id+"deleted";
    }
}
