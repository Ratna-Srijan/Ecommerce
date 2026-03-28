package com.ecommerce.user_service.Service;
import com.ecommerce.user_service.DTO.UserRequestDto;
import com.ecommerce.user_service.DTO.UserResponseDto;
import com.ecommerce.user_service.Exception.DuplicateResourceException;
import com.ecommerce.user_service.Exception.ResourceNotFoundException;
import com.ecommerce.user_service.Model.User;
import com.ecommerce.user_service.Repoistory.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    // Repository connections -

    private final UserRepository userRepository;

    public UserService(UserRepository userRepo){
        this.userRepository=userRepo;
    }

    // DTO logics -

    private User mapToEntity(UserRequestDto dto){
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPhoneNo(dto.getPhoneNo());
        user.setCreatedAt(java.time.LocalDateTime.now());
        return user;
    }

    private UserResponseDto mapToResponseDto(User user){
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhoneNo(user.getPhoneNo());
        dto.setActive(user.getActive());
        dto.setCreatedAt(user.getCreatedAt());

        return dto;
    }


    // Mapping Logics -

    public UserResponseDto createUser(UserRequestDto dto){
        if(userRepository.existsByEmail(dto.getEmail())){
            throw new DuplicateResourceException("Email already registered");
        }
        User user = mapToEntity(dto);
        user.setActive(true);

        User savedUser = userRepository.save(user);
        return mapToResponseDto(savedUser);
    }

    public UserResponseDto getUserById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return mapToResponseDto(user);
    }

    public List <UserResponseDto> getAllUsers(){
        return userRepository.findAll().stream()
                .map(this :: mapToResponseDto).toList();
    }

    public void deleteUserById(Long id){
        if(!userRepository.existsById(id)){
            throw new ResourceNotFoundException("User doesn't exists!");
        }
        userRepository.deleteById(id);
    }

    public UserResponseDto updateUser(Long id, UserRequestDto dto){

        User existingUser = userRepository.findById(id)
                        .orElseThrow(()-> new ResourceNotFoundException("User not found!"));

        if (userRepository.existsByEmail(dto.getEmail()) &&
                !existingUser.getEmail().equals(dto.getEmail())){
            throw new ResourceNotFoundException("Email already in use.");
        }

        existingUser.setFirstName(dto.getFirstName());
        existingUser.setLastName(dto.getLastName());
        existingUser.setPhoneNo(dto.getPhoneNo());

        User savedUser = userRepository.save(existingUser);
        return mapToResponseDto(savedUser);

    }


}
