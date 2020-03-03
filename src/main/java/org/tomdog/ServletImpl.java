package org.tomdog;

/**
 * Created by zk on 30/05/2018.
 */
public class ServletImpl extends Servlet {
    @Override
    public void service(Request req, Response resp) {
              resp.appendContent("<h1>hello,response</h1>")
                .writeback();
    }
}
