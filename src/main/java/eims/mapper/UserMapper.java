package eims.mapper;

import eims.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    User select(@Param("username") String username,@Param("password") String password);

    /**
     * 注册时判断是否已存在
     * @param username
     * @return
     */
    User selectByUsername(String username);

    /**
     * 注册用户
     * @param user
     */
    void add(User user);
    void add1(Emp emp);

    /**
     * 查询所有
     * @return
     */
    List<Emp> empSelectAll();

    /**
     * 添加员工信息
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
     * @return
     */
    Emp selectEmpById(int id);
    /**
     * 修改员工信息
     * @param emp
     */
    void updateEmp(Emp emp);
    void updateUserDeptNo(User user);

    /**
     * 查询所有部门
     * @return
     */
    List<Dept> deptSelectAll();

    /**
     * 添加部门信息
     * @param dept
     */
    void addDept(Dept dept);

    /**
     * 逻辑删除部门
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
     * 通过部门编号查询部门
     * @param deptNo
     * @return
     */
    Dept selectDeptByDeptNo(int deptNo);

    /**
     * 修改部门信息
     * @param dept
     */
    void updateDept(Dept dept);

    /**
     * 查询所有人员的薪资
     * @return
     */
    List<Sal> selectAllSal();

    Sal selectSalById(int eid);

    void updateSal(Sal sal);

    /**
     * 添加薪资
     * @param sal
     */
    void addSal(Sal sal);

    /**
     * 查询所有请假信息
     * @return
     */
    List<Leave> selectAllLeaves();

    /**
     * 部门负责人查询部门人员
     * @return
     */
    List<Emp> mainSelectEmp();

    /**
     * 负责人查询本部门人员信息
     * @param deptNO
     * @return
     */
    List<Emp> mainSelectEmpByDeptNo(int deptNO);



    /**
     * 通过负责人eid查询请假信息
     * @param mid
     */
    List<Leave> selectLeavesById(int mid);

    void leaveIsAgreeServlet(int id);

    void leaveIsNotAgreeServlet(int eid);

    /**
     * 通过id查询薪资信息
     * @param eid
     * @return
     */
    Sal selectSalaryById(int eid);
    /**
     * 通过id查询请假信息
     * @param eid
     * @return
     */
    List<Leave> empSelectLeavesById(int eid);

    void addLeave(Leave leave);

    User selectUserById(int eid);

    void updateNewPassword(User user);

    /**
     * 查询员工账号密码
     * @return
     */
    List<User> selectAllPassword();

    void changepsd2(int eid);

    List<Emp> searchName(String name);

    List<Emp> searchEid(String eid);

    List<Emp> searchDept(String dept);

    List<Emp> searchDeptName(String name,String dept);

    List<Emp> searchAddress(String address);

    List<Emp> searchDeptAddress(String address, String dept);

    List<Emp> searchAge(String age);

    List<Emp> searchAgeName(@Param("age") String age,@Param("name") String name);

    List<Emp> searchAgeAddress(String age, String address);
}
