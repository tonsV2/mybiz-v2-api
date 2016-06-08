package dk.fitfit.mybiz.api.resource.assembler;

import dk.fitfit.mybiz.business.domain.Expense;
import org.springframework.hateoas.Link;

import java.util.List;


public interface ResourceLinksAssemblerInterface {
	List<Link> getLinks(Expense expense);
}
