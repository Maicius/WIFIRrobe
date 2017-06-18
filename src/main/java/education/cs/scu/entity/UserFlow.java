package education.cs.scu.entity;

import java.sql.Timestamp;

/**
 * Created by maicius on 2017/6/18.
 */
public class UserFlow {
    private Timestamp timestamp;
    private int total_flow;
    public UserFlow(){

    }
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getTotal_flow() {
        return total_flow;
    }

    public void setTotal_flow(int total_flow) {
        this.total_flow = total_flow;
    }
}
