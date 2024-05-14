package Knapsack;

import java.util.ArrayList;
import java.util.List;

public class Knapsack {
    private List<Item> items;
    private int capacity;
    private int[][] dp;

    public Knapsack(int capacity) {
        this.capacity = capacity;
        this.items = new ArrayList<>();
    }
    

    public int getCapacity() {
		return capacity;
	}


	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}


	public void addItem(Item item) {
        items.add(item);
    }

    public int totalValue() {
        int totalValue = 0;
        for (Item item : items) {
            totalValue += item.getValue();
        }
        return totalValue;
    }

    public int KnapsackDP() {
        int n = items.size();
        dp = new int[n + 1][capacity + 1];

        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (items.get(i - 1).getWeight() <= w) {
                    dp[i][w] = Math.max(items.get(i - 1).getValue() + dp[i - 1][w - items.get(i - 1).getWeight()], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        return dp[n][capacity];
    }

    public void getBag() {
        List<Item> selectedItems = new ArrayList<>();
        int w = capacity;
        for (int i = items.size(); i > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                selectedItems.add(items.get(i - 1));
                w -= items.get(i - 1).getWeight();
            }
        }

        System.out.println("Items selected:");
        for (Item item : selectedItems) {
            System.out.println("- " + item.getName() + " (Weight: " + item.getWeight() + ", Value: " + item.getValue() + ")");
        }
    }

    
}