import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Loader {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        String filePath = "C:\\Users\\admin\\IdeaProjects\\skillbox\\java_basics\\Performance\\CarNumberGenerator\\res\\region_";


        char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

        new Thread(() -> {
            for (int regionCode = 1; regionCode < 100; regionCode++) {
                StringBuilder builder = new StringBuilder();
                String regionCodeStr = padNumber(regionCode, 2);
                PrintWriter writer;
                try {
                    writer = new PrintWriter(filePath + regionCodeStr + ".txt");
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                for (int number = 1; number < 1000; number++) {
                    String numberStr = padNumber(number, 3);
                    for (char firstLetter : letters) {
                        for (char secondLetter : letters) {
                            for (char thirdLetter : letters) {
                                builder.append(firstLetter);
                                builder.append(numberStr);
                                builder.append(secondLetter);
                                builder.append(thirdLetter);
                                builder.append(regionCodeStr);
                                builder.append("\n");
                            } } }}
                writer.write(builder.toString());
                writer.flush();
                writer.close();
            }}).start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            final long endTime = System.currentTimeMillis();
            System.out.println("Calculated in: " + (endTime - start) + " ms" );
        }));

//        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    private static String padNumber(int number, int numberLength) {
        StringBuilder numberStr = new StringBuilder(Integer.toString(number));
        StringBuilder builder = new StringBuilder();
        int padSize = numberLength - numberStr.length();

        for (int i = 0; i < padSize; i++) {
            builder.append("0");
        }

        return builder.append(numberStr).toString();
    }
}
