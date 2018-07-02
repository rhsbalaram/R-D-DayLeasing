package com.dayLeasing.configuration;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import com.mchange.v2.c3p0.ComboPooledDataSource;

// TODO: Auto-generated Javadoc
/**
 * The Class Configuration.
 *
 * @author Balaram
 */
@EnableScheduling
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class Configuration {

	/** The Constant PROPERTY_NAME_DATABASE_DRIVER. */
	private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";

	/** The Constant PROPERTY_NAME_DATABASE_PASSWORD. */
	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";

	/** The Constant PROPERTY_NAME_DATABASE_URL. */
	private static final String PROPERTY_NAME_DATABASE_URL = "db.url";

	/** The Constant PROPERTY_NAME_DATABASE_USERNAME. */
	private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";

	/** The Constant PROPERTY_NAME_HIBERNATE_DIALECT. */
	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";

	/** The Constant PROPERTY_NAME_HIBERNATE_SHOW_SQL. */
	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	// private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN
	// =
	// "entitymanager.packages.to.scan";

	/** The env. */
	@Resource
	private Environment env;

	/*
	 * @Override public void addResourceHandlers(ResourceHandlerRegistry
	 * registry) {
	 * registry.addResourceHandler("/resources/**").addResourceLocations
	 * ("/resources/"); }
	 */

	/*
	 * @Bean public LocalContainerEntityManagerFactoryBean
	 * entityManagerFactory() throws IllegalStateException,
	 * PropertyVetoException { final LocalContainerEntityManagerFactoryBean em =
	 * new LocalContainerEntityManagerFactoryBean();
	 * em.setDataSource(dataSource()); em.setPackagesToScan(new String[] {
	 * "com.gi.insite.persistence.model" }); final HibernateJpaVendorAdapter
	 * vendorAdapter = new HibernateJpaVendorAdapter();
	 * em.setJpaVendorAdapter(vendorAdapter);
	 * em.setJpaProperties(additionalProperties()); return em; }
	 */

	/*
	 * @Bean(destroyMethod="") public DataSource dataSource() { final
	 * JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
	 * dsLookup.setResourceRef(true); DataSource dataSource =
	 * dsLookup.getDataSource("java:comp/env/jdbc/DGSDB"); return dataSource; }
	 */
	/**
	 * Data source.
	 *
	 * @return the data source
	 * @throws IllegalStateException
	 *             the illegal state exception
	 * @throws PropertyVetoException
	 *             the property veto exception
	 */
	@Bean
	public DataSource dataSource() throws IllegalStateException,
			PropertyVetoException {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setDriverClass(env
				.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
		comboPooledDataSource.setJdbcUrl(env
				.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
		comboPooledDataSource.setUser(env
				.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
		comboPooledDataSource.setPassword(env
				.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
		comboPooledDataSource.setInitialPoolSize(5);
		comboPooledDataSource.setMinPoolSize(5);
		comboPooledDataSource.setMaxPoolSize(50000);

		comboPooledDataSource.setAcquireIncrement(20);
		comboPooledDataSource.setMaxStatements(100);
		comboPooledDataSource.setAcquireRetryAttempts(100);
		comboPooledDataSource.setAcquireRetryDelay(10);

		comboPooledDataSource.setBreakAfterAcquireFailure(false);
		comboPooledDataSource.setMaxIdleTime(200);
		comboPooledDataSource.setMaxConnectionAge(30000);
		comboPooledDataSource.setMaxIdleTimeExcessConnections(3000);
		//comboPooledDataSource.setCheckoutTimeout(1000);
		//comboPooledDataSource.setIdleConnectionTestPeriod(100);
		comboPooledDataSource.setTestConnectionOnCheckout(true);
		comboPooledDataSource.setPreferredTestQuery("SELECT 1");
		comboPooledDataSource.setTestConnectionOnCheckin(true);

		return comboPooledDataSource;
	}

	/*
	 * @Bean public HibernateExceptionTranslator hibernateExceptionTranslator(){
	 * return new HibernateExceptionTranslator(); }
	 */

	/**
	 * Gets the session factory.
	 *
	 * @param dataSource
	 *            the data source
	 * @return the session factory
	 */
	@Bean(name = "sessionFactory1")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(
				dataSource);

		sessionBuilder.addProperties(hibernateProperties());

		sessionBuilder
				.scanPackages(new String[] { "com.dayLeasing.dao.model" });

		return sessionBuilder.buildSessionFactory();
	}

	/**
	 * Hibernate properties.
	 *
	 * @return the properties
	 */
	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put(PROPERTY_NAME_HIBERNATE_DIALECT,
				env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
		properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL,
				env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
		return properties;
	}

	/**
	 * Transaction manager.
	 *
	 * @param sessionFactory
	 *            the session factory
	 * @return the hibernate transaction manager
	 */
	@Bean(name = "transactionManager")
	public HibernateTransactionManager transactionManager(
			SessionFactory sessionFactory) {

		HibernateTransactionManager transactionManager = new HibernateTransactionManager(
				sessionFactory);

		return transactionManager;
	}

	/**
	 * Content negotiating view resolver.
	 *
	 * @param manager
	 *            the manager
	 * @return the view resolver
	 */
	/*@Bean
	public ViewResolver contentNegotiatingViewResolver(
			ContentNegotiationManager manager) {

		List<ViewResolver> resolvers = new ArrayList<ViewResolver>();

		InternalResourceViewResolver r1 = new InternalResourceViewResolver();
		r1.setPrefix("/WEB-INF/pages/");
		r1.setSuffix(".jsp");
		r1.setViewClass(JstlView.class);
		resolvers.add(r1);

		JsonViewResolver r2 = new JsonViewResolver();
		resolvers.add(r2);

		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setViewResolvers(resolvers);
		resolver.setContentNegotiationManager(manager);
		return resolver;

	}*/

	/**
	 * Gets the multipart resolver.
	 *
	 * @return the multipart resolver
	 */
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver getMultipartResolver() {
		/* return new CommonsMultipartResolver(); */

		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(20971520); // 20MB
		multipartResolver.setMaxInMemorySize(1048576); // 1MB
		return multipartResolver;
	}
	
	@Bean(name = "verifymail")
	public String getMailVerificationString(){
		Path path;
		try {
			path = Paths.get(getClass().getClassLoader()
				     .getResource("Verify.txt").toURI());
			 byte[] fileBytes = Files.readAllBytes(path);
			   String data = new String(fileBytes);
			   return data;
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "texttoreplacehere";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "texttoreplacehere";
		}       
			  
	}
	
	@Bean(name = "passwordchange")
	public String getMailPasswordChangeString(){
		Path path;
		try {
			path = Paths.get(getClass().getClassLoader()
				     .getResource("passwordchange.txt").toURI());
			 byte[] fileBytes = Files.readAllBytes(path);
			   String data = new String(fileBytes);
			   return data;
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "texttoreplacehere";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "texttoreplacehere";
		}       
			  
	}
	
	@Bean(name = "payment")
	public String getPayment(){
		Path path;
		try {
			path = Paths.get(getClass().getClassLoader()
				     .getResource("payment.txt").toURI());
			 byte[] fileBytes = Files.readAllBytes(path);
			   String data = new String(fileBytes);
			   return data;
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "texttoreplacehere";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "texttoreplacehere";
		}       
			  
	}

	/**
	 * View resolver for returning JSON in a view-based system. Always returns a
	 * {@link MappingJacksonJsonView}.
	 */

	public class JsonViewResolver implements ViewResolver {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.springframework.web.servlet.ViewResolver#resolveViewName(java
		 * .lang.String, java.util.Locale)
		 */
		@Override
		public View resolveViewName(String viewName, Locale locale)
				throws Exception {
			MappingJackson2JsonView view = new MappingJackson2JsonView();
			view.setPrettyPrint(true);
			return view;
		}

		/*
		 * @Bean public ViewResolver jsonViewResolver() { return new
		 * JsonViewResolver(); }
		 */

	}

	/**
	 * Property place holder configurer.
	 *
	 * @return the property sources placeholder configurer
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	/**
	 * Locale resolver.
	 *
	 * @return the locale resolver
	 */
	@Bean
	public LocaleResolver localeResolver() {
		final CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);
		return cookieLocaleResolver;
	}

	/**
	 * Message source.
	 *
	 * @return the message source
	 */
	@Bean
	public MessageSource messageSource() {
		final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(0);
		return messageSource;
	}

	/**
	 * Java mail sender impl.
	 *
	 * @return the java mail sender impl
	 */
	@Bean
	public JavaMailSenderImpl javaMailSenderImpl() {
		final JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
		mailSenderImpl.setHost(env.getProperty("smtp.host"));

		mailSenderImpl.setPort(env.getProperty("smtp.port", Integer.class));

		mailSenderImpl.setProtocol(env.getProperty("smtp.protocol"));
		mailSenderImpl.setUsername(env.getProperty("smtp.username"));
		mailSenderImpl.setPassword(env.getProperty("smtp.password"));
		final Properties javaMailProps = new Properties();
		javaMailProps.put("mail.smtp.auth", true);
		javaMailProps.put("mail.smtp.starttls.enable", true);
		javaMailProps.put("mail.debug", "true");
		mailSenderImpl.setJavaMailProperties(javaMailProps);
		return mailSenderImpl;
	}

}
