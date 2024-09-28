package com.pro.tuitdormitory.repository;

import com.pro.tuitdormitory.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<com.pro.tuitdormitory.domain.Role, Long> {
    Optional<com.pro.tuitdormitory.domain.Role> findByRole(Role role);
}
