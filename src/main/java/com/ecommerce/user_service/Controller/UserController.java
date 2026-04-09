package com.ecommerce.user_service.Controller;

import com.ecommerce.user_service.DTO.LoginRequestDTO;
import com.ecommerce.user_service.DTO.UserRequestDto;
import com.ecommerce.user_service.DTO.UserResponseDto;
import com.ecommerce.user_service.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping // Register an user..
    public UserResponseDto createUser(@Valid @RequestBody UserRequestDto dto){
        return userService.createUser(dto);
    }

    @PostMapping("/login")
    public UserResponseDto loginUser(@RequestBody LoginRequestDTO dto){
        return userService.loginUser(dto);
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
        return "User deleted Successfully.";
    }

    @PutMapping("/{id}")
    public UserResponseDto updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDto dto){
        return userService.updateUser(id,dto);

    }

    @GetMapping("/allUsers")
    public List<UserResponseDto> getAllUsers(){
        return userService.getAllUsers();
    }

}
