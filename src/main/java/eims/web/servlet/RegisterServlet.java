package eims.web.servlet;

import eims.pojo.Emp;
import eims.pojo.User;
import eims.service.impl.UserServiceImpl;
import eims.util.MD5BU;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    private UserServiceImpl userService=new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //获取输入的账号、密码、姓名
        String username=request.getParameter("username");
        String password = request.getParameter("password");
        String name=request.getParameter("name");
        String phone = request.getParameter("phone");

        if (username==""){
            request.setAttribute("username_msg","用户名不能为空");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            return;
        } else if (username.trim().equals("")) {
            request.setAttribute("username_msg","用户名不能为空");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            return;
        }
        if (phone.length()!=11){
            request.setAttribute("phone_msg","手机号码需为11位");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            return;
        }
        //获取输入验证码
        String checkCode = request.getParameter("checkCode");
        //获取生成的验证码
        HttpSession session = request.getSession();
        String checkCodeGet = (String) session.getAttribute("checkCodeGet");

        User user = new User();
        Emp emp = new Emp();
        user.setUsername(username);
        user.setPassword(MD5BU.md5(password));
        emp.setName(name);
        emp.setPhone(phone);
        emp.setAddress("河南");
        emp.setDeptno(8801);
        emp.setJob("后端开发");
        emp.setEmail(phone+"@qq.com");


        //equalsIgnoreCase()忽略大小写注册
        if (!checkCodeGet.equalsIgnoreCase(checkCode)){
            //验证码错误，不允许注册
            request.setAttribute("checkCode_msg","验证码输入错误");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            return;
        }

        boolean register = userService.register(user, emp);
        if (register){
            //注册成功
            request.setAttribute("register_msg","注册成功，请登录");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }else {
            request.setAttribute("register_msg","用户已存在");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
