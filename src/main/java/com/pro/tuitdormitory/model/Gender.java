package com.pro.tuitdormitory.model;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE");
    private String name;

    Gender(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public static Gender getGender(final String name) {
        for (Gender gender : Gender.values()) {
            if (gender.getName().equals(name)) {
                return gender;
            }
        }
        return Gender.MALE;
    }
}
