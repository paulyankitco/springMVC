package kitco.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import kitco.service.UserService;

@Service(value="userService")
public class UserServiceImpl implements UserService {
	
	public String hello(HttpServletRequest request, HttpServletResponse response, String userName){
		
		return "wawahahahaha, " + userName;
	}

}
