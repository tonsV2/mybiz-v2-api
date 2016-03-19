package dk.fitfit.mybiz.api.resource;

import org.springframework.hateoas.Link;

import java.util.List;


public class ExpenseResourceBuilder {
	private String name;
	private String description;
	private double price;
	private int amount;
	private List<Link> links;

	public ExpenseResourceBuilder withName(final String name) {
		this.name = name;
		return this;
	}

	public ExpenseResourceBuilder withDescription(final String description) {
		this.description = description;
		return this;
	}

	public ExpenseResourceBuilder withPrice(final double price) {
		this.price = price;
		return this;
	}

	public ExpenseResourceBuilder withAmount(final int amount) {
		this.amount = amount;
		return this;
	}

	public ExpenseResourceBuilder withLinks(final List<Link> links) {
		this.links = links;
		return this;
	}

	public ExpenseResource create() {
		ExpenseResource resource = new ExpenseResource(name, description, price, amount);
		resource.add(links);
		return resource;
	}
}
