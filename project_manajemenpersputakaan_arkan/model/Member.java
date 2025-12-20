package model;

public class Member extends Person {
    private String address;

    public Member(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    @Override
    public String getSummary() {
        return name + " - " + address;
    }

    public String getAddress() {
        return address;
    }
}
