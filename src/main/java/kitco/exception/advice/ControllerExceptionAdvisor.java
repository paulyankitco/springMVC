package kitco.exception.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerExceptionAdvisor {
	
	@ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception exception) {
    	exception.printStackTrace();
        System.out.println("Exceptions Handler By @ControllerAdvice...");
        ModelAndView view = new ModelAndView("/error");
        //view.addObject("ex", exception);
        return view;
    }

}
