package aop.spring.noInterfaceCall;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zk on 02/11/2017.
 */
public class ApologyImpl   {

    public void saySorry(String name) {
        System.out.println("ApologyImpl say sorry ! "+name);
    }


    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring.xml");
        ApologyImpl greetingProxy = (ApologyImpl) classPathXmlApplicationContext.getBean("greetingProxy");
        greetingProxy.saySorry("zk");
        Apology apoloyProxy = (Apology)greetingProxy;

        apoloyProxy.saySorry("zk");

    }
}
