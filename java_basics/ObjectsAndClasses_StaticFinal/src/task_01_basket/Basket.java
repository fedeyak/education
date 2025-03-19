package tasks.task_01_basket;

public class Basket {

    private static int count = 0;
    static int overallPrice = 0;
    static int overallAmount = 0;
    String items = "";
    int totalPrice = 0;
    int limit;
    int totalWeight = 0;
    BasketAdder adder = new BasketAdder(this);
    BasketPrinter printer = new BasketPrinter(this);


    public Basket() {
        increaseCount(1);
        items = "Список товаров:";
        this.limit = 1000000;
    }

    public Basket(int limit) {
        this();
        this.limit = limit;
    }

    public Basket(String items, int totalPrice) {
        this();
        this.items = this.items + items;
        this.totalPrice = totalPrice;
    }

    public static int getCount() {
        return count;
    }
    public static int getOverallPrice() {

        return overallPrice;
    }
    public static int getOverallAmount() {
        return overallAmount;
    }
    public int getTotalPrice() {
        return totalPrice;
    }
    public double getTotalWeight() {
        return totalWeight;
    }

    public static double getAverageBasketPrice() {
        return (double) overallPrice / overallAmount;
    }

    public static void increaseCount(int count) {
        Basket.count = Basket.count + count;
    }

    public void add(String name, int price) {
        adder.add(name, price);
    }

    public void add(String name, int price, int count) {
        adder.add(name, price, count);
    }

    public void add(String name, int price, int count, double weight) {
        adder.add(name, price, count, weight);
    }

    public void clear() {
        items = "";
        totalPrice = 0;
    }

    public void print(String title) {
        printer.print(title);
    }
}



