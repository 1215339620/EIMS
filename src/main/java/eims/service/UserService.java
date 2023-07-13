package eims.service;

import eims.pojo.*;

import java.util.List;

public interface UserService {
    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    User login(String username,String password);

    /**
     * 注册
     * @param user
     * @param emp
     * @return
     */
    boolean register(User user, Emp emp);

    /**
     * 查询所有职工信息
     * @return
     */
    List<Emp> empSelectAll();

    /**
     * 添加员工
     * @param emp
     */
    void addEmp(Emp emp);
    void addUser(User user);

    /**
     * 删除员工信息
     * @param user
     */
    void delete1(User user);

    /**
     * 删除员工信息
     * @param emp
     */
    void delete2(Emp emp);

    /**
     * 根据id查询员工信息
     * @param id
     */
    Emp selectEmpById(int id);
    /**
     * 修改员工信息
     * @param emp
     */
    void updateEmp(Emp emp);
    /**
     * 修改员工信息的同时也修改User表中的deptNo
     * @param user
     */
    void updateUserDeptNo(User user);

    /**
     * 查询所有部门信息
     * @return
     */
    List<Dept> deptSelectAll();

    /**
     * 添加部门信息
     * @param dept
     */
    void addDept(Dept dept);

    /**
     * 逻辑删除部门信息
     * @param dept
     */
    void updateIsDeleteDept(Dept dept);

    /**
     * 逻辑删除后增加
     * @param dept
     */
    void updateAddIsDeleteDept(Dept dept);

    /**
     * 判断用户是否存在
     * @param deptNo
     * @return
     */
    Dept selectDeptByDeptNo(int deptNo);

    /**
     * 修改部门数据
     * @param dept
     */
    void updateDept(Dept dept);

    /**
     * 查询所有人员的薪资
     * @return
     */
    List<Sal> selectAllSal();

    /**
     * 通过id查询员工薪资
     * @param eid
     * @return
     */
    Sal selectSalById(int eid);

    /**
     * 修改薪资
     * @param sal
     */
    void updateSal(Sal sal);

    /**
     * 在薪资表中添加信息
     * @param sal
     */
    void addSal(Sal sal);

    /**
     * 查询所有请假信息
     * @return
     */
    List<Leave> selectAllLeaves();

    List<Emp> mainSelectEmp();

    List<Emp> mainSelectEmpByDeptNo(int deptNO);

    List<Leave> selectLeavesById(int mid);

    void leaveIsAgreeServlet(int eid);

    void leaveIsNotAgreeServlet(int eid);


    Sal selectSalaryById(int eid);

    /**
     * 员工通过id查询请假信息
     * @param eid
     * @return
     */
    List<Leave> empSelectLeavesById(int eid);

    void addLeave(Leave leave);

    User selectUserById(int eid);

    void updateNewPassword(User user);

    List<User> selectAllPassword();

    void changepsd2(int eid);

    List<Emp> searchName(String name);

    List<Emp> searchEid(String eid);

    List<Emp> searchDept(String dept);

    List<Emp> searchDeptName(String dept, String name);

    List<Emp> searchAddress(String address);

    List<Emp> searchDeptAddress(String address, String dept);

    List<Emp> searchAge(String age);

    List<Emp> searchAgeName(String job, String name);

    List<Emp> searchAgeAddress(String age, String address);
}
