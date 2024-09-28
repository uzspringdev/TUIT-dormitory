package com.pro.tuitdormitory.service.impl;

import com.pro.tuitdormitory.domain.Admin;
import com.pro.tuitdormitory.domain.User;
import com.pro.tuitdormitory.dto.request.AdminDto;
import com.pro.tuitdormitory.mapper.AdminMapper;
import com.pro.tuitdormitory.model.Role;
import com.pro.tuitdormitory.repository.AdminRepository;
import com.pro.tuitdormitory.service.AdminService;
import com.pro.tuitdormitory.service.RoleService;
import com.pro.tuitdormitory.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    private final UserService userService;

    private final RoleService roleService;
    private final AdminMapper adminMapper = AdminMapper.getInstance;
    private final BCryptPasswordEncoder encoder;

    @Override
    public Admin save(AdminDto adminDto) {
        Admin admin = adminMapper.toEntity(adminDto);
        User user = new User();
        for (Role userRole : adminDto.getRoles()) {
            user.getRoles().clear();
            com.pro.tuitdormitory.domain.Role role = roleService.findByRole(userRole);
            user.addRole(role);
        }
        user.setUsername(adminDto.getUsername());
        user.setEmail(adminDto.getEmail());
        user.setPhoneNumber(adminDto.getPhoneNumber());
        user.setStatus(adminDto.getStatus());
        user.setPassword(encoder.encode(adminDto.getPassword()));

        admin.setUser(user);
        return adminRepository.save(admin);
    }

    @Override
    public Admin save(Admin admin) {

        return adminRepository.save(admin);
    }

    @Override
    public Admin findById(Long id) {
        return adminRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such Admin"));
    }

    @Override
    public Admin findByEmail(String email) {
        return adminRepository.findByUserEmail(email).orElseThrow(() -> new NoSuchElementException("No such Admin"));
    }

    @Override
    public Admin getCurrentAdmin() {
        return null;
    }

    @Override
    public Admin getCurrentAdmin(String email) {
        return adminRepository.findByUserEmail(email).orElseThrow(() -> new NoSuchElementException("No such Admin"));
    }

    @Override
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    @Override
    public Admin update(Long id, AdminDto adminDto) {
        Admin admin = findById(id);
        Admin updatedAdmin = adminMapper.updateFromDto(adminDto, admin);
        User user = userService.findById(admin.getUser().getId());

        user.setEmail(adminDto.getEmail());
        user.setStatus(adminDto.getStatus());
        user.setPhoneNumber(adminDto.getPhoneNumber());

        user.setPassword(encoder.encode(adminDto.getPassword()));

        updatedAdmin.setUser(user);
        return adminRepository.save(updatedAdmin);
    }

    @Override
    public void deleteById(Long id) {
        adminRepository.deleteById(id);
    }
}
