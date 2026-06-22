/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.BryanBecerra.TemperatureConvert;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {
    
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "<h1>Hello moon</h1>";
    }
    
        
    @GetMapping("/convert")
    @ResponseBody
    public String convert(@RequestParam String number1, @RequestParam String tempType) {
        var newAmount = Float.parseFloat(number1);
        Temp theTemperature = new Temp(tempType.charAt(0), newAmount);
        return theTemperature.toString();
    }
    
}