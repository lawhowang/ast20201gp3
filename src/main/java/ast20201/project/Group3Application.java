/*
 * References
 * https://segmentfault.com/a/1190000014832006
 */
package ast20201.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Group3Application extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(Group3Application.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Group3Application.class);
    }
}
