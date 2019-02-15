package com.apll.centermanagementsservice.test;

public abstract class interface1Impl implements Interface1 {
    @Override
    public String m1() {
        return "Class::interface1Impl and method::m1";
    }

    @Override
    public String m3() {
        return "Class::interface1Impl and method::m2";
    }

    @Override
    public String m2() {
        return "Class::interface1Impl and method::m3";
    }
}
