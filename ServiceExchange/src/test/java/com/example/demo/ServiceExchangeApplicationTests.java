package com.example.demo;

import com.service_exchange.api_services.dao.user.UserInterFace;
import com.service_exchange.entities.UserTable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = AuditConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ServiceExchangeApplicationTests {


	@Autowired
	UserInterFace userInterFace;
	@Test
	@Commit
	public void UserTestCreateUser() {
		UserTable userTable = new UserTable();
		userTable.setName("ahmed");
		userTable.setAccountId("123456789");
		userTable.setImage("af");
		System.out.println(userInterFace.createUser(userTable));
		userTable.setName("mohamed");
		userTable.setImage("ddqq");
		//System.out.println(userTable);
		UserTable userTable1 = new UserTable();
		userTable1.setAccountId(userTable.getAccountId());
		System.out.println("this is create test start");
		System.out.println(userInterFace.createUser(userTable1));
		System.out.println(userInterFace.getAllUser(0).stream().collect(Collectors.toList()));
		System.out.println("this is the end of test start");

	}

	@Test
	public void SearchUserByName() {
		System.out.println(("this is start of test search"));
		System.out.println(userInterFace.scerchUserByName("m", 0).stream().collect(Collectors.toList()));
		System.out.println("this is end of test search");
	}

}

@Configuration
@EnableJpaAuditing
@ComponentScan(basePackages = "com.service_exchange")
@EnableJpaRepositories({"com.service_exchange.api_services.dao"})
@EntityScan("com.service_exchange.entities")
class AuditConfiguration {
}
