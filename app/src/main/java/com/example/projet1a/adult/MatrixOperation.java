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
                Matrix falseProp = correctProp.getMatrix();
                do {
                    for(int j = 0; j < falseProp.getM(); j++) {
                        for(int k = 0; k < falseProp.getN(); k++) {
                            int r = this.random.nextInt(44);
                            if(r == 0)
                                falseProp.set(j, k, -(M1.get(j, k)+M2.get(j, k)));
                            else if(r == 1)
                                falseProp.set(j, k, M1.get(j, k)-M2.get(j, k));
                            else if(r == 2)
                                falseProp.set(j, k, -M1.get(j, k)+M2.get(j, k));
                            else if(r == 3)
                                falseProp.set(j, k, M1.get(j, k)+M2.get(j, k)+this.random.nextInt(3)+1);
                            else if(r == 4)
                                falseProp.set(j, k, M1.get(j, k)+M2.get(j, k)-this.random.nextInt(3)-1);
                            else if(r >= 5 && r <= 7)
                                falseProp.set(j, k, M1.get(j, k)+M2.get(j, k)+10);
                            else if(r >= 8 && r <= 10)
                                falseProp.set(j, k, M1.get(j, k)+M2.get(j, k)-10);
                        }
                    }
                } while (choices.contains(falseProp) || falseProp.is(correctProp.getMatrix()));
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
                Matrix falseProp = correctProp.getMatrix();
                do {
                    int r = this.random.nextInt(9);
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
                        falseProp = M1.prod(M2.t());
                    }
                    else {
                        for(int j = 0; j < falseProp.getM(); j++) {
                            for(int k = 0; k < falseProp.getN(); k++) {
                                int r2 = this.random.nextInt(24);
                                if(r2 == 0)
                                    falseProp.set(j, k, (this.random.nextInt(2)+2)*falseProp.get(j, k));
                                else if(r2 == 1)
                                    falseProp.set(j, k, -(this.random.nextInt(2)+2)*falseProp.get(j, k));
                                else if(r2 >= 2 && r2 <= 3)
                                    falseProp.set(j, k, M1.get(j, k)*M2.get(j, k));
                                else if(r2 >= 4 && r2 <= 6)
                                    falseProp.set(j, k, -falseProp.get(j, k));
                            }
                        }
                    }
                } while (choices.contains(falseProp) || falseProp.is(correctProp.getMatrix()));
                choices.add(falseProp);
            }
        }
        return choices;
    }
}
