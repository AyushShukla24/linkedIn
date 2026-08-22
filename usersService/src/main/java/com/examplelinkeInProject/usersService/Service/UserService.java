package com.examplelinkeInProject.usersService.Service;

import com.examplelinkeInProject.usersService.Dto.LoginRequestDto;
import com.examplelinkeInProject.usersService.Dto.SignupRequestDto;
import com.examplelinkeInProject.usersService.Dto.UserDto;
import com.examplelinkeInProject.usersService.Entity.User;
import com.examplelinkeInProject.usersService.Repository.UserRepository;
import com.examplelinkeInProject.usersService.utils.BCrypt;
import com.examplelinkeInProject.usersService.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.lang.module.ResolutionException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    public UserDto signupUser(SignupRequestDto signupRequestDto) {
        User user = userRepository.findByEmail(signupRequestDto.getEmail());

        if(!ObjectUtils.isEmpty(user)) throw new BadRequestException("User Already exists ");

        signupRequestDto.setPassword(BCrypt.hash(signupRequestDto.getPassword()));

        User newUser = userRepository.save(modelMapper.map(signupRequestDto,User.class));
        return modelMapper.map(newUser,UserDto.class);
    }

    public String loginUser(LoginRequestDto loginRequestDto) {

        User user = userRepository.findByEmail(loginRequestDto.getEmail());
        if(ObjectUtils.isEmpty(user)) throw new ResolutionException("User not found");

        boolean isPasswordMatched = BCrypt.match(loginRequestDto.getPassword(),user.getPassword());

        if(!isPasswordMatched) throw new BadRequestException("Invalid Credentials");

        String token = jwtService.generateAccessToken(user);

        return token;
    }
}
