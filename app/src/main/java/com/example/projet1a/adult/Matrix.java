package com.example.projet1a.adult;

import java.util.ArrayList;
import java.util.Random;

public class Matrix {
    private int m;
    private int n;
    private int[][] M;
    private Random random;
    private int nMin = -9;
    private int nMax = 9;
    private int n_props = 3;
    public Matrix(int m, int n) {
        this.m = m; //m lignes
        this.n = n; //n colonnes
        this.M = new int[m][n];
        this.random = new Random();
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
        if(m != n)
            return false;
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

    public float detAns() {
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
