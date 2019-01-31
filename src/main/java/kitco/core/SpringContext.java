package kitco.core;

import org.springframework.context.ApplicationContext;


public abstract class SpringContext implements ISpringContext  {

    @Override
    public ApplicationContext getContext() {
        return SpringHelper.getInstance().getApplicationContext();
    }
}