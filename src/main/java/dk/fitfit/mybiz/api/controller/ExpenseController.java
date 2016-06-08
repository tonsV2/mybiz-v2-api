package dk.fitfit.mybiz.api.controller;

import dk.fitfit.mybiz.api.resource.ExpenseResource;
import dk.fitfit.mybiz.api.resource.ExpenseResources;
import dk.fitfit.mybiz.api.resource.assembler.ExpenseResourceAssembler;
import dk.fitfit.mybiz.business.domain.Expense;
import dk.fitfit.mybiz.business.service.ExpenseServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;


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

	@RequestMapping(value = "/expense/{id}", method = PUT)
	public ResponseEntity<Void> putExpense(@PathVariable Long id, @RequestBody ExpenseResource resource) {
		log.info("putExpense({})", id);
		Expense expense = expenseService.findOne(id);
		expense.setName(resource.getName());
		expense.setDescription(resource.getDescription());
		expense.setPrice(resource.getPrice());
		expense.setAmount(resource.getAmount());
		expenseService.save(expense);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/expense", method = POST)
	public ResponseEntity<ExpenseResource> postExpense(@RequestBody ExpenseResource resource) {
		log.info("postExpense()");
		Expense expense = new Expense();
		expense.setName(resource.getName());
		expense.setDescription(resource.getDescription());
		expense.setPrice(resource.getPrice());
		expense.setAmount(resource.getAmount());
		return new ResponseEntity<>(assembler.toResource(expense), HttpStatus.CREATED);
	}

	@RequestMapping("/expenses")
	public ExpenseResources getExpenses() {
		log.info("getExpenses()");
		return assembler.toResources(expenseService.findAll());
	}
}
