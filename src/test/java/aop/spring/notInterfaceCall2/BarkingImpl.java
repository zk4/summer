package aop.spring.notInterfaceCall2;

import org.springframework.stereotype.Component;

/**
 * Created by zk on 02/11/2017.
 */
@Component
public class BarkingImpl   {

    public void barking() {
        System.out.println(" BarkingImpl barking");
    }
}
