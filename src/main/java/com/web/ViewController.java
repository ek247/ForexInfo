package com.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

/**
 * Created by worri on 6/22/2016.
 */

@Controller
public class ViewController {
    @RequestMapping("/")
    public String index()
    {
        return "Angular/index.html";
    }


}
