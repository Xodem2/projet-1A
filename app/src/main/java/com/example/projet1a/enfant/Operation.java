package com.example.projet1a.enfant;
import com.example.projet1a.list.ListNumbers;

import java.util.Random;

public class Operation {
    private int a;
    private int b;
    private Random random;
    private int nMax = 50;
    int nProps = 3;
    public Operation() {
        this.random = new Random();
        this.generate();
    }
    public int getA() {
        return this.a;
    }
    public int getB() {
        return this.b;
    }
    public void setA(int a) {
        this.a = a;
    }
    public void setB(int b) {
        this.b = b;
    }
    public int ansPlus() {
        return this.a+this.b;
    }
    public int ansMinus() {
        return this.a-this.b;
    }
    public int ansMult() {
        return this.a*this.b;
    }
    public int ansDiv() {
        return this.a/this.b;
    }
    public void generate() {
        this.a = this.random.nextInt(nMax-1)+1;
        this.b = this.random.nextInt(nMax-1)+1;
    }
    public boolean plus(int ans) {
        return ans == ansPlus();
    }
    public boolean minus(int ans) {
        return ans == ansMinus();
    }
    public boolean mult(int ans) {
        return ans == ansMult();
    }
    public boolean div(int ans) {
        return ans == ansDiv();
    }
    public int getNProps() {
        return nProps;
    }
    public ListNumbers propPlus() {
        ListNumbers choices = new ListNumbers(this.nProps);
        int correctProp = ansPlus();
        int correctPropPos = this.random.nextInt(nProps);
        for(int i = 0; i < nProps; i++) {
            if(i == correctPropPos)
                choices.add(correctProp);
            else {
                int falseProp;
                do {
                    int r = this.random.nextInt(3);
                    if(r == 0) {
                        falseProp = correctProp+this.random.nextInt(3)+1;
                    }
                    else if(r == 1) {
                        falseProp = correctProp-this.random.nextInt(3)-1;
                    }
                    else {
                        falseProp = correctProp-10;
                    }
                } while(choices.contains(falseProp) || falseProp == correctProp);
                choices.add(falseProp);
            }
        }
        return choices;
    }
    public ListNumbers propMinus() {
        ListNumbers choices = new ListNumbers(this.nProps);
        int correctProp = ansMinus();
        int correctPropPos = this.random.nextInt(nProps);
        for(int i = 0; i < nProps; i++) {
            if(i == correctPropPos)
                choices.add(correctProp);
            else {
                int falseProp;
                do {
                    int r = this.random.nextInt(4);
                    if(r == 0) {
                        falseProp = correctProp+this.random.nextInt(3)+1;
                    }
                    else if(r == 1) {
                        falseProp = correctProp-this.random.nextInt(3)-1;
                    }
                    else if(r == 2) {
                        falseProp = correctProp+10;
                    }
                    else {
                        falseProp = ansPlus();
                    }
                } while(choices.contains(falseProp) || falseProp == correctProp);
                choices.add(falseProp);
            }
        }
        return choices;
    }
    public ListNumbers propMult() {
        ListNumbers choices = new ListNumbers(this.nProps);
        int correctProp = ansMult();
        int correctPropPos = this.random.nextInt(nProps);
        for(int i = 0; i < nProps; i++) {
            if(i == correctPropPos)
                choices.add(correctProp);
            else {
                int falseProp;
                do {
                    int r = this.random.nextInt(4);
                    if(r == 0) {
                        falseProp = correctProp*(this.random.nextInt(3)+1)+this.random.nextInt(3)+1;
                    }
                    else if(r == 1) {
                        falseProp = correctProp*(this.random.nextInt(3)+1)+this.random.nextInt(3)-1;
                    }
                    else if(r == 2) {
                        falseProp = correctProp*(this.random.nextInt(3)+1)-this.random.nextInt(3)+1;
                    }
                    else {
                        falseProp = correctProp*(this.random.nextInt(3)+1)-this.random.nextInt(3)-1;
                    }
                } while(choices.contains(falseProp) || falseProp == correctProp);
                choices.add(falseProp);
            }
        }
        return choices;
    }
    public ListNumbers propDiv() {
        ListNumbers choices = new ListNumbers(this.nProps);
        int correctProp = ansDiv();
        int correctPropPos = this.random.nextInt(nProps);
        for(int i = 0; i < nProps; i++) {
            if(i == correctPropPos)
                choices.add(correctProp);
            else {
                int falseProp;
                do {
                    int r = this.random.nextInt(4);
                    if(r == 0) {
                        falseProp = correctProp*(this.random.nextInt(3)+1)+this.random.nextInt(3)+1;
                    }
                    else if(r == 1) {
                        falseProp = correctProp*(this.random.nextInt(3)+1)+this.random.nextInt(3)-1;
                    }
                    else if(r == 2) {
                        falseProp = correctProp*(this.random.nextInt(3)+1)-this.random.nextInt(3)+1;
                    }
                    else {
                        falseProp = correctProp*(this.random.nextInt(3)+1)-this.random.nextInt(3)-1;
                    }
                } while(choices.contains(falseProp) || falseProp == correctProp);
                choices.add(falseProp);
            }
        }
        return choices;
    }
}
