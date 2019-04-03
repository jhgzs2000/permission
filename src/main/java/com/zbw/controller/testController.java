package com.zbw.controller;

import com.sun.media.jfxmedia.logging.Logger;
import com.zbw.common.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhangbin @create 2019-04-01 14:39
 */
@Controller
@RequestMapping("/test")
@Slf4j
public class testController {
    @RequestMapping("/hello.json")
    @ResponseBody
    public JsonData hello(){
        log.info("hello");
        return JsonData.success("hello,permission");
    }
}
