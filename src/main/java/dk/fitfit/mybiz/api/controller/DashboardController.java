package dk.fitfit.mybiz.api.controller;

import dk.fitfit.mybiz.business.domain.Dashboard;
import dk.fitfit.mybiz.business.domain.ExpenseOverview;
import dk.fitfit.mybiz.business.domain.OrderOverview;
import dk.fitfit.mybiz.business.domain.User;
import dk.fitfit.mybiz.business.service.ExpenseServiceInterface;
import dk.fitfit.mybiz.business.service.OrderServiceInterface;
import dk.fitfit.mybiz.business.service.UserServiceInterface;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RestController
public class DashboardController {
	private ExpenseServiceInterface expenseService;
	private OrderServiceInterface orderService;
	private UserServiceInterface userService;

	public DashboardController(ExpenseServiceInterface expenseService, OrderServiceInterface orderService, UserServiceInterface userService) {
		this.expenseService = expenseService;
		this.orderService = orderService;
		this.userService = userService;
	}

	@GetMapping("/dashboard")
	public Dashboard getDashboard() {
		long from = LocalDateTime.of(2000, 4, 1, 0, 0).toEpochSecond(ZoneOffset.UTC);
		long to = LocalDateTime.of(2020, 4, 30, 0, 0).toEpochSecond(ZoneOffset.UTC);
		User user = userService.findOne(2L);

		ExpenseOverview expenseOverview = expenseService.calculateOverview(user, from, to);
		OrderOverview orderOverview = orderService.calculateOverview(user, from, to);

		double surplus = orderOverview.getTotalPriceWithoutVat() - expenseOverview.getTotalPriceWithoutVat();
		double totalVat = orderOverview.getTotalVat() - expenseOverview.getTotalVat();
		return new Dashboard(surplus, totalVat);
	}
}
