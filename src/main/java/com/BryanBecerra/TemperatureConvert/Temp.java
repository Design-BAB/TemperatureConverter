/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.BryanBecerra.TemperatureConvert;

/**
 *
 * @author bbece
 */
public class Temp {
    private int type;
    private float amount;
    
    public Temp(char type, float amount) {
        this.type = type;
        this.amount = amount;
    }
    public float getAmount() {
        return amount;
    }
    public int getType() {
        return type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Temp{" + "type=" + type + ", amount=" + amount + '}';
    }
     
    
    
    public float convert(char intoNewType){
        //So here, we bring in what type and output the results
        //note to Bryan, you gotta make this a switch statement
        if (intoNewType == 'c' && type == 'f') {
            float newF;
            float factor = 9 / 5;
            newF = amount * factor;
            newF = newF + 32;
            return newF;
        } else if (intoNewType == 'c' && type == 'f') {
            float newC = amount - 32;
            float factor = 5 / 9;
            newC = newC * factor;
            return newC;
        }
        return 0;
    }
    
}
