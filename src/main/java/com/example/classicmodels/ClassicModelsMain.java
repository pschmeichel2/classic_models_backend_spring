package com.example.classicmodels;

import org.hibernate.boot.model.naming.ImplicitNamingStrategy;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;

@SpringBootApplication
public class ClassicModelsMain {

	// private static final Logger log =
	// LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		if (!available(8080)) {
			System.out.println("Port 8080 already in use, aborting");
			return;
		}
		SpringApplication.run(ClassicModelsMain.class, args);
	}

	@Bean
	public PhysicalNamingStrategy physical() {
		return new PhysicalNamingStrategyStandardImpl();
	}

	@Bean
	public ImplicitNamingStrategy implicit() {
		return new ImplicitNamingStrategyLegacyJpaImpl();
	}

	/**
	 * Checks to see if a specific port is available.
	 *
	 * @param port the port to check for availability
	 */
	public static boolean available(int port) {

		ServerSocket ss = null;
		DatagramSocket ds = null;
		try {
			ss = new ServerSocket(port);
			ss.setReuseAddress(true);
			ds = new DatagramSocket(port);
			ds.setReuseAddress(true);
			return true;
		} catch (IOException e) {
		} finally {
			if (ds != null) {
				ds.close();
			}

			if (ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					/* should not be thrown */
				}
			}
		}

		return false;
	}
}
