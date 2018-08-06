package cn.soft.news.filter;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.charset.StandardCharsets;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Hanoch
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = "/*")
public class EncodingFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {

        // 如果希望匿名内部类使用一个在外部定义的对象，那么编译器会要求其参数引用是final的
        final HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        // POST提交有效
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 解决：对HttpServletRequest接口的getParameter方法进行功能扩展，识别GET请求，可以使用动态代理!
        HttpServletRequest proxyRequest = (HttpServletRequest) Proxy.newProxyInstance(request.getClass().getClassLoader(),
                new Class[]{HttpServletRequest.class},
                new InvocationHandler() {

                    // args对象数组，代表被调用方法的参数
                    @Override
                    public Object invoke(Object proxy, Method method,
                                         Object[] args) throws Throwable {
                        String methodName = method.getName();
                        if ("getParameter".equals(methodName)) {
                            String value = request.getParameter(args[0]
                                    .toString());
                            String requestMethodName = request.getMethod();
                            if ("GET".equals(requestMethodName)) {
                                // 不为空 也不为 空字符串
                                if (value != null && !"".equals(value.trim())) {
                                    value = new String(value
                                            .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                                }
                            }
                            return value;
                        } else {
                            return method.invoke(request, args);
                        }
                    }

                });
        //放行 (执行下一个过滤器或者servlet)
        chain.doFilter(proxyRequest, response);
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

}
