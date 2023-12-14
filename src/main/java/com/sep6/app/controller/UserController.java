package com.sep6.app.controller;

import com.sep6.app.controller.request.EditUserInfoRequest;
import com.sep6.app.controller.request.SimpleResponse;
import com.sep6.app.controller.request.UserInfoResponse;
import com.sep6.app.model.User;
import com.sep6.app.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/info")
    public ResponseEntity<UserInfoResponse> getUserInfo(@RequestParam int id) {
        return this.userRepository.findById(id)
                .map(user -> ResponseEntity.ok(new UserInfoResponse(user.getEmail(), "", user.getUsername())))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/info")
    public ResponseEntity<SimpleResponse> editUser(@RequestBody EditUserInfoRequest request) {
        try {
            this.userRepository.save(new User(request.id(), request.email(), request.username()));
            return ResponseEntity.ok(new SimpleResponse("Changes have been saved!"));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(new SimpleResponse(e.getMessage()));
        }
    }
}
