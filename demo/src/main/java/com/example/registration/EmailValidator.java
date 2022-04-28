package com.example.registration;

import java.util.function.Predicate;

public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
        //TODO: Regex for email validation
        return s.contains("@");
    }
}
