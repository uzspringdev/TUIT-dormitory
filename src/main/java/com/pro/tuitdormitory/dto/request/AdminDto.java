package com.pro.tuitdormitory.dto.request;


import com.pro.tuitdormitory.model.Role;
import com.pro.tuitdormitory.model.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@ToString
public class AdminDto {

    private Long id;

    private String username;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String phoneNumber;

    private Status status = Status.ENABLE;

    private Set<Role> roles = new HashSet<>();

}
