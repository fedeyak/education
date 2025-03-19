
public class Concatenation {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        StringBuilder builder = new StringBuilder();

        String str = "some text some text some text";
        for (int i = 0; i < 20_000; i++) {
            builder.append(str);
        }

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }
}
