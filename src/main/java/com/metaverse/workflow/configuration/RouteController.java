//package com.metaverse.workflow.configuration;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//public class RouteController {
//
//    @RequestMapping(value = "/{path:[^\\.]*}")
//    public String redirect() {
//        return "forward:/index.html";
//    }
//
//    @RequestMapping(value = "/**/{path:[^\\.]*}")
//    public String redirectNested() {
//        return "forward:/index.html";
//    }
//}
