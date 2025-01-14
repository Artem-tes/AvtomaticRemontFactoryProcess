package models;

public class Requests {
    private int ID;
    private String INN;
    private String Name;
    private String timeBrake;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getINN() {
        return INN;
    }

    public void setINN(String INN) {
        this.INN = INN;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTimeBrake() {
        return timeBrake;
    }

    public void setTimeBrake(String timeBrake) {
        this.timeBrake = timeBrake;
    }
}
