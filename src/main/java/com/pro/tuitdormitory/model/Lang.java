package com.pro.tuitdormitory.model;

import lombok.Getter;

@Getter
public enum Lang {

    UZ("UZ"),
    RU("RU"),
    EN("EN");

    private String name;

    Lang(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Lang getLang(final String name) {
        for (Lang lang : Lang.values()) {
            if (lang.getName().equals(name)) {
                return lang;
            }
        }
        return Lang.EN;
    }
}
