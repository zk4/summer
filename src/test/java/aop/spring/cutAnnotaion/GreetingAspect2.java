package aop.spring.cutAnnotaion;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by zk on 02/11/2017.
 */
@Aspect
@Component
public class GreetingAspect2 {


    @Around("@annotation(aop.spring.cutAnnotaion.Tag)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        before();
        Object ret = pjp.proceed();
        after();
        return ret;

    }

    public void before() {
        System.out.println("tag before");
    }

    public void after() {
        System.out.println("tag after");
    }


    public static void main(String[] args) {


            ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring.xml");
        GreetingImpl2 bean = (GreetingImpl2) classPathXmlApplicationContext.getBean(GreetingImpl2.class);

            bean.sayHello("helo");
            bean.goodMorning("helo");
            bean.goodNight("helo");



    }
}
