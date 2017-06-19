package education.cs.scu.webSocket;

import education.cs.scu.DBHelper.DBHelper;
import education.cs.scu.DBHelper.DataDBManager;
import education.cs.scu.entity.UserVisitBean;
import education.cs.scu.webSocket.handler.WebSocketEndPointTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Monitor implements Runnable {

//    UserDiagramData userDiagramData = new UserDiagramData();;
//    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    int getRandomNumber(){
//        return (int)(Math.random() * 300);
//    }

    private static ResultSet rs = null;
    private static DataDBManager clockDataDBManager = null;
    private UserVisitBean userFlow = new UserVisitBean();

    public void run() {
        try {
            System.out.println("clockDBManager" + clockDataDBManager.toString());
            rs = clockDataDBManager.executeQuery();
            if (rs.next()) {
                userFlow.setTime(rs.getString("time"));
                userFlow.setTotalFlow(rs.getInt("total_flow"));
                userFlow.setCheckInFlow(rs.getLong("check_in_flow"));
                userFlow.setCheckInRate(rs.getLong("check_in_rate"));
                userFlow.setDeepVisitRate(rs.getLong("deep_visit_rate"));
                userFlow.setShallowVisitRate(rs.getLong("shallow_visit_rate"));
                WebSocketEndPointTest webSocketTest = new WebSocketEndPointTest();
                System.out.println("推送消息:" + userFlow);
                webSocketTest.sendMsg(userFlow);
            }
            //UserFlow userFlow = userVisitDao.queryUserVisit();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void sendMsg() {
        System.out.println("sendMsg");
        try {
            Connection conn = DBHelper.createInstance();
            clockDataDBManager = new DataDBManager(conn);
        }catch(Exception e){
            e.printStackTrace();
        }
        ScheduledExecutorService newScheduledThreadPool = Executors.newSingleThreadScheduledExecutor();
        newScheduledThreadPool.scheduleWithFixedDelay(new Monitor(), 10, 3, TimeUnit.SECONDS);
    }
}

