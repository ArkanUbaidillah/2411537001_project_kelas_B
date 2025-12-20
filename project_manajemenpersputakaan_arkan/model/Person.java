package model;

public abstract class Person {
    protected String id;
    protected String name;

    public abstract String getSummary();

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
