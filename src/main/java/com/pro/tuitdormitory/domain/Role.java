package com.pro.tuitdormitory.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "role")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Role{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role", unique = true)
    @Enumerated(EnumType.STRING)
    private com.pro.tuitdormitory.model.Role role;

    public Role(com.pro.tuitdormitory.model.Role role) {
        this.role = role;
    }
}
