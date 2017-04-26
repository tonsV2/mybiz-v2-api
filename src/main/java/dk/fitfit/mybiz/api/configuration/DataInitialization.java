package dk.fitfit.mybiz.api.configuration;

import dk.fitfit.mybiz.business.domain.Expense;
import dk.fitfit.mybiz.business.domain.Order;
import dk.fitfit.mybiz.business.domain.OrderEntity;
import dk.fitfit.mybiz.business.domain.Product;
import dk.fitfit.mybiz.business.service.ExpenseServiceInterface;
import dk.fitfit.mybiz.business.service.OrderServiceInterface;
import dk.fitfit.mybiz.business.service.ProductServiceInterface;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitialization {
	private ExpenseServiceInterface expenseService;
	private ProductServiceInterface productService;
	private OrderServiceInterface orderService;

	public DataInitialization(ExpenseServiceInterface expenseService, ProductServiceInterface productService, OrderServiceInterface orderService) {
		this.expenseService = expenseService;
		this.productService = productService;
		this.orderService = orderService;
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

		Product product = new Product();
		product.setName("1 hour of programming");
		product.setPrice(400);
		productService.save(product);

		Product product1 = new Product();
		product1.setName("1 hour of support");
		product1.setPrice(300);
		productService.save(product1);

		Product product2 = new Product();
		product2.setName("1 month of hosting");
		product2.setPrice(100);
		productService.save(product2);

		Order order = new Order();
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

	}
}
