public class Main {
    public static void main(String[] args) {
        Container container = new Container();
        container.addCount(5672);
        System.out.println(container.getCount());

        char ch = 0;
        while (ch != 'А') {
            ch++;
        }

        for (; ch <= 'я'; ch++) {
            System.out.println(ch + " = " + (int) ch);
            if (ch == 'Е') {
                findSymbol('Ё');
            }
            if (ch == 'е') {
                findSymbol('ё');
            }
        }
    }

    public static void findSymbol(char symbol) {
        char ch = 0;
        while (ch != symbol) {
            ch++;
        }
        System.out.println(ch + " = " + (int) ch);
    }
}

