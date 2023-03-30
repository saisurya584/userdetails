package com.Userdetails.Controller;

import com.Userdetails.Entity.User;
import com.Userdetails.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class UserController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    UserController(UserRepository userRepository,PasswordEncoder passwordEncoder)
    {
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @PostMapping("/reg")
    public ResponseEntity<String>saveRecord(@RequestBody User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>("record is saved", HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping("/{username}")
    public ResponseEntity<String>delete(@PathVariable("username") String username)
    {
        Optional<User> byUserName = userRepository.findByUserName(username);
        List<User> collect = byUserName.stream().filter(x -> x.getUserName().equals(username)).collect(Collectors.toList());
        User x=collect.get(0);
        userRepository.deleteById(x.getId());
        return new ResponseEntity<>("deleted",HttpStatus.NO_CONTENT);
    }

}
