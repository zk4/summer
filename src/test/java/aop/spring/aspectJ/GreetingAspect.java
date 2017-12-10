//package aop.spring.aspectJ;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.stereotype.Component;
//
///**
// * Created by zk on 02/11/2017.
// */
//@Aspect
//@Component
//public class GreetingAspect {
//
//
//    @Around("execution(* aop.spring.aspectJ.GreetingImpl.good*(..))")
//    public Object around(ProceedingJoinPoint pjp) throws Throwable {
//        before();
//        Object ret = pjp.proceed();
//        after();
//        return ret;
//
//    }
//
//    public void before() {
//        System.out.println("GreetingAspect before");
//    }
//
//    public void after() {
//        System.out.println("GreetingAspect after");
//    }
//
//
//    public static void main(String[] args) {
//
//
//            ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring.xml");
//        GreetingImpl bean = (GreetingImpl) classPathXmlApplicationContext.getBean("aaa");
//
//            bean.sayHello("helo");
//            bean.goodMorning("helo");
//            bean.goodNight("helo");
//
//
//
//    }
//}
