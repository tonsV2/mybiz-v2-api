package dk.fitfit.mybiz.api.configuration;

import dk.fitfit.mybiz.business.domain.*;
import dk.fitfit.mybiz.business.service.*;
import org.springframework.stereotype.Component;

@Component
public class DataInitialization {
	private ExpenseServiceInterface expenseService;
	private ProductServiceInterface productService;
	private OrderServiceInterface orderService;
	private UserServiceInterface userService;
	private ClientServiceInterface clientService;

	public DataInitialization(ExpenseServiceInterface expenseService, ProductServiceInterface productService, OrderServiceInterface orderService, UserServiceInterface userService, ClientServiceInterface clientService) {
		this.expenseService = expenseService;
		this.productService = productService;
		this.orderService = orderService;
		this.userService = userService;
		this.clientService = clientService;

		loadData();
	}

	private void loadData() {
		Expense expense = new Expense();
		expense.setName("Some expense");
		expenseService.save(expense);

		User user = createUser("username", "password", "email");

		Product product = createProduct(user, "1 hour of programming", 400);
		Product product1 = createProduct(user, "1 hour of support", 300);
		Product product2 = createProduct(user, "1 month of hosting", 100);

		Client client = createClient(user, "Some client", "some@client.com");

		Order order = createOrder(user, client, product, product1, product2);
		Order foundOrder = orderService.findOne(order.getId());
	}

	private Order createOrder(User user, Client client, Product product, Product product1, Product product2) {
		Order order = new Order();
		order.setUser(user);
		order.setClient(client);
		order.setTimestamp(System.currentTimeMillis());
		order.addProduct(product, 666);
		order.addProduct(product1, 2);
		order.addProduct(product2, 12);
		orderService.save(order);
		return order;
	}

	private Client createClient(User user, String name, String email) {
		Client client = new Client();
		client.setUser(user);
		client.setName(name);
		client.setEmail(email);
		return clientService.save(client);
	}

	private User createUser(String username, String password, String email) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		userService.save(user);
		return user;
	}

	private Product createProduct(User user, String name, int price) {
		Product product = new Product();
		product.setUser(user);
		product.setName(name);
		product.setPrice(price);
		productService.save(product);
		return product;
	}
}
