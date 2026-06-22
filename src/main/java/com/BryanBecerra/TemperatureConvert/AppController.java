/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.BryanBecerra.TemperatureConvert;

import org.springframework.http.ResponseEntity;
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
    
    //here the return type is ResponseEntity<String> so I may actually control the HTTP status code
    // this allows me to put some safty around the converting type
    @GetMapping("/convert")
    @ResponseBody
    public ResponseEntity<String> convert(@RequestParam String number1, @RequestParam String tempType) {
        double newAmount;
        try {
            newAmount = Double.parseDouble(number1);
        } catch (NumberFormatException e) {
           return ResponseEntity.badRequest().body("Invalid input. Please enter valid numbers.");
        }
        Temp theTemperature = new Temp(tempType, newAmount);
        if (theTemperature.getType() == 'F') {
            return ResponseEntity.ok(theTemperature.convert('C'));
        } else if (theTemperature.getType() == 'C') {
            return ResponseEntity.ok(theTemperature.convert('F'));
        }
        return ResponseEntity.badRequest().body("Please select a conversion type");
}
    
    @GetMapping("/formLabel")
    @ResponseBody
    public String formLabel(@RequestParam String tempType) {
        char type = tempType.charAt(0);
        type = Character.toUpperCase(type);
        var formUpdate = new StringBuilder();
        switch(type) {
            case 'F':
                //using a string builder will help make the html readable having seperate lines to see
                formUpdate.append("<label for=\"number1\">Fahrenheit: </label>");
                formUpdate.append("<input type=\"number\" id=\"number1\" name=\"number1\" min=\"-100\"><br>");
                break;
            case 'C':
                formUpdate.append("<label for=\"number1\">Celsius: </label>");
                formUpdate.append("<input type=\"number\" id=\"number1\" name=\"number1\" min=\"-100\"><br>");
                break;
            default:
                formUpdate.append("<p>Unknown temperature type</p>");
                break;
        }
        return formUpdate.toString();
    }
    
}