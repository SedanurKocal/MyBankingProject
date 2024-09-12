package com.example.userService.core.mappers;

import com.example.userService.core.dtos.requests.user.CreateUserRequest;
import com.example.userService.core.dtos.requests.user.UpdateUserRequest;
import com.example.userService.core.dtos.responses.user.GetAllUsersResponse;
import com.example.userService.core.dtos.responses.user.GetUserByIdResponse;
import com.example.userService.core.dtos.responses.user.UpdateUserResponse;
import com.example.userService.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userFromCreateRequest(CreateUserRequest request);

    List<GetAllUsersResponse> usersToAllUserResponses(List<User> users);

    GetUserByIdResponse mapToGetByIdUserResponse(User user);

    UpdateUserResponse userToUpdateResponse(User user);
    User updateUserFromRequest(UpdateUserRequest request,
                               @MappingTarget User category);
}
