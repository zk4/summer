package org.tomdog;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zk on 30/05/2018.
 */
public class Request {

    BufferedInputStream bin;
    public Request(InputStream  stream) {

        bin = new BufferedInputStream(stream);
        byte[] buf = null;
        try {
            buf = new byte[10240];
            int len = bin.read(buf);
            if (len <= 0) {
                throw new RuntimeException();
            }
            System.out.println(new String(buf,0,len));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
