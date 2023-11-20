package DietLogs;

import java.util.Date;
import java.util.List;


public class DietLogEntry {

    private int Dietid;
    private String name = "";

    private String foodGroup;

    private int dateTime;

    private int userid;


    public DietLogEntry(int Dietid, String name, String foodGroup, int dateTime, int userid) {
        this.Dietid = Dietid;
        this.name = name;
        this.foodGroup = foodGroup;
        this.dateTime = dateTime;
        this.userid = userid;

    }

    public int getDietId() {
        return Dietid;
    }

    public void setDietId(int DietId) {
        this.Dietid = DietId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFoodGroup() {
        return foodGroup;
    }

    public void setFoodGroup(String foodGroup) {
        this.foodGroup = foodGroup;
    }

    public int getDateTime() {
        return dateTime;
    }

    public void setDateTime(int dateTime) {
        this.dateTime = dateTime;
    }

    public int getUserId() {
        return userid;
    }

    public void setUserId(int userid) {
        this.userid = userid;
    }

}
