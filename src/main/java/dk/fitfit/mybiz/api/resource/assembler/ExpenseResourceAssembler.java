package dk.fitfit.mybiz.api.resource.assembler;

import com.google.common.collect.Lists;
import dk.fitfit.mybiz.api.controller.ExpenseController;
import dk.fitfit.mybiz.api.resource.ExpenseResource;
import dk.fitfit.mybiz.api.resource.ExpenseResourceBuilder;
import dk.fitfit.mybiz.api.resource.ExpenseResources;
import dk.fitfit.mybiz.business.domain.Expense;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@Component
public class ExpenseResourceAssembler implements ResourceAssembler<Expense, ExpenseResource> {
	@Override
	public ExpenseResource toResource(final Expense entity) {
		ExpenseResourceLinksAssembler linksAssembler = new ExpenseResourceLinksAssembler();
		ExpenseResourceBuilder builder = new ExpenseResourceBuilder();
		builder.withName(entity.getName())
				.withDescription(entity.getDescription())
				.withAmount(entity.getAmount())
				.withPrice(entity.getPrice())
				.withLinks(linksAssembler.getLinks(entity));
		return builder.build();
	}

	public ExpenseResources toResources(final List<Expense> all) {
		List<ExpenseResource> resources = Lists.transform(all, this::toResource);
		ExpenseResources expenseResources = new ExpenseResources();
		expenseResources.setExpenses(resources);
		expenseResources.add(linkTo(methodOn(ExpenseController.class).getExpenses()).withSelfRel());
		return expenseResources;
	}
}
