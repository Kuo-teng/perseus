package com.perseus.utopia.controller;

import com.perseus.utopia.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Objects;

/**
 * Description:
 *
 * @author Guoteng Jiang
 * @date 2023/7/17 20:53
 */
@RestController
@Slf4j
public class HelloController {

    @PostConstruct
    public void init() {
        log.error("HelloController init...");
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello()
    {
        return "Hello.";
    }

    @PostMapping("/get")
    @ResponseBody
    //@HystrixCommand(fallbackMethod="errorCallBack")
    public String get(@RequestBody String id) {
        if (Objects.equals(id, "1")) {
            return id;
        } else {
            System.out.println("aaaaaaaaaaaaa");
            throw new RuntimeException("id 不为 1");
        }
    }

    @GetMapping("/test")
    @ResponseBody
    public String test(String name) {
        return "Welcome to perseus, " + name + "!";
    }

    @GetMapping("/date")
    @ResponseBody
    public String date() {

        String dateTime = DateUtils.getFormatDateStr(new Date(), DateUtils.DATE_TIME);
        return "The current time is: " + dateTime;
    }

    ////指定一个降级的方法
    //public String  errorCallBack(@RequestParam("id") int id) {
    //    return id+"不存在,error";
    //}
}
