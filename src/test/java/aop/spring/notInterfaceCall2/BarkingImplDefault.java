package aop.spring.notInterfaceCall2;

/**
 * Created by zk on 02/11/2017.
 */
public class BarkingImplDefault implements Barking {
    @Override
    public void barking() {
        System.out.println("default barking");
    }
}
