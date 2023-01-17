package es.fernando.spring.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Class SpringBootDataJpaApplication.
 */
@SpringBootApplication
public class SpringBootDataJpaApplication implements CommandLineRunner{

	/*@Autowired
	private BCryptPasswordEncoder passwordEncoder;*/
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	/*	// TODO Auto-generated method stub
		String password = "12345";
		
		for(int i=0; i<2; i++) {
			String bcryptPassword = passwordEncoder.encode(password);
			System.out.println(bcryptPassword);
		}*/
	}
}
