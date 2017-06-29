package education.cs.scu.entity;

/**
 * Created by maicius on 2017/6/29.
 */
public class HistoryData {
    private String year;
    private String mongth;
    private String day;
    private String userName;
    private int activityDegree;
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMongth() {
        return mongth;
    }

    public void setMongth(String mongth) {
        this.mongth = mongth;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getActivityDegree() {
        return activityDegree;
    }

    public void setActivityDegree(int activityDegree) {
        this.activityDegree = activityDegree;
    }
}
