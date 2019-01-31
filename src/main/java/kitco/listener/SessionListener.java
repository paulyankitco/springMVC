package kitco.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
	
	public static Map<String, HttpSession> userMap = new HashMap<String, HttpSession>();  
    private MySessionContext myc = MySessionContext.getInstance();  
  
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {  
        myc.AddSession(httpSessionEvent.getSession());  
    }  
  
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {  
        HttpSession session = httpSessionEvent.getSession();  
        myc.DelSession(session); 
    }  
}
