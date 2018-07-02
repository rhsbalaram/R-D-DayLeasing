package com.dayLeasing;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

// TODO: Auto-generated Javadoc
/**
 * The Class ServletInitializer.
 * to provide web support
 */
/**
 * @author Balaram
 *
 */
public class ServletInitializer extends SpringBootServletInitializer {

	/* (non-Javadoc)
	 * @see org.springframework.boot.web.support.SpringBootServletInitializer#configure(org.springframework.boot.builder.SpringApplicationBuilder)
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DayLeasingApplication.class);
	}

}
