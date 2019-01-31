package kitco.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kitco.bean.model.Test;
import kitco.bean.model.User;
import kitco.security.Authorization;

@Controller
@RequestMapping("/testAction")
//@ResponseBody
public class TestController {

	@RequestMapping(value = "/hello.do", method = RequestMethod.GET)
	@Authorization
	public String hello(HttpServletResponse response, HttpServletRequest request, String userName) {

		request.setAttribute("message", "Hello, " + userName);
		System.out.println("in-------");
		//String a = null;
		//System.out.println(a.trim());
		//ModelAndView view = new ModelAndView("/WEB-INF/jsp/hello.jsp");
		//return view;
		return "hello";
	}
	
	@RequestMapping(value = "/greeting.do", method = RequestMethod.GET)
	@Authorization
	@ResponseBody
	public String greeting(HttpServletResponse response, HttpServletRequest request, String userName) throws JsonParseException, JsonMappingException, IOException {
		JSONObject json = new JSONObject();
		json.put("id", 1);
		json.put("userName", "paul");
		json.put("password", "paul123");
//		User user = new User();
//		user.setId(1);
//		user.setUserName("paul");
//		user.setPassword("paul123");
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(json.toString(), User.class);
		Test test = new Test();
		test.setTestId(2);
		test.setTestName("test");
		test.setTestDesc("test description");
		List<User> userList = new ArrayList<User>();
		userList.add(user);
		test.setUserList(userList);
		json = new JSONObject(test);
		
		//Test test2 = mapper.readValue(json.toString(), Test.class);
		//json.put("name", "Paul");
		//json.put("action", "Hi");
		return json.toString();
	}

}
