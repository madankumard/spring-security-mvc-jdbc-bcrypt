package mk.springdemo.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "mk.springdemo")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoConfig {

	private Logger logger = Logger.getLogger(getClass().getName());

	@Autowired
	private Environment env;

	@Bean
	public DataSource securityDataSource() {

		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();

		logger.info(">>> Jdbc.url=" + env.getProperty("jdbc.url"));
		logger.info(">>> Jdbc.user=" + env.getProperty("jdbc.user"));

		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
			securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
			securityDataSource.setUser(env.getProperty("jdbc.user"));
			securityDataSource.setPassword(env.getProperty("jdbc.password"));
			
			securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
			securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
			securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
			securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
			
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}

		return securityDataSource;
	}

	@Bean
	public ViewResolver viewResolver() {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}
	
	private int getIntProperty(String propName) {
		String propVal = env.getProperty(propName);
		int intPropVal = Integer.parseInt(propVal);
		return intPropVal;
	}
}
