package dk.fitfit.mybiz.api.resource;

import org.springframework.hateoas.ResourceSupport;


public class ExpenseResource extends ResourceSupport {
	private String name;
	private String description;
	private double price;
	private int amount = 1;

	protected ExpenseResource(final String name, final String description, final double price, final int amount) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}

	public int getAmount() {
		return amount;
	}
}
