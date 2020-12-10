package servlet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "FilterNumber", urlPatterns = "/arg")
public class FilterNumber implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("FilterNumber called");
        HttpServletRequest request = (HttpServletRequest) req;
        int num1 = Integer.parseInt(request.getParameter("num1"));

        if (num1 > 1){
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) {

    }

}
