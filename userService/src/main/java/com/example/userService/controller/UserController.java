package com.example.userService.controller;

import com.example.userService.core.dtos.requests.user.CheckUserRequest;
import com.example.userService.core.dtos.requests.user.CreateUserRequest;
import com.example.userService.core.dtos.requests.user.UpdateUserRequest;
import com.example.userService.core.dtos.responses.user.CreateUserResponse;
import com.example.userService.core.dtos.responses.user.GetAllUsersResponse;
import com.example.userService.core.dtos.responses.user.GetUserByIdResponse;
import com.example.userService.core.dtos.responses.user.UpdateUserResponse;
import com.example.userService.service.abstracts.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;

    @GetMapping("/{customerId}/exists")
    public boolean doesCustomerExist(@PathVariable Integer customerId) {
        return userService.existsById(customerId);
    }

    @PostMapping("checkUser")
    public boolean checkUser(@RequestBody @Valid CheckUserRequest request){

        return userService.checkUser(request);
    }

    @GetMapping("getAllUsers")
    public List<GetAllUsersResponse> getAllUsers() {

        return userService.getAllUsers();
    }

    @GetMapping("getUser/{id}")
    public Optional<GetUserByIdResponse> getUserById(@PathVariable Integer id){

        return userService.getUser(id);
    }

    @PostMapping("CreateUser")
    public ResponseEntity<CreateUserResponse> CreateUser(@RequestBody @Valid CreateUserRequest request){
        CreateUserResponse response = userService.createUser(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("updateUserInformations/{id}")
    public UpdateUserResponse updateUser(@PathVariable Integer id, @RequestBody @Valid UpdateUserRequest request)
    {
        return userService.updateUser(id, request);
    }

    @DeleteMapping("deleteUser/{id}")
    public void deleteUserById(@PathVariable int id){
        userService.deleteUserById(id);
    }
}
