package dk.fitfit.mybiz.api.controller;

import dk.fitfit.mybiz.api.resource.ExpenseResource;
import dk.fitfit.mybiz.api.resource.ExpenseResources;
import dk.fitfit.mybiz.api.resource.assembler.ExpenseResourceAssembler;
import dk.fitfit.mybiz.business.domain.Expense;
import dk.fitfit.mybiz.business.service.ExpenseServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class ExpenseController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ExpenseServiceInterface expenseService;

	@Autowired
	ExpenseResourceAssembler assembler;

	@RequestMapping("/expense/prototype")
	public ExpenseResource getPrototype() {
		log.info("getPrototype()");
		Expense expense = new Expense();
		return assembler.toResource(expense);
	}

	@RequestMapping("/expense/{id}")
	public ExpenseResource getExpense(@PathVariable Long id) {
		log.info("getExpense({})", id);
		Expense expense = expenseService.findOne(id);
		return assembler.toResource(expense);
	}

	@RequestMapping(value = "/expense/{id}", method = DELETE)
	public ExpenseResource deleteExpense(@PathVariable Long id) {
		log.info("deleteExpense({})", id);
		Expense expense = expenseService.findOne(id);
		return assembler.toResource(expense);
	}

	@RequestMapping(value = "/expense/{id}", method = POST)
	public ExpenseResource postExpense(@PathVariable Long id, @RequestBody ExpenseResource resource) {
		log.info("postExpense({})", id);
		Expense expense = new Expense();
		expense.setId(id);
		expense.setName(resource.getName());
		expense.setDescription(resource.getDescription());
		expense.setPrice(resource.getPrice());
		expense.setAmount(resource.getAmount());
		return assembler.toResource(expense);
	}

	@RequestMapping("/expenses")
	public ExpenseResources getExpenses() {
		log.info("getExpenses()");
		return assembler.toResources(expenseService.findAll());
	}
}
