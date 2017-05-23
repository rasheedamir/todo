package io.as.todo.store.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Home redirection to swagger api documentation
 */
@RestController
public class HomeController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/")
	public String index()
    {
        // TODO - fix this; as it return a string instead of doing a redirect!
		return "redirect:swagger-ui.html";
	}
}
