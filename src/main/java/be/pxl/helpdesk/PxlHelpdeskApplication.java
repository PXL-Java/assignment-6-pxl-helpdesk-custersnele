package be.pxl.helpdesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class PxlHelpdeskApplication {

	public static void main(String[] args) {
		SpringApplication.run(PxlHelpdeskApplication.class, args);
	}

}
