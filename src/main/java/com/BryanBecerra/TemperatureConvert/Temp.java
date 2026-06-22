//Author: BAB-Design
//Date 6/22/2026

package com.BryanBecerra.TemperatureConvert;


public class Temp {
    private char type;
    private double amount;
    
    
    public Temp(String type, double amount) {
        var charType = type.charAt(0);
        this.type = Character.toUpperCase(charType);
        this.amount = amount;
    }
    
    
    public double getAmount() {
        return amount;
    }
    
    
    public String getAmountString() {
        var theAmount = String.format("%.0f", amount);
        return theAmount;
    }
    
    
    public char getType() {
        return type;
    }
    
    
    public void setType(char type) {
        type = Character.toUpperCase(type);
        this.type = type;
    }
    
    
    public void setAmount(int amount) {
        this.amount = amount;
    }

    
    @Override
    public String toString() {
        return "Temp{" + "type=" + type + ", amount=" + amount + '}';
    }
     
    
    private String makeItStrongHTML(String input) {
        var output = new StringBuilder();
        output.append("<strong>");
        output.append(input);
        output.append("</strong>");
        return output.toString();
    }
    
    
    public String convert(char intoNewType){
        //So here, we bring in what type and output the results
        //Did a switch statement so we don't have to deal with all possiblities with if else
        switch (type) {
            //here we are converting from F
            case 'F':
                switch(intoNewType){
                    case 'C':
                        double newC = amount - 32;
                        var factor = 5.0 / 9;
                        newC = newC * factor;
                        String results = "";
                        // the purpose of this if statement is so that it looks nicer if there are no fraction of a temp
                        if (newC % 1 == 0) {
                            results = String.format("%.0f", newC);
                        } else {
                            //otherwise the hundredth's place is fine
                            results = String.format("%.2f", newC);
                        }
                        results = "The temperature " + getAmountString() + " " + type + " is " + results + " C";
                        return makeItStrongHTML(results);
                        
                    default:
                        break;
                }
            //Here we are converting from C
            case 'C':
                switch(intoNewType){
                    case 'F':
                        double newF;
                        var factor = 9 / 5.0;
                        newF = amount * factor;
                        newF = newF + 32;
                        String results = "";
                        //If no fraction of a temp as well
                        if (newF % 1 == 0) {
                            results = String.format("%.0f", newF);
                        } else {
                            results = String.format("%.2f", newF);
                        }
                        results = "The temperature " + getAmountString() + " " + type + " is " + results + "F";
                        return makeItStrongHTML(results);
                    default:
                        break;
                }
        }
        return "Error";
    }
}