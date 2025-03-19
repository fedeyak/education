public class Arithmetic {

    int a;
    int b;

    public Arithmetic(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int addition() {
        return a + b;
    }

    public int product() {
        return a * b;
    }

    public int max() {
        return a > b ? a : b;
    }

    public int min() {
        return a < b ? a : b;
    }
}