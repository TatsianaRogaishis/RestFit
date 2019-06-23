package com.restfit.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import org.junit.After;
import org.junit.Test;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.*;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.List;

import com.restfit.model.User;
import com.restfit.model.UserFitData;
import com.restfit.model.UserFitReport;
import com.restfit.repository.UserFitDataRepository;
import com.restfit.repository.UserRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserFitDataReportControllerTest {
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
		public void givenUserFitReport_whenGetUserFitReport_thenStatus200() {
			User user = createTestUser("testlogin");
			createTestUserFitData(200, new Date(2019, 5, 3), 1600, user);
			createTestUserFitData(500, new Date(2019, 5, 4), 3000, user);

			ResponseEntity<List<UserFitReport>> response = restTemplate.exchange("/home/fitreport", HttpMethod.GET, null,
					new ParameterizedTypeReference<List<UserFitReport>>() {
					});
			List<UserFitReport> datas = response.getBody();
			assertThat(datas, hasSize(1)); //one week
			assertThat(datas.get(0).getSumdistance(), is(700));
			assertThat(datas.get(0).getAvgruntime(), is(2300.00));
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
			repository.saveAndFlush(userFitData);
			return userFitData;
		}
}
