package education.cs.scu.webSocket;

import education.cs.scu.entity.UserDiagramData;
import education.cs.scu.webSocket.handler.WebSocketEndPointTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Monitor implements Runnable {
    UserDiagramData userDiagramData = new UserDiagramData();;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    int getRandomNumber(){
        return (int)(Math.random() * 300);
    }

    public void run() {
        userDiagramData.setTime(df.format(new Date()));
        userDiagramData.setNumber(getRandomNumber());
        WebSocketEndPointTest webSocketTest = new WebSocketEndPointTest();
        System.out.println("推送消息:" + userDiagramData);
        webSocketTest.sendMsg(userDiagramData);
    }
    public void sendMsg() {
        ScheduledExecutorService newScheduledThreadPool = Executors.newSingleThreadScheduledExecutor();
        newScheduledThreadPool.scheduleWithFixedDelay(new Monitor(), 20, 5, TimeUnit.SECONDS);
    }
}

