package com.restfit.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.notNullValue;

import java.util.Date;

import org.junit.After;
import org.junit.Test;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.*;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.List;

import com.restfit.model.User;
import com.restfit.model.UserFitData;
import com.restfit.repository.UserFitDataRepository;
import com.restfit.repository.UserRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserFitDataControllerTest {
	@Autowired
	  private TestRestTemplate restTemplate;
	  @Autowired
	  private UserFitDataRepository repository;
	  @Autowired
	  private UserRepository userRepository;
	  @After
	  public void resetDb() {
	    repository.deleteAll();
	    repository.flush();
	    userRepository.deleteAll();
	    userRepository.flush();
	  }

	@Test
	public void givenUserFitDatas_whenGetUserFitDatas_thenStatus200() {
		User user = createTestUser("testlogin");
		repository.saveAndFlush(createTestUserFitData(100, new Date(2019, 7, 15), 1600, user));
		repository.saveAndFlush(createTestUserFitData(500, new Date(2019, 5, 1), 3000, user));

		ResponseEntity<List<UserFitData>> response = restTemplate.exchange("/home/notes", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<UserFitData>>() {
				});
		List<UserFitData> datas = response.getBody();
		assertThat(datas, hasSize(2));
		assertThat(datas.get(1).getDistance(), is(500));
	}

	@Test
	public void givenUserFitData_whenCreateUserFitData_thenStatus200() {

		UserFitData userFitData = createTestUserFitData(300, new Date(2019, 8, 5), 1600, createTestUser("testuser"));

		ResponseEntity<UserFitData> response = restTemplate.postForEntity("/persons", userFitData, UserFitData.class);
		UserFitData data = response.getBody();
		assertThat(response.getStatusCode(), is(HttpStatus.CREATED));

		assertThat(data.getUser().getLogin(), is("testuser"));
		assertThat(data.getDistance(), is(300));
	}

	@Test
	public void givenUserFitData_whenGetUserFitData_thenStatus200() {

		User user = createTestUser("testlogin");
		long id = repository.saveAndFlush(createTestUserFitData(100, new Date(2019, 7, 15), 1600, user)).getId();

		UserFitData userFitData = restTemplate.getForObject("/home/notes/{id}", UserFitData.class, id);
		assertThat(userFitData.getDistance(), is(100));
	}
	  
	@Test
	public void whenUpdateUserFitData_thenStatus200() {
		User user = createTestUser("testlogin");
		long id = repository.saveAndFlush(createTestUserFitData(100, new Date(2019, 7, 15), 1600, user)).getId();
		UserFitData update = createTestUserFitData(100, new Date(2019, 7, 20), 160, user);
		HttpEntity<UserFitData> entity = new HttpEntity<UserFitData>(update);

		ResponseEntity<UserFitData> response = restTemplate.exchange("/home/notes/{id}", HttpMethod.PUT, entity, UserFitData.class, id);
		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		assertThat(response.getBody().getId(), notNullValue());
		assertThat(response.getBody().getRun_time(), is(160));
	}
	  
	private User createTestUser(String login) {
		User user = new User(login, "testPassword");
		return userRepository.saveAndFlush(user);
	}
	
	private UserFitData createTestUserFitData(int distance, Date date, int run_time, User user) {
		UserFitData userFitData = new UserFitData();
		userFitData.setDistance(distance);
		userFitData.setRace_date(date);
		userFitData.setRun_time(run_time);
		userFitData.setUser(user);
		return userFitData;
	}
}
