package com.ljn.xiaoruiserver.filter;

/**
 * Created by 12390 on 2017/9/25.
 */
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class RequestFilter implements Filter {

    public static final ThreadLocal<HttpServletRequest> requests = new ThreadLocal<HttpServletRequest>();

    @Override
    public void doFilter(ServletRequest request, ServletResponse arg1, FilterChain chain)
            throws IOException, ServletException {
        try{
            request.setAttribute("localThreadResponse", arg1);

            requests.set((HttpServletRequest)request);

            chain.doFilter(request, arg1);
        }
        catch(Exception e){
            System.out.print(e+"@xfilter");
        }

    }
    public void init(FilterConfig config) throws ServletException {}

    public void destroy() {}
}
