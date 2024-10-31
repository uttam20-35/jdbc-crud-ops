package pojo;

public class Employee {

    private Long eid;
    private String ename;
    private int age;

    public Employee() {
    }

    public Employee(Long eid, String ename, int age) {
        this.eid = eid;
        this.ename = ename;
        this.age = age;
    }

    public Long getEid() {
        return eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
