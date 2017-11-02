package aop.spring.noInterfaceCall;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.stereotype.Component;

/**
 * Created by zk on 02/11/2017.
 */
@Component
public class GreetingIntroAdvice  extends DelegatingIntroductionInterceptor  {
    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        return super.invoke(mi);
    }
}
