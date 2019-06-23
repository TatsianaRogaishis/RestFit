package com.restfit.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.After;
import org.junit.Test;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.*;

import static org.hamcrest.MatcherAssert.assertThat;

import com.restfit.model.User;
import com.restfit.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
	@Autowired
	  private TestRestTemplate restTemplate;
	  @Autowired
	  private UserRepository userRepository;
	  
	  @After
	  public void resetDb() {
	    userRepository.deleteAll();
	    userRepository.flush();
	  }
	  
	  @Test
		public void givenUser_whenCreateUser_thenStatus200() {
			
			User user = new User("testuser", "testpass");

			ResponseEntity<User> response = restTemplate.postForEntity("/login", user, User.class);

			assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
			assertThat(response.getBody().getId(), notNullValue());
			assertThat(response.getBody().getLogin(), is("testuser"));
		}
	  
}
