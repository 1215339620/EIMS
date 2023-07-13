package eims.web.servlet;

import eims.pojo.User;
import eims.service.UserService;
import eims.service.impl.UserServiceImpl;
import eims.util.MD5BU;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private UserServiceImpl userService=new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取输入的账号密码
        String username=request.getParameter("username");
        String password = request.getParameter("password");
        //复选框数据
        String remember = request.getParameter("remember");
        //调用service查询
        User user = userService.login(username, MD5BU.md5(password));
        if (user!=null){
            //将登陆成功的user对象，存储到session中
            //用户选择记住我
            if ("1".equals(remember)){
                //发送Cookie
                //回显
                //创建cookie
                Cookie c_username=new Cookie("username",username);
                Cookie c_password=new Cookie("password",password);
                //设置存活时间   一周
                c_username.setMaxAge(60*60*24*7);
                c_password.setMaxAge(60*60*24*7);
                //发送
                response.addCookie(c_username);
                response.addCookie(c_password);

            }

            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            if (user.getIsRoot() ==2){
                response.sendRedirect("/works/backstage.jsp");
            }else if (user.getIsRoot()==1){
                response.sendRedirect("/works/main-1.jsp");
            }else {
                response.sendRedirect("/works/main.jsp");
            }
        }else {
            //登录失败
            request.setAttribute("login_msg","用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
