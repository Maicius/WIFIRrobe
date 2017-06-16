package education.cs.scu.webSocket;

import education.cs.scu.webSocket.handler.WebSocketEndPointTest;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Monitor implements Runnable {

    public void run() {
        WebSocketEndPointTest webSocketTest = new WebSocketEndPointTest();
        System.out.println("推送消息:" + new Date());
        webSocketTest.sendMsg("当前时间:" + new Date());
    }

    public void sendMsg() {
        ScheduledExecutorService newScheduledThreadPool = Executors.newSingleThreadScheduledExecutor();
        newScheduledThreadPool.scheduleWithFixedDelay(new Monitor(), 20, 5, TimeUnit.SECONDS);
    }
}

