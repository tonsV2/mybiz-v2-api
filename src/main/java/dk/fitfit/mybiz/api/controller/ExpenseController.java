package dk.fitfit.mybiz.api.controller;

import com.google.common.collect.Lists;
import dk.fitfit.mybiz.api.resource.ExpenseResource;
import dk.fitfit.mybiz.api.resource.ExpenseResources;
import dk.fitfit.mybiz.api.resource.assembler.ExpenseResourceAssembler;
import dk.fitfit.mybiz.business.domain.Expense;
import dk.fitfit.mybiz.business.service.ExpenseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/expenses")
public class ExpenseController {
	private ExpenseServiceInterface expenseService;
	private ExpenseResourceAssembler assembler;

	@Autowired
	public ExpenseController(ExpenseServiceInterface expenseService, ExpenseResourceAssembler assembler) {
		this.expenseService = expenseService;
		this.assembler = assembler;
	}

	@GetMapping("/prototype")
	public ExpenseResource getPrototype() {
		Expense expense = new Expense();
		return assembler.toResource(expense);
	}

	@GetMapping("/{id}")
	public ExpenseResource getExpense(@PathVariable Long id) {
		Expense expense = expenseService.findOne(id);
		return assembler.toResource(expense);
	}

	@DeleteMapping("/{id}")
	public ExpenseResource deleteExpense(@PathVariable Long id) {
		Expense expense = expenseService.findOne(id);
		return assembler.toResource(expense);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> putExpense(@PathVariable Long id, @RequestBody ExpenseResource resource) {
		// TODO: Don't was resources looking up the old resource
		// TODO: Refactor this into an assembler
		Expense expense = expenseService.findOne(id);
		expense.setName(resource.getName());
		expense.setDescription(resource.getDescription());
		expense.setPrice(resource.getPrice());
		expense.setAmount(resource.getAmount());
		expenseService.save(expense);
		return ResponseEntity.noContent().build();
	}

	@PostMapping
	public ResponseEntity<ExpenseResource> postExpense(@RequestBody ExpenseResource resource) {
		Expense expense = new Expense();
		expense.setName(resource.getName());
		expense.setDescription(resource.getDescription());
		expense.setPrice(resource.getPrice());
		expense.setAmount(resource.getAmount());
		expenseService.save(expense);
		return new ResponseEntity<>(assembler.toResource(expense), HttpStatus.CREATED);
	}

	@GetMapping("/init")
	public ExpenseResources init() {
		Expense expense = new Expense();
		expense.setName("Shalala name...");
		expense.setDescription("some description");
		expense.setAmount(666);
		expense.setPrice(12);
		Expense saved = expenseService.save(expense);

		Expense expense1 = new Expense();
		expense1.setName("Other expense name...");
		expense1.setDescription("Some other description");
		expense1.setAmount(1212);
		expense1.setPrice(6);
		Expense saved1 = expenseService.save(expense1);

		List<Expense> expenses = Lists.newArrayList(saved, saved1);
		return assembler.toResources(expenses);
	}

	@GetMapping
	public ExpenseResources getExpenses() {
		return assembler.toResources(expenseService.findAll());
	}
}
