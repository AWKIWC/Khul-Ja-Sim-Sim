package com.awkiwc.dheemanth.wavrecord.enums;

public enum FragmentType {

    HOME("Awkiwc"),
    LOGIN("Login"),
    SIGNUP("Signup"),
    VOICE("voice");

    private final String name;

    FragmentType(String name){
        this.name = name;
    }

    public String toString(){
        return this.name;
    }
}
