/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kitco.webservice.server;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Result login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestHeader("Accept") String acceptHeader, HttpSession session) {

        Result r = new Result();
        r.setStatus(true);
        r.setMessage("Valid user " + username + " and " + password);
        
        session.setAttribute("user", username);
        
        return r;
    }
}
