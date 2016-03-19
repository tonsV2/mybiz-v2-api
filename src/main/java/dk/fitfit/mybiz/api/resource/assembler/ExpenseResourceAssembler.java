package dk.fitfit.mybiz.api.resource.assembler;

import dk.fitfit.mybiz.api.resource.ExpenseResource;
import dk.fitfit.mybiz.api.resource.ExpenseResourceBuilder;
import dk.fitfit.mybiz.business.domain.Expense;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;


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
		return builder.create();
	}
}
