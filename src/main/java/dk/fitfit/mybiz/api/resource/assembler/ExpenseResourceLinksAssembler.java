package dk.fitfit.mybiz.api.resource.assembler;

import dk.fitfit.mybiz.api.controller.ExpenseController;
import dk.fitfit.mybiz.business.domain.Expense;
import org.springframework.hateoas.Link;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ExpenseResourceLinksAssembler {
	public List<Link> getLinks(final Expense expense) {
		List<Link> links = new ArrayList<>();
		Long expenseId = expense.getId();
		if(expenseId != null) {
			links.add(linkTo(methodOn(ExpenseController.class).getExpense(expenseId)).withSelfRel());
		} else {
			links.add(linkTo(methodOn(ExpenseController.class).getPrototype()).withSelfRel());
		}
		links.add(linkTo(methodOn(ExpenseController.class).getPrototype()).withRel("prototype"));
		return links;
	}
}
