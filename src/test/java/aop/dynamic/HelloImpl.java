package aop.dynamic;

/**
 * Created by zk on 02/11/2017.
 */

public class HelloImpl implements Hello {
    @Override
    public void say(String name) {
        System.out.println("hello! " + name);
    }

    public static void main(String[] args) {
        //必须有接口
        Hello o = new DynamicProxy(new HelloImpl()).getProxy();
        o.say("zk");
    }
}
