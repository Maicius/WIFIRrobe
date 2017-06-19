package education.cs.scu.webSocket;

import education.cs.scu.DBHelper.DBHelper;
import education.cs.scu.DBHelper.DataDBManager;
import education.cs.scu.entity.UserFlow;
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
    private UserFlow userFlow = new UserFlow();

    public void run() {
        try {
            System.out.println("clockDBManager" + clockDataDBManager.toString());
            rs = clockDataDBManager.executeQuery();
            if (rs.next()) {
                userFlow.setTimestamp(rs.getTimestamp("time"));
                userFlow.setTotal_flow(rs.getInt("total_flow"));
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

