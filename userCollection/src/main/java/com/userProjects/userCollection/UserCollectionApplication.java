package com.userProjects.userCollection;

import com.userProjects.userCollection.entity.User;
import com.userProjects.userCollection.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.List;

@SpringBootApplication
public class UserCollectionApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(UserCollectionApplication.class, args);

		UserRepository userRepository = context.getBean(UserRepository.class);

		// Create and save a user
		User user0 = new User();
		user0.setId("1");
		user0.setName("meet");
		user0.setCity("surat");
		user0.setStatus("jay shree ram");

		userRepository.save(user0);

		User user1 = new User();
		user1.setId("2");
		user1.setName("sarthak");
		user1.setCity("junagadh");
		user1.setStatus("jay shree krishna");

		User save1 = userRepository.save(user1);

		User user2 = new User();
		user2.setId("3");
		user2.setName("jatin");
		user2.setCity("bhavnager");
		user2.setStatus("jay shree swaminarayan");

		User user3 = new User();
		user3.setId("4");
		user3.setName("chirag");
		user3.setCity("vadodara");
		user3.setStatus("jay Dwarkadhis");

		List<User> list1 = List.of(user2, user3);
		userRepository.saveAll(list1);

		Iterable<User> show_all = userRepository.findAll();
		show_all.forEach(user -> {System.out.println(user);});

		System.out.println("User saved successfully!");
	}
}
