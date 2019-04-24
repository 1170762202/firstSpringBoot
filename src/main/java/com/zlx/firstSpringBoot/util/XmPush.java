package com.zlx.firstSpringBoot.util;

import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;

public class XmPush {

    private static String APP_SECRET;
    private static String PACKAGE_NAME;

    public static void main(String[] args) {
        Constants.useOfficial();
        try {
            sendMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendMessage() throws Exception {
        Constants.useOfficial();
        Sender sender = new Sender(APP_SECRET);
        String messagePayload = "斯蒂芬1";
        String title = "斯蒂芬";
        String description = "斯蒂芬2";
        Message message = new Message.Builder()
                .title(title)
                .description(description).payload(messagePayload)
                .restrictedPackageName(PACKAGE_NAME)
                .notifyType(1)     // 使用默认提示音提示
                .build();
        Result result = sender.sendToAlias(message, "1903208B26KXWR68", 2);
        System.out.println("MessageId: " + result.getMessageId()
                + " ErrorCode: " + result.getErrorCode().getDescription()
                + " Reason: " + result.getReason());
    }
}
