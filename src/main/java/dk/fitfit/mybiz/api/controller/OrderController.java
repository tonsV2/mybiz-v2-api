package dk.fitfit.mybiz.api.controller;

import dk.fitfit.mybiz.business.domain.Order;
import dk.fitfit.mybiz.business.service.OrderServiceInterface;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController extends CrudController<Order, Long> {
	OrderController(OrderServiceInterface service) {
		super(service);
	}
}
