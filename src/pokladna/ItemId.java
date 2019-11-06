package pokladna;

import java.util.UUID;

public class ItemId {
    private UUID uuid;
    private String randomUUIDString;

    public ItemId() {
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
