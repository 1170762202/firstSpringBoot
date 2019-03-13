package com.zlx.firstSpringBoot.controller;


import com.zlx.firstSpringBoot.constant.ResponseCode;
import com.zlx.firstSpringBoot.constant.ReturnUtil;
import com.zlx.firstSpringBoot.service.WebSocketServer;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
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
