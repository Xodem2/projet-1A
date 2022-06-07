package com.example.projet1a.adult;

import java.util.ArrayList;
import java.util.Random;

public class VectorQuestion {

    private static final int MINIMUM_DIMENSION = 2;
    private static final int MAXIMUM_DIMENSION = 4;

    private static final int MINIMUM_VALUE = 0;
    private static final int MAXIMUM_VALUE = 100;

    private final Random random;

    private  int vectorDimension;

    private ArrayList<Integer> vector1;
    private ArrayList<Integer> vector2;

    private Object result;

    public VectorQuestion(){
        this.random = new Random();
        this.generateDimension();
        this.generateVectors();
    }

    public Object getResult(){
        return this.result;
    }

    private void generateDimension(){
        this.vectorDimension = this.random.nextInt(MAXIMUM_DIMENSION + 1) + MINIMUM_DIMENSION;
    }

    private void generateVectors(){
        this.vector1 = new ArrayList<>();
        this.vector2 = new ArrayList<>();

        for(int i = 0; i < this.vectorDimension; i++) {
            this.vector1.add(this.random.nextInt(MAXIMUM_VALUE + 1) + MINIMUM_VALUE);
            this.vector2.add(this.random.nextInt(MAXIMUM_VALUE + 1) + MINIMUM_VALUE);
        }
    }

    public void addition(){
        ArrayList<Integer> vectorResult = new ArrayList<>();
        for(int i = 0; i < this.vectorDimension; i++)
            vectorResult.add(this.vector1.get(i) + this.vector2.get(i));
        this.result = vectorResult;
    }

    public void substraction(){
        ArrayList<Integer> vectorResult = new ArrayList<>();
        for(int i = 0; i < this.vectorDimension; i++)
            vectorResult.add(this.vector1.get(i) - this.vector2.get(i));
        this.result = vectorResult;
    }

    public void scalarProduct(){
        int scalarResult = 0;
        for(int i = 0; i < this.vectorDimension; i++)
            scalarResult += this.vector1.get(i) * this.vector2.get(i);
        this.result = scalarResult;
    }

}
