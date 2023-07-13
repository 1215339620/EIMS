package eims.service.impl;

import eims.mapper.UserMapper;
import eims.pojo.*;
import eims.service.UserService;
import eims.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserServiceImpl implements UserService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public User login(String username, String password) {
        //获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取UserMapper
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //调用资源
        User user = userMapper.select(username, password);
        //释放资源
        sqlSession.close();
        return user;
    }

    /**
     * 注册
     *
     * @param user
     * @param emp
     * @return
     */
    @Override
    public boolean register(User user, Emp emp) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //判断用户是否存在
        User u = userMapper.selectByUsername(user.getUsername());
        if (u == null) {
            //用户不存在
            userMapper.add(user);
            userMapper.add1(emp);

            sqlSession.commit();
        }
        sqlSession.close();
        return u == null;
    }

    @Override
    public List<Emp> empSelectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<Emp> emps = userMapper.empSelectAll();
        sqlSession.close();
        return emps;
    }

    @Override
    public void addEmp(Emp emp) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.addEmp(emp);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void addUser(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.addUser(user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void delete1(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.delete1(user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void delete2(Emp emp) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.delete2(emp);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public Emp selectEmpById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        Emp emp = userMapper.selectEmpById(id);
        sqlSession.close();
        return emp;
    }

    @Override
    public void updateEmp(Emp emp) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.updateEmp(emp);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void updateUserDeptNo(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.updateUserDeptNo(user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public List<Dept> deptSelectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<Dept> depts = userMapper.deptSelectAll();
        sqlSession.close();
        return depts;
    }

    @Override
    public void addDept(Dept dept) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.addDept(dept);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void updateIsDeleteDept(Dept dept) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.updateIsDeleteDept(dept);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void updateAddIsDeleteDept(Dept dept) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.updateAddIsDeleteDept(dept);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public Dept selectDeptByDeptNo(int deptNo) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        Dept dept = userMapper.selectDeptByDeptNo(deptNo);
        sqlSession.close();
        return dept;
    }

    @Override
    public void updateDept(Dept dept) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.updateDept(dept);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public List<Sal> selectAllSal() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<Sal> sals = userMapper.selectAllSal();
        sqlSession.close();
        return sals;
    }

    @Override
    public Sal selectSalById(int eid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        Sal sal = userMapper.selectSalById(eid);
        sqlSession.close();
        return sal;
    }

    @Override
    public void updateSal(Sal sal) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.updateSal(sal);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void addSal(Sal sal) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.addSal(sal);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public List<Leave> selectAllLeaves() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<Leave> leaves = userMapper.selectAllLeaves();
        sqlSession.close();
        return leaves;
    }

    /**
     * 部门负责人查询本部门人员信息
     *
     * @param deptNO
     * @return
     */
    @Override
    public List<Emp> mainSelectEmpByDeptNo(int deptNO) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<Emp> emps = userMapper.mainSelectEmpByDeptNo(deptNO);
        sqlSession.close();
        return emps;
    }

    /**
     * 部门员工查询员工信息
     *
     * @return
     */
    @Override
    public List<Emp> mainSelectEmp() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<Emp> emps = userMapper.mainSelectEmp();
        sqlSession.close();
        return emps;
    }

    /**
     * 通过负责人id查询请假信息
     *
     * @param mid
     * @return
     */
    @Override
    public List<Leave> selectLeavesById(int mid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<Leave> leaves = userMapper.selectLeavesById(mid);
        sqlSession.close();
        return leaves;
    }

    @Override
    public void leaveIsAgreeServlet(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.leaveIsAgreeServlet(id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void leaveIsNotAgreeServlet(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.leaveIsNotAgreeServlet(id);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 通过eid查询薪资
     *
     * @param eid
     * @return
     */
    @Override
    public Sal selectSalaryById(int eid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        Sal sal = userMapper.selectSalaryById(eid);
        sqlSession.close();
        return sal;
    }

    @Override
    public List<Leave> empSelectLeavesById(int eid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<Leave> leaves = userMapper.empSelectLeavesById(eid);
        sqlSession.close();
        return leaves;
    }

    @Override
    public void addLeave(Leave leave) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.addLeave(leave);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public User selectUserById(int eid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectUserById(eid);
        sqlSession.close();
        return user;
    }

    @Override
    public void updateNewPassword(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.updateNewPassword(user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public List<User> selectAllPassword() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> user = userMapper.selectAllPassword();
        sqlSession.close();
        return user;
    }

    @Override
    public void changepsd2(int eid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.changepsd2(eid);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public List<Emp> searchName(String name) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<Emp> emp =userMapper.searchName(name);
        sqlSession.close();
        return emp;
    }

    @Override
    public List<Emp> searchEid(String eid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<Emp> emp =userMapper.searchEid(eid);
        sqlSession.close();
        return emp;
    }

    @Override
    public List<Emp> searchDept(String dept) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<Emp> emp =userMapper.searchDept(dept);
        sqlSession.close();
        return emp;
    }

    @Override
    public List<Emp> searchDeptName(String name,String dept) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<Emp> emp =userMapper.searchDeptName(name,dept);
        sqlSession.close();
        return emp;
    }

    @Override
    public List<Emp> searchAddress(String address) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<Emp> emp =userMapper.searchAddress(address);
        sqlSession.close();
        return emp;
    }

    @Override
    public List<Emp> searchDeptAddress(String address, String dept) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<Emp> emp =userMapper.searchDeptAddress(address,dept);
        sqlSession.close();
        return emp;
    }

    @Override
    public List<Emp> searchAge(String age) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<Emp> emp =userMapper.searchAge(age);
        sqlSession.close();
        return emp;
    }

    @Override
    public List<Emp> searchAgeName(String job, String name) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<Emp> emp =userMapper.searchAgeName(job,name);
        sqlSession.close();
        return emp;
    }

    @Override
    public List<Emp> searchAgeAddress(String age, String address) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<Emp> emp =userMapper.searchAgeAddress(age,address);
        sqlSession.close();
        return emp;
    }


}
