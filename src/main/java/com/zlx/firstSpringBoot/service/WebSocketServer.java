package com.zlx.firstSpringBoot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/websocket/{sid}")
@RestController
public class WebSocketServer {

    protected static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static ConcurrentHashMap<String, WebSocketServer> webSocketSet = new ConcurrentHashMap<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    //接收sid
    private String sid = "";

    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        this.session = session;
        this.sid = sid;
        webSocketSet.put(sid, this);//加入set中
        addOnlineCount();
        logger.info("有新窗口开始监听:" + sid + ",当前在线人数为" + getOnlineCount());
        sendtoUser("socket 初始化",sid);
        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            logger.info("websocket IO 异常");
        }
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        subOnlineCount();
        logger.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("收到来自窗口" + sid + "的信息:" + message);
        //群发消息
        sendToAll(message);
    }


    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message, String cid) {
        logger.info("推送消息到窗口" + cid + "  "+(cid == null) + "，推送内容:" + message);
        if (cid != null) {
            sendtoUser(message, cid);
        } else {
            sendToAll(message);
        }
    }

    /**
     * 发送信息给所有人
     *
     * @param message
     */
    private static void sendToAll(String message) {
        logger.info("群聊");

        for (String key : webSocketSet.keySet()) {
            try {
                logger.info("sendToAll:" + key);
                webSocketSet.get(key).sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发送信息给指定ID用户，如果用户不在线则返回不在线信息给自己
     *
     * @param message
     * @param sendUserId
     * @throws IOException
     */
    private static void sendtoUser(String message, String sendUserId) {
        logger.info("单聊");

        if (webSocketSet.get(sendUserId) != null) {
            try {
                webSocketSet.get(sendUserId).sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //如果用户不在线则返回不在线信息给自己
            System.out.println("用户" + sendUserId + "不在线");
        }
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
