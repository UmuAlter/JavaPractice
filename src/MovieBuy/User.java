package MovieBuy;

public class User {
    private String logname;
    private String realname;
    private String passwd;
    private String phone;
    private char sex;
    private double money;

    public User(String logname, String realname, String passwd, String phone, char sex, double money) {
        this.logname = logname;
        this.realname = realname;
        this.passwd = passwd;
        this.phone = phone;
        this.sex = sex;
        this.money = money;
    }

    public User() {
    }

    public String getLogname() {
        return logname;
    }

    public void setLogname(String logname) {
        this.logname = logname;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
