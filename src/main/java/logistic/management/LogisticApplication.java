package logistic.management;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@RequiredArgsConstructor
public class TransportApplication {

  /*  @PostConstruct
    public void initUsers() {
        List<User> users = Stream.of(
                new User( "emmy", "emmytemmy@gmail.com", "N0 3 raye street","emmytemmy","emmy"),
                new User( "emmy", "emmytemmy@gmail.com", "N0 3 raye street","user1","pwd1")
                ).collect(Collectors.toList());
        userRepository.saveAll(users);
    }*/

	public static void main(String[] args) {

        SpringApplication.run(TransportApplication.class, args);
	}




}
