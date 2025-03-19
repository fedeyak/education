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
        System.out.println("Total weight: " + basket1.getTotalWeight());
    }
}
