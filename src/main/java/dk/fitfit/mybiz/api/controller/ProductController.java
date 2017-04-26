package dk.fitfit.mybiz.api.controller;

import dk.fitfit.mybiz.business.domain.Product;
import dk.fitfit.mybiz.business.service.ProductServiceInterface;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController extends CrudController<Product, Long> {
	ProductController(ProductServiceInterface service) {
		super(service);
	}
}
