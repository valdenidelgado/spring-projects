package com.test.junit.api.services.impl;

import com.test.junit.api.domain.User;
import com.test.junit.api.domain.dtos.UserDTO;
import com.test.junit.api.repositories.UserRepository;
import com.test.junit.api.services.UserService;
import com.test.junit.api.services.exception.DataIntegratyViolationException;
import com.test.junit.api.services.exception.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public User findById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("User not found"));
        return user;
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> mapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO create(UserDTO userDTO) {
        findByEmail(userDTO);
        User user = mapper.map(userDTO, User.class);
        return mapper.map(userRepository.save(user), UserDTO.class);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        findByEmail(userDTO);
        User obj = mapper.map(userDTO, User.class);
        return mapper.map(userRepository.save(obj), UserDTO.class);
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        userRepository.deleteById(id);
    }

    private void findByEmail(UserDTO obj) {
        Optional<User> user = userRepository.findByEmail(obj.getEmail());
        if (user.isPresent() && !user.get().getId().equals(obj.getId())) {
            throw new DataIntegratyViolationException("Email already exists");
        }
    }
}
