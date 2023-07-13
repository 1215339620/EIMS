package eims.web.servlet;

import com.alibaba.fastjson.JSON;
import eims.pojo.*;
import eims.service.impl.UserServiceImpl;
import eims.util.MD5BU;

import javax.crypto.spec.PSource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@WebServlet("/works/*")
public class UserServlet extends BaseServlet {
    private UserServiceImpl userService = new UserServiceImpl();

    //查询所有的员工信息
    public void selectAllEmpServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service查询
        List<Emp> emps = userService.empSelectAll();
        //将集合转换为JSON数据
        String jsonString = JSON.toJSONString(emps);
        //响应数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //添加员工信息
    public void addEmpServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.接受数据，request.getParameter 不能接受JSON数据
        //获取请求体数据
        BufferedReader br = request.getReader();
        String readLine = br.readLine();
        //将json字符串转化为Java对象
        Emp emp = JSON.parseObject(readLine, Emp.class);
        User user = JSON.parseObject(readLine, User.class);
        String name = emp.getName();
        String phone =emp.getPhone();
        String username=user.getUsername();
        String password= user.getPassword();
        if (Objects.equals(username, "")){
            response.getWriter().write("user");
        }else if (username.trim().equals("")){
            response.getWriter().write("user");
        } else if (Objects.equals(name, "")) {
            response.getWriter().write("name");
        } else if (phone.length()!=11) {
            response.getWriter().write("phone");
        } else if (phone.equals("")) {
            response.getWriter().write("phone1");
        } else if (Objects.equals(password, "")) {
            response.getWriter().write("password");
        } else {
            //调用service添加
            user.setPassword(MD5BU.md5(password));
            userService.addUser(user);
            System.out.println(user);
            userService.addEmp(emp);
            //响应成功标识
            response.getWriter().write("success");
        }
    }

    //删除员工信息
    public void deleteServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Emp emp = new Emp();
        User user = new User();
        emp.setEid(Integer.parseInt(id));
        user.setEid(Integer.parseInt(id));
        userService.delete1(user);
        userService.delete2(emp);
        response.getWriter().write("deleteSuccess");
    }

    //通过id查询员工信息
    public void selectEmpByIdServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Emp emp = userService.selectEmpById(Integer.parseInt(id));
        //将集合转换为JSON数据
        String jsonString = JSON.toJSONString(emp);
        //响应数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //更新员工信息
    public void updateEmpServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受数据，request.getParameter 不能接受JSON数据
        //获取请求体数据
        BufferedReader br = request.getReader();
        String readLine = br.readLine();
        //将json字符串转化为Java对象
        Emp emp = JSON.parseObject(readLine, Emp.class);
        //调用service添加
        userService.updateEmp(emp);
        int eid = emp.getEid();
        int deptno = emp.getDeptno();
        User user = JSON.parseObject(readLine, User.class);
        System.out.println(user);
        userService.updateUserDeptNo(user);

        response.getWriter().write("updateEmpSuccess");
    }

    //查询所有部门信息
    public void selectAllDeptServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Dept> depts = userService.deptSelectAll();
        //将集合转换为JSON数据
        String jsonString = JSON.toJSONString(depts);
        //响应数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //添加部门信息
    public void addDeptServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受数据，request.getParameter 不能接受JSON数据
        //获取请求体数据
        BufferedReader br = request.getReader();
        String readLine = br.readLine();
        //将json字符串转化为Java对象
        Dept dept = JSON.parseObject(readLine, Dept.class);
        //调用service添加
        System.out.println(dept);
        userService.addDept(dept);
        //响应成功标识
        response.getWriter().write("addDeptSuccess");
    }

    //通过部门编号查询部门信息
    public void selectDeptByIdServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deptNo = request.getParameter("deptNo");
        Dept dept = userService.selectDeptByDeptNo(Integer.parseInt(deptNo));
        //将集合转换为JSON数据
        String jsonString = JSON.toJSONString(dept);
        //响应数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //修改部门信息
    public void updateDeptServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受数据，request.getParameter 不能接受JSON数据
        //获取请求体数据
        BufferedReader br = request.getReader();
        String readLine = br.readLine();
        //将json字符串转化为Java对象
        Dept dept = JSON.parseObject(readLine, Dept.class);
        System.out.println(dept);
        //调用service添加
        userService.updateDept(dept);
        response.getWriter().write("updateDeptSuccess");
    }

    //逻辑删除部门信息
    public void deleteDeptServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deptNo = request.getParameter("deptNo");
        Dept dept = new Dept();
        dept.setDeptNo(Integer.parseInt(deptNo));
        userService.updateIsDeleteDept(dept);
        response.getWriter().write("deleteDeptSuccess");
    }

    //逻辑删除后添加部门信息
    public void deleteDeptAfterAddServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deptNo = request.getParameter("deptNo");
        Dept dept = new Dept();
        if (userService.selectDeptByDeptNo(Integer.parseInt(deptNo)) != null) {
            dept.setDeptNo(Integer.parseInt(deptNo));
            userService.updateAddIsDeleteDept(dept);
            response.getWriter().write("deleteDeptAfterAddSuccess");
        } else {
            response.getWriter().write("isNOtExist");
        }
    }

    //查询所有职工工资信息
    public void selectAllSalServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Sal> sals = userService.selectAllSal();
        //将集合转换为JSON数据
        String jsonString = JSON.toJSONString(sals);
        //响应数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //通过员工id查询工资信息
    public void selectSalByIdServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eid = request.getParameter("eid");
        Sal sal = userService.selectSalById(Integer.parseInt(eid));
        Emp emp = userService.selectEmpById(Integer.parseInt(eid));
        //将集合转换为JSON数据
        String jsonString = JSON.toJSONString(sal);
        String jsonString1 = JSON.toJSONString(emp);

        if (jsonString.equals("null")) {
            jsonString = "{\"eid\":" + emp.getEid() + ",\"salary\":0}";
        }
        int index1 = jsonString1.indexOf("}");
        int index2 = jsonString.lastIndexOf("{");
        String s = jsonString.substring(index2 + 1);
        String s1 = jsonString1.substring(0, index1);
        String json = s1 + "," + s;
        //响应数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(json);
    }

    //修改员工薪资
    public void updateSalServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受数据，request.getParameter 不能接受JSON数据
        //获取请求体数据
        BufferedReader br = request.getReader();
        String readLine = br.readLine();
        //将json字符串转化为Java对象
        Sal sal = JSON.parseObject(readLine, Sal.class);
        System.out.println(sal);
        //调用service添加
        userService.updateSal(sal);
        response.getWriter().write("updateSalSuccess");
    }

    //当修改的员工薪资不存在时添加该员工的信息到数据库中
    public void addSalServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.接受数据，request.getParameter 不能接受JSON数据
        //获取请求体数据
        BufferedReader br = request.getReader();
        String readLine = br.readLine();
        //将json字符串转化为Java对象
        Sal sal = JSON.parseObject(readLine, Sal.class);
        //调用service添加
        System.out.println(sal);
        userService.addSal(sal);
        //响应成功标识
        response.getWriter().write("addSalSuccess");
    }

    public void selectSalByIdServlet2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eid = request.getParameter("eid");
        if (userService.selectSalById(Integer.parseInt(eid)) == null) {
            //响应数据
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().write("addSalById");
        } else {
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().write("isExist");
        }
    }

    public void selectAllLeavesServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Leave> leaves = userService.selectAllLeaves();
        //将集合转换为JSON数据
        String jsonString = JSON.toJSONString(leaves);
        //响应数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //    //查询所有的员工信息
