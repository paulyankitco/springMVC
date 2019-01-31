package kitco.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
	public String hello(HttpServletRequest request, HttpServletResponse response, String userName);
	
}
