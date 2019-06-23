package com.restfit.endpoint;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.restfit.model.User;
import com.restfit.model.UserFitData;
import com.restfit.repository.UserFitDataRepository;
import com.restfit.repository.UserRepository;
import com.restfit.soap.AddUserFitDataRequest;
import com.restfit.soap.AddUserFitDataResponse;
import com.restfit.soap.GetUserFitDataByLoginRequest;
import com.restfit.soap.GetUserFitDataByLoginResponse;
import com.restfit.soap.GetUserFitDatasRequest;
import com.restfit.soap.GetUserFitDatasResponse;
import com.restfit.soap.ServiceStatus;
import com.restfit.soap.UserFitDatas;


@Endpoint
public class UserFitDataEndpoint {
	private static final String NAMESPACE_URI = "http://localhost:8080/soap/fitdata-ws";
	 
    private UserFitDataRepository userFitDataRepository;
    @Autowired
    private UserRepository userRepository;
 
    @Autowired
    public UserFitDataEndpoint(UserFitDataRepository userFitDataRepository) {
        this.userFitDataRepository = userFitDataRepository;
    }
 
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserFitDatasRequest")
    @ResponsePayload
    public GetUserFitDatasResponse getFitData(@RequestPayload GetUserFitDatasRequest request) {
    	GetUserFitDatasResponse response = new GetUserFitDatasResponse();
    	User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	response.getUserFitDatas().addAll(getUserFitDatas(user));

        return response;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserFitDataByLoginRequest")
	@ResponsePayload
	public GetUserFitDataByLoginResponse getUserFitDataByLogin(@RequestPayload GetUserFitDataByLoginRequest request) {
    	GetUserFitDataByLoginResponse response = new GetUserFitDataByLoginResponse();
    	User user = userRepository.findByLogin(request.getLogin());
		if (user != null) {
			response.getUserFitDatas().addAll(getUserFitDatas(user));
		}
    	
		return response;
	}
    
    private List<UserFitDatas> getUserFitDatas(User user) {
    	List<UserFitData> userFitDatas = userFitDataRepository.findByUser(user);
		List<UserFitDatas> userFitDatasList = new ArrayList<>();
		for (int i = 0; i < userFitDatas.size(); i++) {
			UserFitDatas ob = new UserFitDatas();
			BeanUtils.copyProperties(userFitDatas.get(i), ob);
			userFitDatasList.add(ob);
		}
		return userFitDatasList;
    }
    
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addUserFitDataRequest")
	@ResponsePayload
	public AddUserFitDataResponse addArticle(@RequestPayload AddUserFitDataRequest request) {
    	AddUserFitDataResponse response = new AddUserFitDataResponse();		
    	        ServiceStatus serviceStatus = new ServiceStatus();
    	UserFitData userFitData = new UserFitData();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		userFitData.setUser(user);
		userFitData.setDistance(request.getDistance());
		userFitData.setRace_date(request.getRaceDate().toGregorianCalendar().getTime());
		userFitData.setRun_time(request.getRunTime());
		UserFitData savedData = userFitDataRepository.save(userFitData);

		if (savedData == null) {
			serviceStatus.setStatusCode("FAILED");
			serviceStatus.setMessage("Failed to save data");
			response.setServiceStatus(serviceStatus);
		} else {

			serviceStatus.setStatusCode("SUCCESS");
			serviceStatus.setMessage("Content Added Successfully");
			response.setServiceStatus(serviceStatus);
		}
		return response;
	}
    
}
