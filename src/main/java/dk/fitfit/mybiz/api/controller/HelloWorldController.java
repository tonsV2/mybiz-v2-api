package dk.fitfit.mybiz.api.controller;

import dk.fitfit.mybiz.business.domain.Expense;
import dk.fitfit.mybiz.business.service.ExpenseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class HelloWorldController {
	@Autowired
	ExpenseServiceInterface expenseService;

	@RequestMapping("/expense/{id}")
	public Expense helloWorld(@PathVariable Long id) {
		return expenseService.findOne(id);
	}
}
