package dk.fitfit.mybiz.api.controller;

import dk.fitfit.mybiz.business.domain.User;
import dk.fitfit.mybiz.business.service.CrudServiceInterface;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends CrudController<User, Long> {
	UserController(CrudServiceInterface<User, Long> service) {
		super(service);
	}
}
