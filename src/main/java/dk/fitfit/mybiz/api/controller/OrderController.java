package dk.fitfit.mybiz.api.controller;

import dk.fitfit.mybiz.business.domain.Order;
import dk.fitfit.mybiz.business.domain.Totals;
import dk.fitfit.mybiz.business.domain.User;
import dk.fitfit.mybiz.business.service.OrderServiceInterface;
import dk.fitfit.mybiz.business.service.TotalsService;
import dk.fitfit.mybiz.business.service.UserServiceInterface;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RestController
@RequestMapping("/orders")
public class OrderController extends CrudController<Order, Long> {
	private UserServiceInterface userService;

	OrderController(OrderServiceInterface service, UserServiceInterface userService) {
		super(service);
		this.userService = userService;
	}

	@GetMapping("/overview")
	public Totals getOverview() {
		long from = LocalDateTime.of(2000, 4, 1, 0, 0).toEpochSecond(ZoneOffset.UTC);
		long to = LocalDateTime.of(2020, 4, 30, 0, 0).toEpochSecond(ZoneOffset.UTC);
		User user = userService.findOne(2L);

		return ((TotalsService<Order, Long>)service).calculateTotals(user, from, to);
	}
}
