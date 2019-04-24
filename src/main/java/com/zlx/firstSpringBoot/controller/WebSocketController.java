package com.zlx.firstSpringBoot.controller;


import com.zlx.firstSpringBoot.constant.ResponseCode;
import com.zlx.firstSpringBoot.constant.ReturnUtil;
import com.zlx.firstSpringBoot.service.WebSocketServer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/websocket/sendMsg")
public class WebSocketController {

    @PostMapping("/sendText")
    public Map sendText(String message, String cid) {
        if (message == null) {
            return ReturnUtil.returnValue(ResponseCode.failed);
        }
        WebSocketServer.sendInfo(message, cid);
        return ReturnUtil.returnValue(ResponseCode.success);
    }
}
