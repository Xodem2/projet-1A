package com.example.projet1a.ado_games;

import junit.framework.TestCase;

public class MemoTest extends TestCase {
    public void testAns() {
        Memo memo = new Memo();
        assert memo.ans() == memo.getA()+memo.getN();
    }

    public void testInput() {
        Memo memo = new Memo();
        for(int i = 0; i < 5; i++) {
            int ans = memo.ans();
            System.out.println(memo.toString());
            assert !memo.input(ans+1);
            assert memo.input(ans);
        }
    }
}