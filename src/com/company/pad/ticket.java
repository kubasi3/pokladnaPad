package com.company.pad;

public class ticket extends item {
    private String type;

    public ticket(String name, float price, int count, String type) {
        super(name, price, count);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "ticket{" +
                "type='" + type + '\'' +
                '}';
    }
}
