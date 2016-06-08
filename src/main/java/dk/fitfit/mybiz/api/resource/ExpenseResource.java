package dk.fitfit.mybiz.api.resource;

import org.springframework.hateoas.ResourceSupport;


public class ExpenseResource extends ResourceSupport {
	protected String name;
	protected String description;
	protected double price;
	protected int amount = 1;

	// Required for spring to be able to instantiate during posting of data
	public ExpenseResource() {
	}

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
