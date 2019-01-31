package kitco.core;

import org.springframework.context.ApplicationContext;

public interface ISpringContext {

    ApplicationContext getContext();
    
    <T> T lookup(String type);
    
    @SuppressWarnings("rawtypes")
    <T> T lookup(Class cls);
    
    @SuppressWarnings("rawtypes")
    boolean isSingleton(Class cls);

}