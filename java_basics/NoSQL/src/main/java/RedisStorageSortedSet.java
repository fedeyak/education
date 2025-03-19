import org.redisson.Redisson;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.client.protocol.ScoredEntry;
import org.redisson.config.Config;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class RedisStorageSortedSet {
    private RedissonClient redisson;
    private RScoredSortedSet<String> users;
    private final static String KEY = "users";
    private ArrayList<String> paidUsers = new ArrayList<>();
    String firstUserInQueue = "";
    public static final int MIN = 1;
    public static final int MAX = 20;

    void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try {
            redisson = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            out.println("Не удалось подключиться к Redis");
            out.println(Exc.getMessage());
        }
        users = redisson.getScoredSortedSet(KEY);
    }

    void addUsers() {
        for (int i = 0; i < MAX; i++) {
            double userNo = System.currentTimeMillis();
            users.add(userNo, "user " + (i + 1));
        }
    }

    void processUsers() {

        while (true) {
            firstUserInQueue = users.first();

            if (userMadePaiment()) {
                processPaidUser();
            }

            if (firstUserIsPaidUser()) {
                sendFirstUserToTheBackOfTheQueue();
                continue;
            }

            processBasicUser();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean userMadePaiment() {
        return getRandomNumber(1, 10) == getRandomNumber(1, 10);
    }

    private void processPaidUser() {
        int randUser = getRandomNumber(MIN, MAX);
        String paidUser = ((List<ScoredEntry<String>>) users.entryRange(randUser, randUser)).get(0).getValue();
        paidUsers.add(paidUser);
        out.println(paidUser);
    }

    private boolean firstUserIsPaidUser() {
        return paidUsers.contains(firstUserInQueue);
    }

    private void sendFirstUserToTheBackOfTheQueue() {
        paidUsers.remove(firstUserInQueue);
        users.add(System.nanoTime(), firstUserInQueue);
    }

    private void processBasicUser() {
        out.println(firstUserInQueue);
        users.add(System.nanoTime(), firstUserInQueue);
    }


    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    void shutdown() {
        redisson.shutdown();
    }
}
