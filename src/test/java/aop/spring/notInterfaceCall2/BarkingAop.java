//package aop.spring.notInterfaceCall2;
//
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.DeclareParents;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.stereotype.Component;
//
///**
// * Created by zk on 02/11/2017.
// */
//@Aspect
//@Component
//public class BarkingAop {
//
//    @DeclareParents(value = "aop.spring.notInterfaceCall2.BarkingImpl", defaultImpl =BarkingImplDefault.class)
//    private Barking barking;
//
//    public static void main(String[] args) {
//        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring.xml");
//        BarkingImpl bean = classPathXmlApplicationContext.getBean(BarkingImpl.class);
//
//        bean.barking();
//
//        Barking bean1 = (Barking) bean;
//        bean1.barking();
//
//    }
//}
