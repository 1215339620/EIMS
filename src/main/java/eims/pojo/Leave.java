package eims.pojo;

public class Leave {
    private int id;
    private int eid;
    private int mid;
    private String eName;
    private String mName;
    private String deptName;
    private String begin;
    private String end;
    private String lTime;
    private String reason;
    private int isAgree;
    private int deptNo;

    public Leave() {
    }

    public Leave(int id, int eid, int mid, String eName, String mName, String deptName, String begin, String end, String lTime, String reason, int isAgree, int deptNo) {
        this.id = id;
        this.eid = eid;
        this.mid = mid;
        this.eName = eName;
        this.mName = mName;
        this.deptName = deptName;
        this.begin = begin;
        this.end = end;
        this.lTime = lTime;
        this.reason = reason;
        this.isAgree = isAgree;
        this.deptNo = deptNo;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getlTime() {
        return lTime;
    }

    public void setlTime(String lTime) {
        this.lTime = lTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(int isAgree) {
        this.isAgree = isAgree;
    }

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Leave{" +
                "id=" + id +
                ", eid=" + eid +
                ", mid=" + mid +
                ", eName='" + eName + '\'' +
                ", mName='" + mName + '\'' +
                ", deptName='" + deptName + '\'' +
                ", begin='" + begin + '\'' +
                ", end='" + end + '\'' +
                ", lTime='" + lTime + '\'' +
                ", reason='" + reason + '\'' +
                ", isAgree=" + isAgree +
                ", deptNo=" + deptNo +
                '}';
    }
}
