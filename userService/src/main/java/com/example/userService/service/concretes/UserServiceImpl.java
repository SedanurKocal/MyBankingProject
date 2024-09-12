package com.example.userService.service.concretes;

import com.example.userService.core.dtos.requests.user.CheckUserRequest;
import com.example.userService.core.dtos.requests.user.CreateUserRequest;
import com.example.userService.core.dtos.requests.user.UpdateUserRequest;
import com.example.userService.core.dtos.responses.user.CreateUserResponse;
import com.example.userService.core.dtos.responses.user.GetAllUsersResponse;
import com.example.userService.core.dtos.responses.user.GetUserByIdResponse;
import com.example.userService.core.dtos.responses.user.UpdateUserResponse;
import com.example.userService.core.exeption.types.BusinessException;
import com.example.userService.core.mappers.UserMapper;
import com.example.userService.entities.User;
import com.example.userService.repositories.UserRepository;
import com.example.userService.service.abstracts.SmsService;
import com.example.userService.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public boolean existsById(Integer customerId) {
        return userRepository.existsById(customerId);
    }

    @Autowired
    public UserServiceImpl(UserRepository userRepository, SmsService smsService) {
        this.userRepository = userRepository;
    }
    @Override
    public boolean checkUser(CheckUserRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            if (request.getPassword().equals(user.getPassword())) {
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public List<GetAllUsersResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return UserMapper.INSTANCE.usersToAllUserResponses(users);
    }

    @Override
    public Optional<GetUserByIdResponse> getUser(int id)
    {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new BusinessException(id + " numaralı id'ye ait bir kullanıcı bulunamadı.");
        }
        return userOptional.map(UserMapper.INSTANCE::mapToGetByIdUserResponse);
    }

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        doesTheEmailAlreadyExists(request.getEmail());
        User user = UserMapper.INSTANCE.userFromCreateRequest(request);
        User savedUser = userRepository.save(user);
        CreateUserResponse response = new CreateUserResponse(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
        return response;
    }

    @Override
    public void deleteUserById(int id) {
        isIdExisted(id);
        userRepository.deleteById(id);
    }


    @Override
    public UpdateUserResponse updateUser(int id, UpdateUserRequest request) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new BusinessException(id + " numaralı id'ye ait bir kullanıcı bulunamadı.");
        }
        User existingUser = userOptional.get();
        if (!request.getEmail().equals(existingUser.getEmail())) {
            doesTheEmailAlreadyExists(request.getEmail());
        }
        User user= UserMapper.INSTANCE.updateUserFromRequest(request,existingUser);
        User savedCategory=userRepository.save(user);

        UpdateUserResponse response= new UpdateUserResponse(savedCategory.getId(), savedCategory.getName(), savedCategory.getEmail());
        return response;
    }

    private  void isIdExisted(int id){
        if (!userRepository.existsById(id)) {
            throw new BusinessException(id + "numaralı id'ye ait bir kullanıcı bulunamadı.");
        }
    }

    private void doesTheEmailAlreadyExists(String email){
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            throw new BusinessException(("Bu email'e ait bir hesap bulunmakta."));
        }
    }
    private String generateSecurityCode() {
        return String.valueOf((int) ((Math.random() * 8999) + 1000)); // 4 basamaklı rastgele sayı üretir
    }
}
