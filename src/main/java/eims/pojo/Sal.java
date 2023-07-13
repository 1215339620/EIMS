package eims.pojo;

public class Sal {
    private int eid;
    private String name;
    private String job;
    private  double salary;

    public Sal() {
    }

    public Sal(int eid, String name, String job, double salary) {
        this.eid = eid;
        this.name = name;
        this.job = job;
        this.salary = salary;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Sal{" +
                "eid=" + eid +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", salary=" + salary +
                '}';
    }
}
