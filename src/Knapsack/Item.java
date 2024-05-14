package Knapsack;

public class Item {
	private String name;
	private int weight;
	private int value;
	public Item(String name, int weight, int value) {
		super();
		this.name = name;
		this.weight = weight;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Item [name=" + name + ", weight=" + weight + ", value=" + value + "]";
	}
	
}
