package com.enigma.tokonyadia.entity;

import java.util.Objects;

public class Store {
    String name;
    String phoneNumber;
    Integer id;
    String siupNumber;
    String address;

    public Store(){}

    public Store(String name, String phoneNumber, Integer id, String siupNumber, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.id = id;
        this.siupNumber = siupNumber;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSiupNumber() {
        return siupNumber;
    }

    public void setSiupNumber(String siupNumber) {
        this.siupNumber = siupNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return Objects.equals(name, store.name) && Objects.equals(phoneNumber, store.phoneNumber) && Objects.equals(id, store.id) && Objects.equals(siupNumber, store.siupNumber) && Objects.equals(address, store.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phoneNumber, id, siupNumber, address);
    }

    @Override
    public String toString() {
        return "Store{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", id=" + id +
                ", siupNumber='" + siupNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
