package dk.fitfit.mybiz.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
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

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;


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
		log("putExpense({})", resource);
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

	@RequestMapping(value = {"/expense", "/expenses"}, method = POST)
	public ResponseEntity<ExpenseResource> postExpense(@RequestBody ExpenseResource resource) {
		log("postExpense({})", resource);
		Expense expense = new Expense();
		expense.setName(resource.getName());
		expense.setDescription(resource.getDescription());
		expense.setPrice(resource.getPrice());
		expense.setAmount(resource.getAmount());
		expenseService.save(expense);
		return new ResponseEntity<>(assembler.toResource(expense), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/init", method = GET)
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

	private void log(final String format, final ExpenseResource resource) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(resource);
			log.info(format, json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/expenses")
	public ExpenseResources getExpenses() {
		log.info("getExpenses()");
		return assembler.toResources(expenseService.findAll());
	}
}
