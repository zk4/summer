package aop.cglib;

/**
 * Created by zk on 02/11/2017.
 */
public class Hello2 {
    public void say(String name){
        System.out.println("hello "+name);
    }

    public static void main(String[] args) {
        Hello2 proxy = CGlibProxy.getInstance().getProxy(Hello2.class);
        proxy.say("zk");

    }
}
