package zuul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * An Instance of the Zuul gateway server.
 * 
 * configure using gateway-server.yml
 *
 * @version 1.0.0
 * @since   June 2017
 * @author  Eduardo Francisco Barbosa 
 */
@SpringBootApplication
@EnableZuulProxy
public class GatewayServer {
	
	private static final Logger logger = LoggerFactory.getLogger(GatewayServer.class);

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 *
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		logger.info("ZuulServer - Starting...");
		// Tell server to look for registration.properties or registration.yml
		System.setProperty("spring.config.name", "zuul-server");
		// Start the Server
		SpringApplication.run(GatewayServer.class, args);
	}

}
