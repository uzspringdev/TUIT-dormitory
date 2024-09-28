package com.pro.tuitdormitory.dto.request;

import com.pro.tuitdormitory.domain.Role;
import com.pro.tuitdormitory.model.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@ToString
public class UserDto{

    private Long id;

    private String email;

    private String password;

    private String phoneNumber;

    private Set<Role> roles = new HashSet<>();

    private Status status = Status.ENABLE;

}
