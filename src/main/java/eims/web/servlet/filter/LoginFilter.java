package eims.web.servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登陆验证过滤器
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req=(HttpServletRequest) request;

        //判断访问资源路径是否和登陆注册相关
        String[] urls={"/login.jsp","/imgs/","/css/","/loginServlet","/register.jsp","registerServlet","/checkCodeServlet"};
        //获取当前访问路径
        String url = req.getRequestURL().toString();
        for (String u:urls) {
            if (url.contains(u)){
                //放行
                chain.doFilter(request, response);
                return;
            }
        }
        //1.判断session是否有User
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");
        //2.判断user是否位null
        if (user!=null){
            //放行
            chain.doFilter(request, response);
        }else {
            //未登录，跳转登录页面
            req.setAttribute("login_msg","未登录用户，请先登录");
            req.getRequestDispatcher("/login.jsp").forward(req, response);
        }


    }
}
