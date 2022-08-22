package betterplace.betterplacebcd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class BetterplacebcdApplication {

	public static void main(String[] args) {
		SpringApplication.run(BetterplacebcdApplication.class, args);
	}

}
