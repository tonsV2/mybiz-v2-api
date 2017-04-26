package dk.fitfit.mybiz.api.configuration;

import dk.fitfit.mybiz.business.domain.*;
import dk.fitfit.mybiz.business.service.ExpenseServiceInterface;
import dk.fitfit.mybiz.business.service.OrderServiceInterface;
import dk.fitfit.mybiz.business.service.ProductServiceInterface;
import dk.fitfit.mybiz.business.service.UserServiceInterface;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitialization {
	private ExpenseServiceInterface expenseService;
	private ProductServiceInterface productService;
	private OrderServiceInterface orderService;
	private UserServiceInterface userService;

	public DataInitialization(ExpenseServiceInterface expenseService, ProductServiceInterface productService, OrderServiceInterface orderService, UserServiceInterface userService) {
		this.expenseService = expenseService;
		this.productService = productService;
		this.orderService = orderService;
		this.userService = userService;

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

		Product product = createProduct("1 hour of programming", 400);
		Product product1 = createProduct("1 hour of support", 300);
		Product product2 = createProduct("1 month of hosting", 100);

		User user = createUser("username", "password", "email");

		Order order = new Order();
		order.setUser(user);
//		order.setClient(new Client());
		order.setTimestamp(System.currentTimeMillis());
		order.addProduct(product, 666);
		order.addProduct(product1, 2);
		order.addProduct(product2, 12);

		Order savedOrder = orderService.save(order);

		Order foundOrder = orderService.findOne(savedOrder.getId());
//		System.out.println(foundOrder);
		for (OrderEntity orderEntity : foundOrder.getOrderEntities()) {
			String output = String.format("%s - %s", orderEntity.getProduct().getId(), orderEntity.getProduct().getName());
			System.out.println(output);
		}
		System.out.println("sdsd");
	}

	private User createUser(String username, String password, String email) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		userService.save(user);
		return user;
	}

	private Product createProduct(String name, int price) {
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		productService.save(product);
		return product;
	}
}
