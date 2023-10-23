package apex.legend.laoqu.filter;

import apex.legend.laoqu.common.R;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    //路径匹配器
    public static AntPathMatcher PATH_MATCHER = new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response =(HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        String[] urls = new String[]{"/employee/login",
                                    "/employee/logout",
                                    "/backend/**",
                                    "/front/**" };
        boolean check = check(urls,requestURI);
        if(check){
            filterChain.doFilter(request,response);
            return;
        }
        if(request.getSession().getAttribute("employee")!=null){
            filterChain.doFilter(request,response);
            return;
        }
        //如果未登录则返回未登录结果。通过输出流方式向客户端页面响应数据
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
    }
    public boolean check(String[] urls,String request){
        for(String url :urls){
            boolean match = PATH_MATCHER.match(url,request);
            if (match) return true;
        }
        return false;
    }
}
