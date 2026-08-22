package com.examplelinkeInProject.usersService.Controller;

import com.examplelinkeInProject.usersService.Dto.LoginRequestDto;
import com.examplelinkeInProject.usersService.Dto.SignupRequestDto;
import com.examplelinkeInProject.usersService.Dto.UserDto;
import com.examplelinkeInProject.usersService.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    private ResponseEntity<UserDto> signupUser(@RequestBody SignupRequestDto signupRequestDto){
        UserDto userDto = userService.signupUser(signupRequestDto);
        return new ResponseEntity<>(userDto,HttpStatus.CREATED);
    }

    @PostMapping("/login")
    private ResponseEntity<String> loginUser(@RequestBody LoginRequestDto loginRequestDto){
        String token = userService.loginUser(loginRequestDto);
        return ResponseEntity.ok(token);
    }
}
