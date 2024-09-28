package com.pro.tuitdormitory.controller;

import com.pro.tuitdormitory.domain.Admin;
import com.pro.tuitdormitory.dto.request.AdminDto;
import com.pro.tuitdormitory.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admins")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody AdminDto adminDto) {
        Admin admin = adminService.save(adminDto);

        return ResponseEntity.ok(admin);
    }

    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
        Admin admin = adminService.findById(id);

        return ResponseEntity.ok(admin);
    }


    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findAll() {
        List<Admin> faculties = adminService.findAll();

        return ResponseEntity.ok(faculties);
    }

    @PostMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody AdminDto adminDto) {
        Admin admin = adminService.update(id, adminDto);

        return ResponseEntity.ok(admin);
    }

    @GetMapping(value = "/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") Long id) {
        adminService.deleteById(id);
        return ResponseEntity.ok("Admin is deleted");
    }

}
