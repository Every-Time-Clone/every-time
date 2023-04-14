package com.example.everytime.service;

import com.example.everytime.domain.users.User;
import com.example.everytime.domain.users.UserRepository;
import com.example.everytime.dto.user.UserCreateRequestDto;
import com.example.everytime.dto.user.UserResponseDto;
import com.example.everytime.dto.user.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<UserResponseDto> getUserList() {
        List<User> userList = userRepository.findAll();
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        for (User user : userList) {
            userResponseDtoList.add(new UserResponseDto(user));
        }
        return userResponseDtoList;
    }

    @Override
    public UserResponseDto getUserData(UUID uuid) {
        User user = userRepository.findByUuid(uuid);
        return new UserResponseDto(user);
    }


    @Override
    public UserResponseDto getUserData(String email) {
        List<User> userList = userRepository.findByEmail(email);
        User user = userList.get(0); // 유저는 무조건 하나 중복없음
        return new UserResponseDto(user);
    }

    @Override
    public UUID createUser(UserCreateRequestDto userCreateRequestDto) {
        User user = userCreateRequestDto.toEntity();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        UUID uuid = userRepository.save(user).getUuid();
        return uuid;
    }

    @Override
    public UserResponseDto updateUser(UUID uuid, UserUpdateRequestDto userUpdateRequestDto) {
        User user = userRepository.save(userUpdateRequestDto.toEntity());
        UserResponseDto userResponseDto = new UserResponseDto(user);
        return userResponseDto;
    }

    @Override
    public Boolean deleteUser(UUID uuid) {
        User user = userRepository.findByUuid(uuid);
        user.delete(true);
        userRepository.save(user);
        return user.isDeleted();
    }

    @Override
    public Boolean validationPassword(String email, String password) {
        User loginUser = userRepository.findByEmail(email).get(0);

        if (passwordEncoder.matches(password, loginUser.getPassword())) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean checkDuplicationEmail(String email) {
        List<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            return false;
            //중복아님
        }
        return true;
        //중복임
    }

    @Override
    public Boolean checkDuplicationNickname(String nickname){
        List<User> user = userRepository.findByNickname(nickname);

        if(user.isEmpty()){
            return false;
        }

        return true;
    }

    @Override
    public Boolean existUser(String email){
        List<User> user = userRepository.findByEmail(email);
        if(email.equals(user.get(0).getEmail())){
            return true;
        }
        return false;
    }

}