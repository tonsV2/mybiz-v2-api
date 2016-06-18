package dk.fitfit.mybiz.api.resource;

import dk.fitfit.mybiz.api.controller.ApiController;
import dk.fitfit.mybiz.api.controller.ExpenseController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


public class ApiResource extends ResourceSupport {
	private String title = "MyBiz2 API";
	private String description = "Rest API for My Business";
	private String version = "0.1";

	public ApiResource() {
		addLinks();
	}

	private void addLinks() {
		add(linkTo(methodOn(ApiController.class).getApi()).withSelfRel());
		add(linkTo(methodOn(ExpenseController.class).getExpenses()).withRel("expenses"));

		long id = 54321L;
		Link fakeExpenseByIdLink = linkTo(methodOn(ExpenseController.class).getExpense(id)).withSelfRel();
		// TODO: This sucks!!! What to do... ? Is it that bad? What if I generate a random number
		String href = fakeExpenseByIdLink.getHref().replace(String.valueOf(id), "{id}");
		Link expenseByIdLink = new Link(href).withRel("expense");
		add(expenseByIdLink);
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getVersion() {
		return version;
	}
}
