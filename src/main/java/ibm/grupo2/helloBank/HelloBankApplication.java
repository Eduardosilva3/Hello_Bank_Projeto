package ibm.grupo2.helloBank;

import ibm.grupo2.helloBank.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class HelloBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloBankApplication.class, args);
	}

	@Bean
	public PasswordEncoder getPassWordEncoder(){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}



}
