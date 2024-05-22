package com.svb.optional;

public class OptionalDemo {
    public static void main(String[] args) {
        Employee employee = new Employee("Suraj V. Bangera");
        System.out.println(employee.getAddress().map(Address::getCity).orElse("UnknownCity"));
    }
}
