package com.pro.tuitdormitory.service;


import com.pro.tuitdormitory.domain.User;
import com.pro.tuitdormitory.dto.request.UserDto;

import java.util.List;

public interface UserService {

    User save(UserDto userDto);

    User save(User user);

    User findById(Long id);

    User findByEmail(String email);
    User findByUsername(String username);


    Object getProfile();

    List<User> findAll();

    User update(Long id, UserDto userDto);

    void deleteById(Long id);

    User getCurrentUser();

    Object getCurrentUser(User user);


}
