package aop.spring.aspectJ;

import org.springframework.stereotype.Component;

/**
 * Created by zk on 02/11/2017.
 */
@Component
public class GreetingImpl implements Greeting {
    @Override
    public void sayHello(String name) {
        System.out.println("hello"+name);
    }

    @Override
    public void goodMorning(String name) {
        System.out.println("good morning"+name);
    }

    @Override
    public void goodNight(String name) {
        System.out.println("good night"+ name);
    }

}