package dk.fitfit.mybiz.api.controller;

import dk.fitfit.mybiz.api.resource.ExpenseResource;
import dk.fitfit.mybiz.api.resource.assembler.ExpenseResourceAssembler;
import dk.fitfit.mybiz.business.domain.Expense;
import dk.fitfit.mybiz.business.service.ExpenseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class ExpenseController {
	@Autowired
	ExpenseServiceInterface expenseService;

	@Autowired
	ExpenseResourceAssembler assembler;

	@RequestMapping("/expense/{id}")
	public ExpenseResource getExpense(@PathVariable Long id) {
		Expense expense = expenseService.findOne(id);
		return assembler.toResource(expense);
	}

	@RequestMapping("/expenses")
	public List<ExpenseResource> getExpenses() {
		return assembler.toResources(expenseService.findAll());
	}
}
