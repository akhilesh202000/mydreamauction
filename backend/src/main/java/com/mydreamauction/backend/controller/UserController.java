package com.mydreamauction.backend.controller;

import com.mydreamauction.backend.dao.UserDao;
import com.mydreamauction.backend.model.DAOUser;
import com.mydreamauction.backend.model.UserDTO;
import com.mydreamauction.backend.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private final UserDao userDao;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/users")
    public @ResponseBody Iterable<DAOUser> getUsers() {
        return userDao.findAll();
    }

    @GetMapping("/users/{username}")
    public @ResponseBody DAOUser getUser(@PathVariable String username) {
        return userDao.findByUsername(username);
    }

    @PostMapping("/users")
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(user));
    }

    @PatchMapping("/users/{username}")
    public ResponseEntity<?> updateUser(@PathVariable String username, @RequestBody UserDTO user) throws Exception {
        DAOUser x = userDao.findByUsername(username);
        x.setUsername(user.getUsername());
        x.setLocked(user.getLocked());
        x.setRole(user.getRole());
        x.setEmail(user.getEmail());
        x.setPassword(bcryptEncoder.encode(user.getPassword()));
        x.setRoleFunction(user.getRoleFunction());
        return ResponseEntity.ok(userDao.save(x));
    }

    @PatchMapping("/users/admin/{username}")
    public ResponseEntity<?> updateUserAsAdmin(@PathVariable String username, @RequestBody UserDTO user) throws Exception {
        DAOUser x = userDao.findByUsername(username);
        System.out.println(x.getUsername());
        x.setUsername(user.getUsername());
        x.setLocked(user.getLocked());
        x.setRole(user.getRole());
        x.setEmail(user.getEmail());
        x.setRoleFunction(user.getRoleFunction());
        return ResponseEntity.ok(userDao.save(x));
    }

    @DeleteMapping("/users/{username}")
    public ResponseEntity<HttpStatus> deletUser(@PathVariable String username) throws Exception {
        try {
            userDao.delete(userDao.findByUsername(username));
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}