public class RedisTestSortedSet {

    public static void main(String[] args) throws InterruptedException {

        RedisStorageSortedSet redis = new RedisStorageSortedSet();
        redis.init();
        redis.addUsers();
        redis.processUsers();
        redis.shutdown();
    }
}
