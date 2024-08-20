package com.monetize360.lsc.di;

import org.springframework.stereotype.Component;

@Component
public class BirthdayGreetings implements GreetingService{
    @Override
    public void greet(String name) {
        System.out.println("Happy Birthday " + name);
    }
}
