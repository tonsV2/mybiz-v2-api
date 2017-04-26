package dk.fitfit.mybiz.api.controller;

import dk.fitfit.mybiz.business.domain.Client;
import dk.fitfit.mybiz.business.service.CrudServiceInterface;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/clients")
@RestController
public class ClientController extends CrudController<Client, Long> {
	ClientController(CrudServiceInterface<Client, Long> service) {
		super(service);
	}
}
