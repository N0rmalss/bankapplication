package com.example.bankapp.util;

import com.example.bankapp.data.entity.LoanRequest;

import java.util.Random;

public class RandomDecisionGenerate {

    private static final double PROBABILITYAPPROVAL = 0.7;
    private static final int MINCREDIT = 10000;
    private static final int MINTIME = 1;
    private static final int MAXTIME = 12;

    static Random rand = new Random();

    private static boolean makeDecision() {
        return rand.nextDouble() < PROBABILITYAPPROVAL;
    }

    private static int makeAmount(int max) {
        return rand.nextInt((max - MINCREDIT) + 1) + MINCREDIT;
    }

    private static int makeTime(){
        return rand.nextInt((MAXTIME - MINTIME) + 1) + MINTIME;
    }

    public static LoanRequest fillRequest(LoanRequest loanRequest) {

        if (makeDecision()) {
            loanRequest.setLoanDecision(true);
            loanRequest.setLoanAmountDecision(makeAmount(loanRequest.getUserLoan().getLoanAmount()));
            loanRequest.setLoanTime(makeTime());
        }
        else {
            loanRequest.setLoanDecision(false);
            loanRequest.setLoanAmountDecision(0);
            loanRequest.setLoanTime(0);
        }
        return loanRequest;
    }

}
