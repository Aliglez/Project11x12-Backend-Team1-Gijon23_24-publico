package com.x12.project11x12.implementations;

public interface IEncryptFacade {

    String encode(String type, String data);
    String decode(String type, String data);
    
}