package kitco.interceptor;

import java.io.IOException;
import java.lang.reflect.Method;
import java.security.interfaces.RSAPrivateKey;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kitco.bean.model.TokenModel;
import kitco.security.Authorization;
import kitco.security.RSASecurityUtil2_UNUSED;
import kitco.security.Security;
import kitco.security.TokenManager;

@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenManager manager;

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        String authorization = request.getHeader("token");

        TokenModel model = manager.getToken(authorization);
        
        return true;
        /*if (manager.checkToken(model)) {
            request.setAttribute("CURRENT_USER_ID", model.getUserId());
            return true;
        }
        if(model != null && model.getToken() != null){

        	String plainTextStr = null;
        	try{
        		plainTextStr = Security.decrypt(model.getToken());
        	}catch(BadPaddingException e){
        		writeResponse(response);
        		return false;
        	}
			String[] tokenStrArray = plainTextStr.split("_");
			if(tokenStrArray != null && tokenStrArray.length == 2 && "paul".equals(tokenStrArray[0]) && 
					"123".equals(tokenStrArray[1])){
				return true;
			}
			writeResponse(response);
			return false;
        }

        if (model == null || method.getAnnotation(Authorization.class) != null) {
            writeResponse(response);
            return false;
        }
        return false;*/
    }
    
    private void writeResponse(HttpServletResponse response) throws IOException{
    	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("text/plain;charset=UTF-8");
		response.getWriter().write("You donot have permission to access this url, code: 401");
    }
}