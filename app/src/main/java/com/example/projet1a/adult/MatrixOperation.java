package com.example.projet1a.adult;

import java.util.ArrayList;
import java.util.Random;

public class MatrixOperation {
    int nProps = 3;
    private Random random = new Random();
    private Matrix M1;
    private Matrix M2;
    private int correct;

    public MatrixOperation() {
        int dim = random.nextInt(2)+2;
        M1 = new Matrix(dim, dim);
        M2 = new Matrix(dim, dim);
        this.generate();
    }

    public MatrixOperation(int dim) {
        M1 = new Matrix(dim, dim);
        M2 = new Matrix(dim, dim);
        this.generate();
    }

    public void generate() {
        this.M1.generate();
        this.M2.generate();
    }

    public int getCorrect() {
        return correct;
    }

    public Matrix getM1() {
        return this.M1;
    }

    public Matrix getM2() {
        return this.M2;
    }

    public int getNProps() { return this.nProps; }

    public Matrix ansSum() {
        return M1.sum(M2);
    }

    public Matrix ansMinus() {
        return M1.minus(M2);
    }

    public Matrix ansProd() {
        return M1.prod(M2);
    }

    public boolean sum(Matrix R) {
        return R.is(ansSum());
    }

    public boolean minus(Matrix R) {
        return R.is(ansMinus());
    }

    public boolean prod(Matrix R) {
        return R.is(ansProd());
    }

    public ArrayList<Matrix> propSum() {
        ArrayList<Matrix> choices = new ArrayList<>();
        Matrix correctProp = ansSum().getMatrix();
        int correctPropPos = this.random.nextInt(nProps);
        this.correct = correctPropPos;
        for (int i = 0; i < nProps; i++) {
            if (i == correctPropPos)
                choices.add(correctProp.getMatrix());
            else {
                Matrix falseProp;
                do {
                    falseProp = correctProp.getMatrix();
                    for (int j = 0; j < falseProp.getM(); j++) {
                        for (int k = 0; k < falseProp.getN(); k++) {
                            int v = correctProp.get(j, k);
                            if (M2.get(j, k) < 0 && random.nextInt(3) == 0) {
                                v = M1.get(j, k) - M2.get(j, k);
                            }
                            if (M1.get(j, k) < 0 && random.nextInt(3) == 0) {
                                v = -M1.get(j, k) - M2.get(j, k);
                            }
                            if (random.nextInt(9) == 0) {
                                v += random.nextInt(2)+1;
                            }
                            if (random.nextInt(9) == 0) {
                                v -= random.nextInt(2)+1;
                            }
                            falseProp.set(j, k, v);
                        }
                    }
                } while (falseProp.inArray(choices) || falseProp.is(correctProp.getMatrix()));
                choices.add(falseProp);
            }
        }
        return choices;
    }

    public ArrayList<Matrix> propMinus() {
        ArrayList<Matrix> choices = new ArrayList<>();
        Matrix correctProp = ansMinus().getMatrix();
        int correctPropPos = this.random.nextInt(nProps);
        this.correct = correctPropPos;
        for (int i = 0; i < nProps; i++) {
            if (i == correctPropPos)
                choices.add(correctProp.getMatrix());
            else {
                Matrix falseProp;
                do {
                    falseProp = correctProp.getMatrix();
                    for (int j = 0; j < falseProp.getM(); j++) {
                        for (int k = 0; k < falseProp.getN(); k++) {
                            int v = correctProp.get(j, k);
                            if (M2.get(j, k) < 0 && random.nextInt(3) == 0) {
                                v = M1.get(j, k) + M2.get(j, k);
                            }
                            if (M1.get(j, k) < 0 && random.nextInt(3) == 0) {
                                v = -M1.get(j, k) + M2.get(j, k);
                            }
                            if (random.nextInt(9) == 0) {
                                v += random.nextInt(2)+1;
                            }
                            if (random.nextInt(9) == 0) {
                                v -= random.nextInt(2)+1;
                            }
                            falseProp.set(j, k, v);
                        }
                    }
                } while (falseProp.inArray(choices) || falseProp.is(correctProp.getMatrix()));
                choices.add(falseProp);
            }
        }
        return choices;
    }

    public ArrayList<Matrix> propProd() {
        ArrayList<Matrix> choices = new ArrayList<>();
        Matrix correctProp = ansProd().getMatrix();
        int correctPropPos = this.random.nextInt(nProps);
        this.correct = correctPropPos;
        for (int i = 0; i < nProps; i++) {
            if (i == correctPropPos)
                choices.add(correctProp.getMatrix());
            else {
                Matrix falseProp;
                do {
                    falseProp = correctProp.getMatrix();
                    int r = this.random.nextInt(4);
                    if(r == 0) {
                        for(int j = 0; j < falseProp.getM(); j++) {
                            for(int k = 0; k < falseProp.getN(); k++) {
                                falseProp.set(j, k, M1.get(j, k)*M2.get(j, k));
                            }
                        }
                    }
                    else if (r == 1) {
                        falseProp = M2.prod(M1);
                    }
                    else if (r == 2) {
                        for(int j = 0; j < falseProp.getM(); j++) {
                            for(int k = 0; k < falseProp.getN(); k++) {
                                int v = correctProp.get(j, k);
                                if(this.random.nextInt(3) == 0) {
                                    v = -falseProp.get(j, k);
                                }
                                falseProp.set(j, k, v);
                            }
                        }
                    }
                    else {
                        Matrix M1f = this.M1.getMatrix();
                        Matrix M2f = this.M2.getMatrix();
                        for(int j = 0; j < falseProp.getM(); j++) {
                            for(int k = 0; k < falseProp.getN(); k++) {
                                if(((M1f.get(j, k) < 0 && M2f.get(j, k) > 0)
                                        || (M1f.get(j, k) > 0 && M2f.get(j, k) < 0))
                                        && this.random.nextInt(3) == 0) {
                                    if(this.random.nextInt(2) == 0) {
                                        M1f.set(j, k, -M1f.get(j, k));
                                    }
                                    else {
                                        M2f.set(j, k, -M2f.get(j, k));
                                    }
                                }
                            }
                        }
                        falseProp = M1f.prod(M2f);
                    }
                } while (falseProp.inArray(choices) || falseProp.is(correctProp.getMatrix()));
                choices.add(falseProp);
            }
        }
        return choices;
    }
}
