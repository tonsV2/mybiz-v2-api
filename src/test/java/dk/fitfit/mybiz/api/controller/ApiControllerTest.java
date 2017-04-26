package dk.fitfit.mybiz.api.controller;

import dk.fitfit.mybiz.api.resource.ApiResource;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.Link;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiControllerTest extends TestCase {
	@Autowired
	private ApiController controller;

	@Before
	public void before() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	}

	@Test
	public void testGetApi() throws Exception {
		// Given
		ApiResource resource = controller.getApi();

		// When
		String actualTitle = resource.getTitle();
		String actualDescription = resource.getDescription();
		String actualVersion = resource.getVersion();

		List<Link> links = resource.getLinks();
		Link self = links.get(0);
		Link expenses = links.get(1);

		// Then
		assertThat(actualTitle, is(equalTo("MyBiz2 API")));
		assertThat(actualDescription, is(equalTo("Rest API for My Business")));
		assertThat(actualVersion, is(equalTo("0.1")));

		assertThat(links.size(), is(equalTo(2)));

		assertThat(self.getRel(), is(equalTo("self")));
		assertThat(self.getHref(), endsWith("/api/v0.1"));

		assertThat(expenses.getRel(), is(equalTo("expenses")));
		assertThat(expenses.getHref(), endsWith("/api/expenses"));
	}
}
