package org.summer.framework.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.summer.framework.annotation.Transaction;
import org.summer.framework.aop.Proxy;
import org.summer.framework.aop.ProxyChain;
import org.summer.framework.util.DataBaseHelper;

import java.lang.reflect.Method;
import java.sql.SQLException;

/**
 * Created by zk on 03/11/2017.
 */
public class TransactionProxy  implements Proxy{
    private static final Logger LOGGER = LoggerFactory.getLogger(DataBaseHelper.class);

    private static final ThreadLocal<Boolean> FLAG_HOLDER= new ThreadLocal<Boolean>(){
        @Override
        protected Boolean initialValue() {
            return false;
        }
    };

    @Override
    public Object doProxy(ProxyChain proxyChain)  {
        Boolean flag = FLAG_HOLDER.get();
        Object result=null;
        Method method = proxyChain.getTargetMethod();
        if (!flag  && method.isAnnotationPresent(Transaction.class)) {
            FLAG_HOLDER.set(true);
            try {
                DataBaseHelper.beginTransaction();
                LOGGER.debug("begin transaction");
                result = proxyChain.doProxyChain();
                DataBaseHelper.commitdTransaction();
                LOGGER.debug("commit transaction");
            } catch (SQLException e) {
                LOGGER.debug("SQLException error:",e);
                try {
                    DataBaseHelper.rollbackTransaction();
                } catch (SQLException e1) {
                    LOGGER.debug("proxyChain error:",e1);
                }
            } catch (Throwable throwable) {
                LOGGER.debug("proxyChain error:",throwable);
            }finally {
                FLAG_HOLDER.remove();
            }

        }

        return  result;
    }
}
