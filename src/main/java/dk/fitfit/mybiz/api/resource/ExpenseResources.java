package dk.fitfit.mybiz.api.resource;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;


public class ExpenseResources extends ResourceSupport {
	protected List<ExpenseResource> expenses;

	public void setExpenses(final List<ExpenseResource> expenses) {
		this.expenses = expenses;
	}

	public List<ExpenseResource> getExpenses() {
		return expenses;
	}
}
