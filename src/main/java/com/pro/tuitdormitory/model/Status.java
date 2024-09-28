package com.pro.tuitdormitory.model;

import lombok.Getter;

@Getter
public enum Status {
    ENABLE("ENABLE"),
    DISABLE("DISABLE"),
    BLOCKED("BLOCKED"),
    SUGGESTED("SUGGESTED"),
    ACCEPTED("ACCEPTED"),
    REJECTED("REJECTED"),
    PENDING("PENDING"),
    APPROVED("APPROVED");
    private String name;

    Status(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public static Status getStatus(final String name) {
        for (Status status : Status.values()) {
            if (status.getName().equals(name)) {
                return status;
            }
        }
        return Status.DISABLE;
    }
}
