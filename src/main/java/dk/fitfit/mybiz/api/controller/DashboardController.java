package dk.fitfit.mybiz.api.controller;

import dk.fitfit.mybiz.business.domain.*;
import dk.fitfit.mybiz.business.service.TotalsService;
import dk.fitfit.mybiz.business.service.UserServiceInterface;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RestController
public class DashboardController {
	private TotalsService<Expense, Long> expenseService;
	private TotalsService<Order, Long> orderService;
	private UserServiceInterface userService;

	public DashboardController(TotalsService<Expense, Long> expenseService, TotalsService<Order, Long> orderService, UserServiceInterface userService) {
		this.expenseService = expenseService;
		this.orderService = orderService;
		this.userService = userService;
	}

	@GetMapping("/dashboard")
	public Dashboard getDashboard() {
		long from = LocalDateTime.of(2000, 4, 1, 0, 0).toEpochSecond(ZoneOffset.UTC);
		long to = LocalDateTime.of(2020, 4, 30, 0, 0).toEpochSecond(ZoneOffset.UTC);
		User user = userService.findOne(2L);

		Totals<Expense> expenseTotals = expenseService.calculateTotals(user, from, to);
		Totals<Order> orderTotals = orderService.calculateTotals(user, from, to);

		double surplus = orderTotals.getPriceExVat() - expenseTotals.getPriceExVat();
		double totalVat = orderTotals.getVat() - expenseTotals.getVat();
		return new Dashboard(surplus, totalVat);
	}
}
