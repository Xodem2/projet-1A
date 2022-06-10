package com.example.projet1a.adult;

import java.util.ArrayList;
import java.util.Random;

public class MatrixOperation {
    int nProps = 3;
    int dim = 3;
    private Matrix M1 = new Matrix(dim, dim);
    private Matrix M2 = new Matrix(dim, dim);
    private Random random = new Random();
    public MatrixOperation() {
        this.generate();
    }

    public void generate() {
        this.M1.generate();
        this.M2.generate();
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
                            if (M1.get(j, k) > 0 && M2.get(j, k) > 0 && M1.get(j, k) % 10 + M2.get(j, k) % 10 >= 10  && random.nextInt(3) == 0) {
                                v = v - 10;
                            }
                            if (M2.get(j, k) < 0 && random.nextInt(3) == 0) {
                                v = M1.get(j, k) - M2.get(j, k);
                            }
                            if (M1.get(j, k) < 0 && random.nextInt(3) == 0) {
                                v = -M1.get(j, k) - M2.get(j, k);
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
                            if (M1.get(j, k) > 0 && M2.get(j, k) > 0 && M1.get(j, k) % 10 - M2.get(j, k) % 10 < 0 && random.nextInt(3) == 0) {
                                v = v + 10;
                            }
                            if ((M1.get(j, k) < 0 || M2.get(j, k) < 0) && random.nextInt(3) == 0) {
                                v = M1.get(j, k) + M2.get(j, k);
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
        for (int i = 0; i < nProps; i++) {
            if (i == correctPropPos)
                choices.add(correctProp.getMatrix());
            else {
                Matrix falseProp;
                do {
                    falseProp = correctProp.getMatrix();
                    int r = this.random.nextInt(3);
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
                    else {
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
                } while (falseProp.inArray(choices) || falseProp.is(correctProp.getMatrix()));
                choices.add(falseProp);
            }
        }
        return choices;
    }
}
