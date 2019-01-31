package kitco.core;

import kitco.Application;

public class BaseSpringContext extends SpringContext{

    static BaseSpringContext springContext = new BaseSpringContext();
    
    public static BaseSpringContext getSpringContext() {
        return springContext;
    }

    public static void setSpringContext(BaseSpringContext springContext) {
    	BaseSpringContext.springContext = springContext;
    }

    @SuppressWarnings("unchecked")
	public <T> T lookup(String type) {
    	
//    	if( getContext().containsBean(type)) {
//            return (T)  getContext().getBean(type);
//        }
        if( Application.applicationContext.containsBean(type)) {
            return (T)  Application.applicationContext.getBean(type);
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
	public <T> T lookup(Class cls) {
        String type = cls.getSimpleName();
        return lookup(type.toLowerCase());
    }
    
    @SuppressWarnings("rawtypes")
    public boolean isSingleton(Class cls) {
        return getContext().isSingleton(cls.getSimpleName().toLowerCase());
    }

}