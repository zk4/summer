package threadLocal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zk on 03/11/2017. 释放有问题.会有内存泄漏
 * http://vence.github.io/2016/05/28/threadlocal-info/
 */
public class MyThreadLocal<T> {
    //为什么要用synchronized map???
    private Map<Thread, T> container = Collections.synchronizedMap(new HashMap<Thread, T>());

    public void set(T value) {
        container.put(Thread.currentThread(), value);
    }

    public T get() {
        Thread thread = Thread.currentThread();
        T value = container.get(thread);
        if (value == null && !container.containsKey(thread)) {
            value = initialValue();
            container.put(thread, value);

        }
        return value;
    }

    protected T initialValue() {
        return null;
    }

    public void remove() {
        container.remove(Thread.currentThread());
    }
}
