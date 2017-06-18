package education.cs.scu.webSocket;

import education.cs.scu.entity.UserFlow;
import education.cs.scu.service.impl.UserVisitDaoImpl;
import education.cs.scu.webSocket.handler.WebSocketEndPointTest;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Monitor implements Runnable {

//    UserDiagramData userDiagramData = new UserDiagramData();;
//    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    int getRandomNumber(){
//        return (int)(Math.random() * 300);
//    }

    public void run() {
        UserVisitDaoImpl userVisitDao = new UserVisitDaoImpl();
        try {
            UserFlow userFlow = userVisitDao.queryUserVisit();
            WebSocketEndPointTest webSocketTest = new WebSocketEndPointTest();
            System.out.println("推送消息:" + userFlow);
            webSocketTest.sendMsg(userFlow);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void sendMsg() {
        ScheduledExecutorService newScheduledThreadPool = Executors.newSingleThreadScheduledExecutor();
        newScheduledThreadPool.scheduleWithFixedDelay(new Monitor(), 30, 3, TimeUnit.SECONDS);
    }
}

