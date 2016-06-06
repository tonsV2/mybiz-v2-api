package dk.fitfit.mybiz.api.controller;

import dk.fitfit.mybiz.api.resource.ApiResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/v0.1")
	public ApiResource getApi() {
		log.info("getApi()");
		return new ApiResource();
	}
}
