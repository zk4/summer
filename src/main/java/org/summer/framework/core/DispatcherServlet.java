package org.summer.framework.core;

import org.summer.framework.config.ConfigHelper;
import org.summer.framework.controller.Data;
import org.summer.framework.controller.Param;
import org.summer.framework.controller.View;
import org.summer.framework.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

/**
 * Created by zk on 02/11/2017.
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        HelperLoader.init();

        ServletContext servletContext = servletConfig.getServletContext();

        ServletRegistration jsp = servletContext.getServletRegistration("jsp");
        String appJspPath = ConfigHelper.getAppJspPath();
        jsp.addMapping(appJspPath + "*");


        ServletRegistration aDefault = servletContext.getServletRegistration("default");

        Map<String, ? extends ServletRegistration> servletRegistrations = servletContext.getServletRegistrations();
        servletRegistrations.forEach((s, o) -> {
            System.out.println("key:"+s);
            System.out.println("name:"+o.getName());
            Collection<String> mappings = o.getMappings();
            for (String mapping : mappings) {
                System.out.println("--"+mapping);
            }
        });
    }



    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestMethod = req.getMethod().toLowerCase();
        String requestPath = req.getPathInfo();

        Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
        if (handler != null) {
            Class<?> controllerClass = handler.getControllerClass();
            Object bean = BeanHelper.getBean(controllerClass);

            Map<String, String[]> paramMap = req.getParameterMap();


            //todo 将parameter body转换为对应的 Action 参数
//            String body = CodecUtil.decodeURL(StreamUtil.getString(req.getInputStream()));
//            ObjectMapper objectMapper = new ObjectMapper();
//            Customer c = objectMapper.readValue(req.getInputStream(), Customer.class);

            Param param = new Param(paramMap);
            Method actionMethod =
                    handler.getActionMethod();

            Object result = ReflectionUtil.invokeMethod(bean, actionMethod, param);

            if (result instanceof View) {
                View view = (View) result;
                String path = view.getPath();
                if (path != null && path.length() != 0) {
                    if (path.startsWith("/")) {
                        resp.sendRedirect(req.getContextPath() + path);
                    } else {
                        Map<String, Object> model =
                                view.getModel();
                        for (Map.Entry<String, Object> entry : model.entrySet()) {
                            req.setAttribute(entry.getKey(), entry.getValue());
                        }
                        req.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(req, resp);
                    }
                }
            }else if (result instanceof Data){
                Data data = (Data) result;
                Object model = data.getModel();
                if (model != null) {
                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");
                    PrintWriter writer = resp.getWriter();
                    String s = JsonUtil.toJson(model);
                    writer.write(s);
                    writer.flush();
                    writer.close();
                }
            }

        }else {
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.write("没找到映射:"+requestMethod+" "+requestPath);
            writer.flush();
            writer.close();
        }
    }
}
