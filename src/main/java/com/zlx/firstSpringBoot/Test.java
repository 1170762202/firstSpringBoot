package com.zlx.firstSpringBoot;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

public class Test {
    public static void main(String[] args) {
        push("测试标题", "测试内容");

    }

    private static void push(String title, String content) {
        JPushClient jpushClient = new JPushClient("ac22ae720478b121989cdd2f",
                "a55aa0dae32aef13a481ea8d", null, ClientConfig.getInstance());

        // For push, all you need do is to build PushPayload object.
        PushPayload payload = buildPushObject_android_tag_alertWithTitle(title, content,null);

        try {
            PushResult result = jpushClient.sendPush(payload);
            System.out.println("Got result - " + result);

        } catch (APIConnectionException e) {
            // Connection error, should retry later
            System.out.println("Connection error, should retry later" + e);

        } catch (APIRequestException e) {
            // Should review the error, and fix the request
            System.out.println("Should review the error, and fix the request" + e);
            System.out.println("HTTP Status: " + e.getStatus());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("Error Message: " + e.getErrorMessage());
        }
    }

    public static PushPayload buildPushObject_android_tag_alertWithTitle(String title, String content, String alias) {
        PushPayload.Builder builder = PushPayload.newBuilder()
                .setPlatform(Platform.android());
        if (alias == null) {
            builder.setAudience(Audience.all());
        } else {
            builder.setAudience(Audience.alias(alias));
        }
        builder.setNotification(Notification.android(content, title, null));
        return builder.build();
    }
}
