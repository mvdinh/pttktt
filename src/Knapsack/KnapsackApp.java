package Knapsack;

public class KnapsackApp {
	public static void main(String[] args) {
        Knapsack knapsack = new Knapsack(50);
        knapsack.addItem(new Item("Item 1", 10, 60));
        knapsack.addItem(new Item("Item 2", 20, 100));
        knapsack.addItem(new Item("Item 3", 30, 120));
        System.out.println("Maximum value: " + knapsack.KnapsackDP());
        System.out.println("Weight:"+knapsack.getCapacity());
        knapsack.getBag();
    }
}
f