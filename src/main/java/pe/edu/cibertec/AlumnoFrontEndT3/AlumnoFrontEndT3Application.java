package pe.edu.cibertec.AlumnoFrontEndT3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AlumnoFrontEndT3Application {

	public static void main(String[] args) {
		SpringApplication.run(AlumnoFrontEndT3Application.class, args);
	}

}
