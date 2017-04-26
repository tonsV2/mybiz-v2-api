package dk.fitfit.mybiz.api.configuration;

import dk.fitfit.mybiz.business.domain.*;
import dk.fitfit.mybiz.business.service.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

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
		example();

		User user = createUser("username", "password", "email");

		Product product = createProduct(user, "1 hour of programming", 400);
		Product product1 = createProduct(user, "1 hour of support", 300);
		Product product2 = createProduct(user, "1 month of hosting", 100);

		Client client = createClient(user, "Some client", "some@client.com");

		Order order = createOrder(user, client, product, product1, product2);
		Order foundOrder = orderService.findOne(order.getId());
	}

	private void example() {
		User user = createUser("et", "password", "other");

		LocalDateTime dateTime = LocalDateTime.of(2015, 4, 3, 0, 0);
		createExpense(user, "Larsen Data ApS", 100.39, "Ordernumber 760463", dateTime);

		dateTime = LocalDateTime.of(2015, 4, 5, 0, 0);
		createExpense(user, "Gigahost", 448, "Faktura 211396", dateTime);

		dateTime = LocalDateTime.of(2015, 4, 7, 0, 0);
		createExpense(user, "CineMagic A/S", 1038.81, "FAKTURA  210581", dateTime);

		long from = LocalDateTime.of(2015, 4, 1, 0, 0).toEpochSecond(ZoneOffset.UTC);
		long to = LocalDateTime.of(2015, 4, 30, 0, 0).toEpochSecond(ZoneOffset.UTC);

		List<Expense> expenses = expenseService.findAll(user, from, to);
		double totalVat = expenseService.totalVat(user, from, to);
		double totalPriceWithoutVat = expenseService.totalPriceWithoutVat(user, from, to);
		System.out.println("sdsds");
	}

	private void createExpense(User user, String name, double price, String description, LocalDateTime dateTime) {
		Expense expense = new Expense();
		expense.setUser(user);
		expense.setName(name);
		expense.setPrice(price);
		expense.setDescription(description);
		expense.setTimestamp(dateTime);
		expenseService.save(expense);
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
