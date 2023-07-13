package eims.pojo;

public class User {
    private String name;
    private String username;
    private String password;
    private int eid;
    private int isRoot;
    private int isDelete;
    private int deptNo;
    private String oldpassword;
    private String newpassword;
    private String newpassword2;

    public User() {
    }

    public User(String name, String username, String password, int eid, int isRoot, int isDelete, int deptNo, String oldpassword, String newpassword, String newpassword2) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.eid = eid;
        this.isRoot = isRoot;
        this.isDelete = isDelete;
        this.deptNo = deptNo;
        this.oldpassword = oldpassword;
        this.newpassword = newpassword;
        this.newpassword2 = newpassword2;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getIsRoot() {
        return isRoot;
    }

    public void setIsRoot(int isRoot) {
        this.isRoot = isRoot;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getNewpassword2() {
        return newpassword2;
    }

    public void setNewpassword2(String newpassword2) {
        this.newpassword2 = newpassword2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", eid=" + eid +
                ", isRoot=" + isRoot +
                ", isDelete=" + isDelete +
                ", deptNo=" + deptNo +
                ", oldpassword='" + oldpassword + '\'' +
                ", newpassword='" + newpassword + '\'' +
                ", newpassword2='" + newpassword2 + '\'' +
                '}';
    }
}
