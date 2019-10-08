package com.company.pad;
import java.util.UUID;

public class itemId {
    private  UUID uuid;
    private String randomUUIDString;

    public itemId() {
        this.uuid = UUID.randomUUID();
        this.randomUUIDString = this.uuid.toString();
    }

    public String getRandomUUIDString() {
        return this.randomUUIDString;
    }

    @Override
    public String toString() {
        return "itemId{" +
                "randomUUIDString='" + randomUUIDString + '\'' +
                '}';
    }
}
