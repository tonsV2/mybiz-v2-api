package dk.fitfit.mybiz.api.controller;

import dk.fitfit.mybiz.api.resource.ApiResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiController {
	@GetMapping("/v0.1")
	public ApiResource getApi() {
		return new ApiResource();
	}
}
