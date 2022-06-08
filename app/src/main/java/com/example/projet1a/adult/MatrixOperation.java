package com.example.projet1a.adult;

import java.util.ArrayList;
import java.util.Random;

public class MatrixOperation {
    int nProps = 3;
    int dim = 3;
    private Matrix M1 = new Matrix(dim, dim);
    private Matrix M2 = new Matrix(dim, dim);
    private Random random = new Random();
    public void MatrixOperation() {
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

    public Matrix ansProd() {
        return M1.prod(M2);
    }

    public boolean sum(Matrix R) {
        return R.is(ansSum());
    }

    public boolean prod(Matrix R) {
        return R.is(ansProd());
    }

    public ArrayList<Matrix> propSum() {
        ArrayList<Matrix> choices = new ArrayList<>();
        Matrix correctProp = ansSum();
        int correctPropPos = this.random.nextInt(nProps);
        for (int i = 0; i < nProps; i++) {
            if (i == correctPropPos)
                choices.add(correctProp);
            else {
                Matrix falseProp = new Matrix(dim, dim);
                do {
                    int r = this.random.nextInt(6);
                    int jf = this.random.nextInt(dim);
                    int kf = this.random.nextInt(dim);
                    for(int j = 0; j < falseProp.getM(); j++) {
                        for(int k = 0; k < falseProp.getN(); k++) {
                            if (j == jf && k == kf) {
                                if(r == 0)
                                    falseProp.set(j, k, -(M1.get(j, k)+M2.get(j, k)));
                                else if(r == 1)
                                    falseProp.set(j, k, M1.get(j, k)-M2.get(j, k));
                                else if(r == 2)
                                    falseProp.set(j, k, -M1.get(j, k)+M2.get(j, k));
                                else if(r == 3)
                                    falseProp.set(j, k, M1.get(j, k)+M2.get(j, k)+10);
                                else if(r == 4)
                                    falseProp.set(j, k, M1.get(j, k)+M2.get(j, k)-10);
                                else
                                    falseProp.set(j, k, M1.get(j, k)+M2.get(j, k)+this.random.nextInt(3)+1);
                            }
                            else {
                                falseProp.set(j, k, M1.get(j, k)+M2.get(j, k));
                            }
                        }
                    }
                } while (choices.contains(falseProp) || falseProp.getMatrix() == correctProp.getMatrix());
                choices.add(falseProp);
            }
        }
        return choices;
    }

    public ArrayList<Matrix> propProd() {
        ArrayList<Matrix> choices = new ArrayList<>();
        Matrix correctProp = ansProd();
        int correctPropPos = this.random.nextInt(nProps);
        for (int i = 0; i < nProps; i++) {
            if (i == correctPropPos)
                choices.add(correctProp);
            else {
                Matrix falseProp = new Matrix(dim, dim);
                do {
                    int r = this.random.nextInt(6);
                    int jf = this.random.nextInt(dim);
                    int kf = this.random.nextInt(dim);
                    if(r == 0) {
                        falseProp = ansProd();
                        falseProp.set(jf, kf, -falseProp.get(jf, kf));
                    }
                    else if (r == 1) {
                        falseProp = ansProd();
                        falseProp.set(jf, kf, (this.random.nextInt(2)+2)*falseProp.get(jf, kf));
                    }
                    else if (r == 2) {
                        falseProp = ansProd();
                        falseProp.set(jf, kf, -(this.random.nextInt(2)+2)*falseProp.get(jf, kf));
                    }
                    else if (r == 3) {
                        for(int j = 0; j < falseProp.getM(); j++) {
                            for(int k = 0; k < falseProp.getN(); k++) {
                                falseProp.set(j, k, M1.get(j, k)*M2.get(j, k));
                            }
                        }
                    }
                    else if (r == 4) {
                        falseProp = M2.prod(M1);
                    }
                    else {
                        falseProp = M1.prod(M2.t());
                    }
                } while (choices.contains(falseProp) || falseProp.getMatrix() == correctProp.getMatrix());
                choices.add(falseProp);
            }
        }
        return choices;
    }
}
