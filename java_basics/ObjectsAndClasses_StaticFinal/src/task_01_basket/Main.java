package tasks.task_01_basket;

public class Main {

    public static void main(String[] args) {
        Basket basket = new Basket();
        basket.add("Milk", 40);
        basket.print("Milk");

        Basket basket1 = new Basket();
        basket1.add("One", 10, 1, 100);
        basket1.add("Two", 20, 2, 200);
        basket1.add("Tree", 30, 3, 300);
        System.out.println();
        basket1.print("Second basket");

        Basket basket2 = new Basket();
        basket2.add("First", 100);
        basket2.add("Second", 5, 20);
        basket2.add("Third", 13, 2);
        basket2.print("Third basket");

        System.out.printf("The average basket price is: %.2f\n", Basket.getAverageBasketPrice());


    }
}
