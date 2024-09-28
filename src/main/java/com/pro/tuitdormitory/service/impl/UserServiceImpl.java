package com.pro.tuitdormitory.service.impl;

import com.pro.tuitdormitory.domain.*;
import com.pro.tuitdormitory.dto.request.UserDto;
import com.pro.tuitdormitory.mapper.UserMapper;
import com.pro.tuitdormitory.model.Role;
import com.pro.tuitdormitory.repository.UserRepository;
import com.pro.tuitdormitory.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper = UserMapper.getInstance;
    private final BCryptPasswordEncoder encoder;


    @Override
    public User save(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such element"));
    }


    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("No such element"));
    }

    @Override
    public Object getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String name = authentication.getName();
            User user = findByUsername(name);
            return findByUsername(name);
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


    @Override
    public User update(Long id, UserDto userDto) {
        User user = findById(id);
        return userRepository.save(userMapper.updateFromDto(userDto, user));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String name = authentication.getName();
            return findByUsername(name);
        }
        return null;
    }

    @Override
    public Object getCurrentUser(User user) {

        return null;
    }


}
