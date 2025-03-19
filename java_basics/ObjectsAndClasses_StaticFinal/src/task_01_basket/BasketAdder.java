package tasks.task_01_basket;

public class BasketAdder {
    Basket basket;

    public BasketAdder(Basket basket) {
        this.basket = basket;
    }

    void add(String name, int price) {
        add(name, price, 1);
    }

    public void add(String name, int price, int count) {
        boolean error = false;
        if (contains(name)) {
            error = true;
        }

        if (basket.totalPrice + count * price >= basket.limit) {
            error = true;
        }

        if (error) {
            System.out.println("Error occured :(");
            return;
        }

        basket.items = basket.items + "\n" + name + " - " +
                count + " шт. - " + price + " руб.";

        basket.totalPrice = basket.totalPrice + count * price;
        Basket.overallPrice = Basket.overallPrice + count * price;
        Basket.overallAmount += count;
    }

    public void add(String name, int price, int count, double weight) {
        add(name, price, count);
        basket.totalWeight += weight;
    }

    public boolean contains(String name) {
        return basket.items.contains(name);
    }
}