//    public void mainSelectEmpServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //调用service查询
//        List<Emp> emps = userService.mainSelectEmp();
//        //将集合转换为JSON数据
//        String jsonString = JSON.toJSONString(emps);
//        //响应数据
//        response.setContentType("text/json;charset=utf-8");
//        response.getWriter().write(jsonString);
//    }
    //通过部门编号查询员工信息
    public void mainSelectEmpByDeptNoServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deptNo = request.getParameter("deptNo");
        //调用service查询
        List<Emp> emps = userService.mainSelectEmpByDeptNo(Integer.parseInt(deptNo));
        //将集合转换为JSON数据
        String jsonString = JSON.toJSONString(emps);
        //响应数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //通过负责人编号查询请假信息
    public void selectLeavesByIdServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eid = request.getParameter("eid");
        List<Leave> leaves = userService.selectLeavesById(Integer.parseInt(eid));
        //将集合转换为JSON数据
        String jsonString = JSON.toJSONString(leaves);
        //响应数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void leaveIsAgreeServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        System.out.println(id);
        userService.leaveIsAgreeServlet(Integer.parseInt(id));
        response.getWriter().write("isAgree");
    }

    public void leaveIsNotAgreeServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        System.out.println(id);
        userService.leaveIsNotAgreeServlet(Integer.parseInt(id));
        response.getWriter().write("isNotAgree");
    }

    //通过id查询自己的薪资信息
    public void selectSalaryByIdServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eid = request.getParameter("eid");
        Sal sal = userService.selectSalaryById(Integer.parseInt(eid));
        //将集合转换为JSON数据
        String jsonString = JSON.toJSONString(sal);
        //响应数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //通过id查询自己的请假信息
    public void empSelectLeavesByIdServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eid = request.getParameter("eid");
        List<Leave> leaves = userService.empSelectLeavesById(Integer.parseInt(eid));
        //将集合转换为JSON数据
        String jsonString = JSON.toJSONString(leaves);
        //响应数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //添加请假信息
    public void addLeaveServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deptNo = request.getParameter("deptNo");
        //1.接受数据，request.getParameter 不能接受JSON数据
        //获取请求体数据
        BufferedReader br = request.getReader();
        String readLine = br.readLine();
        //将json字符串转化为Java对象
        Leave leave = JSON.parseObject(readLine, Leave.class);
        //调用service添加
        userService.addLeave(leave);
        leave.setDeptNo(Integer.parseInt(deptNo));
        System.out.println(leave);
        System.out.println(deptNo);
        //响应成功标识
        response.getWriter().write("addLeaveSuccess");
    }

    //通过id修改密码
    public void changePasswordServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求体数据
        BufferedReader br = request.getReader();
        String readLine = br.readLine();
        //将json字符串转化为Java对象
        User user1 = JSON.parseObject(readLine, User.class);
        int eid = user1.getEid();
        String oldpassword = user1.getOldpassword();
        String newpassword = user1.getNewpassword();
        String newpassword2 = user1.getNewpassword2();
        User user = userService.selectUserById(eid);
        if (!oldpassword.equals(user.getPassword())) {
            response.getWriter().write("err");
        } else if (!newpassword2.equals(newpassword)) {
            response.getWriter().write("new_err");
        } else {
            user1.setPassword(MD5BU.md5(newpassword));
            userService.updateNewPassword(user1);
            //响应数据
            response.getWriter().write("changeSuccess");
        }
    }

    public void selectAllPasswordServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userService.selectAllPassword();
        //将集合转换为JSON数据
        String jsonString = JSON.toJSONString(users);
        //响应数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void changepsd2Servlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eid = request.getParameter("eid");
        userService.changepsd2(Integer.parseInt(eid));
        response.getWriter().write("success");
    }

    public void searchName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        //解决中文乱码问题
        name = new String(name.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        List<Emp> emps = userService.searchName(name);
        //将集合转换为JSON数据
        String jsonString = JSON.toJSONString(emps);
        //响应数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void searchEid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eid = request.getParameter("eid");
        //解决中文乱码问题
        List<Emp> emps = userService.searchEid(eid);
        //将集合转换为JSON数据
        String jsonString = JSON.toJSONString(emps);
        //响应数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void searchDept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dept = request.getParameter("dept");
        //解决中文乱码问题
        dept = new String(dept.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        List<Emp> emps = userService.searchDept(dept);
        //将集合转换为JSON数据
        String jsonString = JSON.toJSONString(emps);
        //响应数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void searchDeptName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dept = request.getParameter("dept");
        //解决中文乱码问题
        dept = new String(dept.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String name = request.getParameter("name");
        //解决中文乱码问题
        name = new String(name.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        List<Emp> emps = userService.searchDeptName(name, dept);
        //将集合转换为JSON数据
        String jsonString = JSON.toJSONString(emps);
        //响应数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void searchAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = request.getParameter("address");
        //解决中文乱码问题
        address = new String(address.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        List<Emp> emps = userService.searchAddress(address);
        //将集合转换为JSON数据
        String jsonString = JSON.toJSONString(emps);
        //响应数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void searchDeptAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = request.getParameter("address");
        //解决中文乱码问题
        address = new String(address.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String dept = request.getParameter("dept");
        //解决中文乱码问题
        dept = new String(dept.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        List<Emp> emps = userService.searchDeptAddress(address, dept);
        //将集合转换为JSON数据
        String jsonString = JSON.toJSONString(emps);
        //响应数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void searchAge(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String age = request.getParameter("age");
        //解决中文乱码问题
        age = new String(age.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        List<Emp> emps = userService.searchAge(age);
        //将集合转换为JSON数据
        String jsonString = JSON.toJSONString(emps);
        //响应数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void searchAgeName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String age = request.getParameter("age");
        //解决中文乱码问题
        age = new String(age.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String name = request.getParameter("name");
        //解决中文乱码问题
        name = new String(name.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        List<Emp> emps = userService.searchAgeName(age, name);
        //将集合转换为JSON数据
        String jsonString = JSON.toJSONString(emps);
        //响应数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void searchAgeAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String age = request.getParameter("age");
        //解决中文乱码问题
        age = new String(age.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String address = request.getParameter("address");
        //解决中文乱码问题
        address = new String(address.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        List<Emp> emps = userService.searchAgeAddress(age, address);
        //将集合转换为JSON数据
        String jsonString = JSON.toJSONString(emps);
        //响应数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}
