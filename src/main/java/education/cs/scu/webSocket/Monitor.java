package education.cs.scu.webSocket;

import education.cs.scu.DBHelper.DBHelper;
import education.cs.scu.DBHelper.DataDBManager;
import education.cs.scu.entity.UserVisitBean;
import education.cs.scu.service.UserVisitService;
import education.cs.scu.webSocket.handler.WebSocketEndPointTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.ResultSet;


public class Monitor implements Runnable {

//    UserDiagramData userDiagramData = new UserDiagramData();;
//    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    int getRandomNumber(){
//        return (int)(Math.random() * 300);
//    }

    @Autowired
    UserVisitService userVisitService;

    private static ResultSet rs = null;
    private static DataDBManager clockDataDBManager = null;
    private UserVisitBean userFlow = new UserVisitBean();

    public void run() {
        try {
            Connection conn = DBHelper.createInstance();
            clockDataDBManager = new DataDBManager(conn);
            //System.out.println("clockDBManager" + clockDataDBManager.toString());
            rs = clockDataDBManager.executeQuery();
            if (rs.next()) {
                userFlow.setTime(rs.getLong("time"));
                userFlow.setTotalFlow(rs.getInt("total_flow"));
                userFlow.setCheckInFlow(rs.getInt("check_in_flow"));
                userFlow.setCheckInRate(rs.getDouble("check_in_rate"));
                userFlow.setDeepVisitRate(rs.getDouble("deep_visit_rate"));
                userFlow.setShallowVisitRate(rs.getDouble("shallow_visit_rate"));
                WebSocketEndPointTest webSocketTest = new WebSocketEndPointTest();
//                System.out.println("推送消息:" + userFlow);
                webSocketTest.sendMsg(userFlow);
            }
            DBHelper.closeDB();
            //UserFlow userFlow = userVisitDao.queryUserVisit();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void sendMsg() {
//        System.out.println("sendMsg");
//        ScheduledExecutorService newScheduledThreadPool = Executors.newSingleThreadScheduledExecutor();
//        newScheduledThreadPool.scheduleWithFixedDelay(new Monitor(), 10, 2, TimeUnit.SECONDS);
    }
}

