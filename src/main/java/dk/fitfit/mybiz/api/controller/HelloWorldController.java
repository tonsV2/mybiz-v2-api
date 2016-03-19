package dk.fitfit.mybiz.api.controller;

import dk.fitfit.mybiz.business.domain.Expense;
import dk.fitfit.mybiz.business.service.ExpenseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class HelloWorldController {
	@Autowired
	ExpenseServiceInterface expenseService;

	@RequestMapping("/hello")
	public Expense helloWorld() {
		return expenseService.findOne(123);
	}
}
