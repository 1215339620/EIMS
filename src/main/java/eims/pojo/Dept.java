package eims.pojo;

public class Dept {
    private int id;
    private int deptNo;
    private String name;
    private String deptName;
    private String introduction;
    private int isDelete;

    public Dept() {
    }

    public Dept(int id, int deptNo, String name, String deptName, String introduction, int isDelete) {
        this.id = id;
        this.deptNo = deptNo;
        this.name = name;
        this.deptName = deptName;
        this.introduction = introduction;
        this.isDelete = isDelete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", deptNo=" + deptNo +
                ", name='" + name + '\'' +
                ", deptName='" + deptName + '\'' +
                ", introduction='" + introduction + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }
}
