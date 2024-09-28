package com.pro.tuitdormitory.service.impl;

import com.pro.tuitdormitory.model.Role;
import com.pro.tuitdormitory.repository.RoleRepository;
import com.pro.tuitdormitory.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public com.pro.tuitdormitory.domain.Role findByRole(Role role) {
        return roleRepository.findByRole(role).orElseThrow(()->new NoSuchElementException("No such Role"));
    }
}
