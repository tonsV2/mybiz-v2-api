package dk.fitfit.mybiz.api.controller;

import dk.fitfit.mybiz.business.service.CrudServiceInterface;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

public abstract class CrudController<T, ID extends Serializable> {
	protected CrudServiceInterface<T, ID> service;

	CrudController(CrudServiceInterface<T, ID> service) {
		this.service = service;
	}

	@GetMapping
	public List<T> findAll() {
		return service.findAll();
	}

	@GetMapping(value = "/{id}")
	public T findOne(@PathVariable ID id) {
		return service.findOne(id);
	}

	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
	public T save(@RequestBody T entity) {
		return service.save(entity);
	}
/* Clashes with the above mapping, @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}) public T save(@RequestBody T entity)
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
	public List<T> save(@RequestBody List<T> entities) {
		return entities.stream().map(service::save).collect(Collectors.toList());
	}
*/
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable ID id) {
		service.delete(id);
	}
}
