package com.pro.tuitdormitory.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class JWToken {
    private String tokenId;
    public JWToken(String tokenId) {
        this.tokenId = tokenId;
    }

}