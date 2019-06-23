package com.restfit.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.restfit.model.UserFitData;

@WebService
//@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface FitDataService {

	@WebMethod
    public String getDataForId(String id);
	
	@WebMethod//annotation optional and is mainly used to provide a name attribute to the public method in wsdl
    String testService();
 
    @WebMethod
    String sayHelloTo(@WebParam(name = "text") String text);
 
    @WebMethod
    List<UserFitData> getUserFitData();
}
