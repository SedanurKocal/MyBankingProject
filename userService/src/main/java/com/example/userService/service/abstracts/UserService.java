package com.example.userService.service.abstracts;

import com.example.userService.core.dtos.requests.user.CheckUserRequest;
import com.example.userService.core.dtos.requests.user.CreateUserRequest;
import com.example.userService.core.dtos.requests.user.UpdateUserRequest;
import com.example.userService.core.dtos.responses.user.CreateUserResponse;
import com.example.userService.core.dtos.responses.user.GetAllUsersResponse;
import com.example.userService.core.dtos.responses.user.GetUserByIdResponse;
import com.example.userService.core.dtos.responses.user.UpdateUserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean existsById(Integer customerId);
    boolean checkUser(CheckUserRequest request);
    List<GetAllUsersResponse> getAllUsers();

    Optional<GetUserByIdResponse> getUser(int id);

    CreateUserResponse createUser(CreateUserRequest request);

    void deleteUserById(int id);

    UpdateUserResponse updateUser(int id, UpdateUserRequest request);
}
