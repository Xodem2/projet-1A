package com.example.projet1a.adult;

import com.example.projet1a.list.ListNumbers;

import java.util.ArrayList;
import java.util.Random;

public class Matrix {
    private int m;
    private int n;
    private int[][] M;
    private Random random;
    private static final int nMin = -9;
    private static final int nMax = 9;
    private static final int nProps = 3;
    private int correct;
    public Matrix() {
        this.random = new Random();
        this.m = this.random.nextInt(2)+2; //m lignes
        this.n = this.m; //n colonnes
        this.M = new int[this.m][this.n];
        this.generate();
    }

    public Matrix(int m, int n) {
        this.random = new Random();
        this.m = m; //m lignes
        this.n = n; //n colonnes
        this.M = new int[this.m][this.n];
        this.generate();
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                str += get(i, j) + " ";
            }
            str += "\n";
        }
        str += "\n";
        return str;
    }

    public int getCorrect() {
        return correct;
    }

    public void set(int m, int n, int value) {
        this.M[m][n] = value;
    }

    public int get(int m, int n) {
        return this.M[m][n];
    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    public int getNMin() {
        return nMin;
    }

    public int getNMax() {
        return nMax;
    }

    public int getNProps() { return nProps; }

    public Matrix getMatrix() {
        Matrix M = new Matrix(this.m, this.n);
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                M.set(i, j, this.get(i, j));
        return M;
    }

    public boolean is(Matrix N) {
        int m = this.getM();
        int n = this.getN();
        if(m != n) {
            return false;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(this.get(i, j) != N.get(i, j))
                    return false;
            }
        }
        return true;
    }

    public Matrix sum(Matrix N) {
        int m = this.getM();
        int n = this.getN();
        assert m == N.getM() && n == N.getN();
        Matrix P = new Matrix(m, n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int c = this.get(i, j)+N.get(i, j);
                P.set(i, j, c);
            }
        }
        return P;
    }

    public Matrix minus(Matrix N) {
        int m = this.getM();
        int n = this.getN();
        assert m == N.getM() && n == N.getN();
        Matrix P = new Matrix(m, n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int c = this.get(i, j)-N.get(i, j);
                P.set(i, j, c);
            }
        }
        return P;
    }

    public Matrix prod(Matrix N) {
        int m = this.getM();
        int n = this.getN();
        assert n == N.getM();
        int p = N.getN();
        Matrix P = new Matrix(m, p);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < p; j++) {
                int c = 0;
                for(int k = 0; k < n; k++) {
                    c += this.get(i, k)*N.get(k, j);
                }
                P.set(i, j, c);
            }
        }
        return P;
    }

    public Matrix t() {
        int m = this.getM();
        int n = this.getN();
        Matrix tM = new Matrix(n, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tM.set(i, j, this.get(j, i));
            }
        }
        return tM;
    }

    public boolean trans(Matrix M) {
        return this.t().is(M);
    }

    public int detAns() {
        assert this.getM() <= 3;
        assert this.getN() <= 3;
        assert m == n;
        int d = 0;
        if(m == 1) {
            d = this.get(0, 0);
        }
        else if(m == 2) {
            d = this.get(0, 0)*this.get(1, 1)-this.get(1, 0)*this.get(0, 1);
        }
        else if(m == 3) {
            d = this.get(0, 0)*this.get(1, 1)*this.get(2, 2)
                    +this.get(1, 0)*this.get(2, 1)*this.get(0, 2)
                    +this.get(2, 0)*this.get(0, 1)*this.get(1, 2)
                    -this.get(0, 2)*this.get(1, 1)*this.get(2, 0)
                    -this.get(2, 2)*this.get(0, 1)*this.get(1, 0)
                    -this.get(0, 0)*this.get(1, 2)*this.get(2, 1);
        }
        return d;
    }

    public ListNumbers propDet() {
        ListNumbers choices = new ListNumbers(this.nProps);
        int correctProp = detAns();
        int correctPropPos = this.random.nextInt(this.nProps);
        for(int i = 0; i < nProps; i++) {
            if(i == correctPropPos)
                choices.add(correctProp);
            else {
                int falseProp;
                do {
                    falseProp = correctProp;
                    if(this.random.nextInt(2) == 0) {
                        falseProp = -falseProp;
                    }
                    if(this.random.nextInt(2) == 0) {
                        falseProp = falseProp+this.random.nextInt(3)+1;
                    }
                    if(this.random.nextInt(2) == 0) {
                        falseProp = falseProp-this.random.nextInt(3)-1;
                    }
                } while(choices.contains(falseProp) || falseProp == correctProp);
                choices.add(falseProp);
            }
        }
        return choices;
    }

    public ArrayList<Matrix> propTrans() {
        ArrayList<Matrix> choices = new ArrayList<>();
        Matrix correctProp = t().getMatrix();
        int correctPropPos = this.random.nextInt(nProps);
        this.correct = correctPropPos;
        for (int i = 0; i < nProps; i++) {
            if (i == correctPropPos)
                choices.add(correctProp.getMatrix());
            else {
                Matrix falseProp;
                do {
                    falseProp = new Matrix(this.getM(), this.getN());
                    int r = this.random.nextInt(4);
                    if(r == 0) {
                        for (int j = 0; j < falseProp.getM(); j++) {
                            for (int k = 0; k < falseProp.getN(); k++) {
                                falseProp.set(j, k, this.get(k, falseProp.getM() - j - 1));
                            }
                        }
                    }
                    else if(r == 1) {
                        for (int j = 0; j < falseProp.getM(); j++) {
                            for (int k = 0; k < falseProp.getN(); k++) {
                                falseProp.set(j, k, this.get(falseProp.getN() - k - 1, j));
                            }
                        }
                    }
                    else if(r == 2) {
                        for (int j = 0; j < falseProp.getM(); j++) {
                            for (int k = 0; k < falseProp.getN(); k++) {
                                falseProp.set(j, k, this.get(falseProp.getN() - k - 1, falseProp.getM() - j - 1));
                            }
                        }
                    }
                    else if(r == 3) {
                        for (int j = 0; j < falseProp.getM(); j++) {
                            falseProp.set(j, j, this.get(j, j));
                        }
                    }
                } while (falseProp.inArray(choices) || falseProp.is(correctProp.getMatrix()));
                choices.add(falseProp);
            }
        }
        return choices;
    }

    public boolean det(int d) {
        return d == detAns();
    }

    public void generate() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                this.set(i, j, this.random.nextInt(nMax-nMin+1)+nMin);
            }
        }
    }

    public boolean inArray(ArrayList<Matrix> ML) {
        for(int i = 0; i < ML.size(); i++)
            if(ML.get(i).is(this.getMatrix()))
                return true;
        return false;
    }
}
