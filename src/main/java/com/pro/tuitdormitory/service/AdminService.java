package com.pro.tuitdormitory.service;


import com.pro.tuitdormitory.domain.Admin;
import com.pro.tuitdormitory.dto.request.AdminDto;

import java.util.List;

public interface AdminService {

    Admin save(AdminDto adminDto);

    Admin save(Admin admin);

    Admin findById(Long id);

    Admin findByEmail(String email);

    Admin getCurrentAdmin();

    Admin getCurrentAdmin(String email);

    List<Admin> findAll();

    Admin update(Long id, AdminDto adminDto);

    void deleteById(Long id);

}
