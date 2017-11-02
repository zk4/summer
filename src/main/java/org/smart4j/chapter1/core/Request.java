package org.smart4j.chapter1.core;

/**
 * Created by zk on 02/11/2017.
 */
public class Request {
    String requestMethod;
    String requestPath;

    public Request(String requestMethod, String requestPath) {
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;

        Request request = (Request) o;

        if (getRequestMethod() != null ? !getRequestMethod().equals(request.getRequestMethod()) : request.getRequestMethod() != null)
            return false;
        return getRequestPath() != null ? getRequestPath().equals(request.getRequestPath()) : request.getRequestPath() == null;
    }

    @Override
    public int hashCode() {
        int result = getRequestMethod() != null ? getRequestMethod().hashCode() : 0;
        result = 31 * result + (getRequestPath() != null ? getRequestPath().hashCode() : 0);
        return result;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }
}
