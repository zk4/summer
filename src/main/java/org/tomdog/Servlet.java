package org.tomdog;

/**
 * Created by zk on 30/05/2018.
 */
abstract class Servlet {
    public abstract void service(Request req, Response resp);
}
