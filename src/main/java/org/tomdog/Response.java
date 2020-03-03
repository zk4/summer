package org.tomdog;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by zk on 29/05/2018.
 */

public class Response {

    String header = "HTTP/1.1 200 OK\r\n" +
            "Content-Type: text/html; charset=utf-8\r\n" +
            "Server: tomdog/7.5\r\n" +
            "Date: Tue, 29 May 2018 11:56:14 GMT\r\n";

    String content="";
    OutputStream outputStream;



    public Response setContent(String content) {
        this.content = content;
        return this;
    }

    public Response appendContent(String content) {
        this.content += content;
        return this;
    }

    public void writeback() {
        String full =header+
                "Content-Length: " + content.getBytes().length + "\r\n\r\n" + content;

        try {
            outputStream.write(full.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }
}
