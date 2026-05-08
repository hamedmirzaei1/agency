package data;

import java.util.concurrent.ThreadLocalRandom;

public class IDKeeper {
    public String idGenerator() {
        return String.format("%08d", ThreadLocalRandom.current().nextInt(100000000));
    }
}
