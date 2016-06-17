package dk.fitfit.mybiz.api.resource.assembler;

import dk.fitfit.mybiz.api.controller.ExpenseController;
import dk.fitfit.mybiz.business.domain.Expense;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@Component
public class ExpenseResourceLinksAssembler implements ResourceLinksAssemblerInterface {
	@Override
	public List<Link> getLinks(final Expense expense) {
		List<Link> links = new ArrayList<>();
		Long expenseId = expense.getId();
		if(expenseId != null) {
			links.add(linkTo(methodOn(ExpenseController.class).getExpense(expenseId)).withSelfRel());
		} else {
			links.add(linkTo(methodOn(ExpenseController.class).getPrototype()).withSelfRel());
		}

		long id = 54321L;
		Link fakeExpenseByIdLink = linkTo(methodOn(ExpenseController.class).getExpense(id)).withSelfRel();
		// TODO: This sucks!!! What to do... ?
		String href = fakeExpenseByIdLink.getHref().replace(String.valueOf(id), "{Id}");
		Link expenseByIdLink = new Link(href).withRel("expense");
		links.add(expenseByIdLink);

		links.add(linkTo(methodOn(ExpenseController.class).getPrototype()).withRel("prototype"));
		return links;
	}
}
