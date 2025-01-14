package models;

public class Worker {
    private int tabelNumer;
    private String login;
    private String  name;
    private String secName;
    private int discharge;
    private int workStatus;

    public int getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(int workStatus) {
        this.workStatus = workStatus;
    }

    public int getTabelNumer() {
        return tabelNumer;
    }

    public void setTabelNumer(int tabelNumer) {
        this.tabelNumer = tabelNumer;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSecName() {
        return secName;
    }

    public void setSecName(String secName) {
        this.secName = secName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDischarge() {
        return discharge;
    }

    public void setDischarge(int discharge) {
        this.discharge = discharge;
    }
}
