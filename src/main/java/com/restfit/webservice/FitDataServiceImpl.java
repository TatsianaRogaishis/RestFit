package com.restfit.webservice;

import java.util.List;

import javax.jws.WebService;

import com.restfit.model.User;
import com.restfit.model.UserFitData;

@WebService(endpointInterface = "com.restfit.webservice.FitDataService",
serviceName = "FitDataService")
public class FitDataServiceImpl implements FitDataService {

	@Override
	public String getDataForId(String id) {
		
		return id;
	}

	@Override
	public String testService() {
		return "Hello from SOAP Webservice!";
	}

	@Override
	public String sayHelloTo(String text) {
		return "Hello to " + text;
	}

	@Override
	public List<UserFitData> getUserFitData() {
		User user = new User("123", "123");
		return user.getUserFitDatas();
	}

}
