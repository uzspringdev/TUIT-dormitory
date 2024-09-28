package com.pro.tuitdormitory.model;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("ADMIN"),
    SUPER_ADMIN("SUPER_ADMIN");


    private String name;

    Role(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Role getRole(final String role) {
        for (Role userRole : Role.values()) {
            if (userRole.getName().equals(role)) {
                return userRole;
            }
        }
        return Role.ADMIN;
    }
}
