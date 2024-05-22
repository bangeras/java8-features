package com.svb.optional;


import java.util.Optional;

public class Employee {
    private String name;
    private Optional<Address> address;

    public Employee(String name) {
        this.name = name;
        this.address = Optional.empty();
    }

    public Optional<Address> getAddress() {
        return address;
    }
}

class Address {
    private String city;
    private String street;

    private String country;

    public Address(String city, String street, String country) {
        this.city = city;
        this.street = street;
        this.country = country;
    }

    public String getCity() {
        return city;
    }
}