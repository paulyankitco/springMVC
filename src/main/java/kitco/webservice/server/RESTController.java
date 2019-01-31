package kitco.webservice.server;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RESTController {
	private Logger logger = Logger.getLogger(this.getClass());

	@RequestMapping(value = "/users/{id}", produces="application/json")
	@ResponseBody
	public Result getUser(@PathVariable Long id, @RequestHeader("Accept") String acceptHeader) {
		logger.info("Serving resource for Accept header: {}: " + acceptHeader);
                Result r = new Result();
                r.setStatus(true);
                r.setMessage("");
                r.setData(new User(id, "John Doe"));
		return r;
	}
        
        
}
