package tasks.task_01_basket;

 class BasketPrinter {

     Basket basket;
     public BasketPrinter(Basket basket) {
         this.basket = basket;
     }

     public void print(String title) {
         System.out.println(title);
         if (basket.items.isEmpty()) {
             System.out.println("Корзина пуста");
         } else {
             System.out.println(basket.items);
             System.out.println("Итого: " + basket.totalPrice);
             if(basket.totalWeight > 0) {System.out.println("Общий вес: " + basket.totalWeight);}
             System.out.println("Overall price: " + Basket.getOverallPrice());
             System.out.println("Overall amount: " + Basket.getOverallAmount());
             System.out.println();
         }
     }


}
