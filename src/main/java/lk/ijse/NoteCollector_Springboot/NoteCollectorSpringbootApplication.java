package lk.ijse.NoteCollector_Springboot;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
/*
@SpringBootApplication
	Meta annotated ->  @Configuration, @EnableAutoConfiguration, @ComponentScan
*/
public class NoteCollectorSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoteCollectorSpringbootApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}


/*
        PM
        | (accept / not accept)
	Dev -> UAT -> pre-prod -> Prod    👀
	                           | Server IP
	                           | Credentials - database
	                           | Ports
*/