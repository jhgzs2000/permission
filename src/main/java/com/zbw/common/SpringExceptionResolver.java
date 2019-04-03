package com.zbw.common;

import com.zbw.exception.PermissionException;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhangbin
 * @create 2019-04-02-11:58
 */
@Slf4j
public class SpringExceptionResolver implements HandlerExceptionResolver{


    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String url = request.getRequestURL().toString();
        ModelAndView mv;
        String defaultMsg = "System error";

        //这里要求项目中所有请求，都是用.json结尾
        if(url.endsWith(".json")){
            if(ex instanceof PermissionException){
                JsonData result = JsonData.fail(ex.getMessage());
                mv=new ModelAndView("jsonView",result.toMap());
            }else{
                log.error("unknown json exception, url:" + url, ex);
                JsonData result = JsonData.fail(defaultMsg);
                mv = new ModelAndView("jsonView", result.toMap());
            }

        }else if(url.endsWith(".page")){//里要求项目中所有请求，都是用.page结尾
            log.error("unknown json exception, url:" + url, ex);
            JsonData result=JsonData.fail(defaultMsg);
            mv=new ModelAndView("exception",result.toMap());
        }else {
            log.error("unknown json exception, url:" + url, ex);
            JsonData result=JsonData.fail(defaultMsg);
            mv=new ModelAndView("jsonView",result.toMap());
        }

        return mv;
    }
}
