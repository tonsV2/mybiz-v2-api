package dk.fitfit.mybiz.api.configuration;

import dk.fitfit.mybiz.business.domain.Expense;
import dk.fitfit.mybiz.business.service.ExpenseServiceInterface;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitialization {
	private ExpenseServiceInterface expenseService;

	public DataInitialization(ExpenseServiceInterface expenseService) {
		this.expenseService = expenseService;
		loadData();
	}

	private void loadData() {
		Expense expense = new Expense();
		expense.setName("Some expense");
		expenseService.save(expense);

		List<Expense> expenses = expenseService.findAll();

		for (Expense exp : expenses) {
			System.out.println(exp.getId());
		}
	}
}
