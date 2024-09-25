package com.example.bankapp.data.entity;

import jakarta.persistence.*;

@Entity
public class LoanRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private UserLoanInf userLoan;

    private boolean loanDecision;
    private int loanAmountDecision;
    private int loanTime;

    public LoanRequest(UserLoanInf userLoanInf) {
        setUserLoan(userLoanInf);
    }


    public LoanRequest() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserLoanInf getUserLoan() {
        return userLoan;
    }

    public void setUserLoan(UserLoanInf userLoan) {
        this.userLoan = userLoan;
    }

    public boolean isLoanDecision() {
        return loanDecision;
    }

    public void setLoanDecision(boolean loanDecision) {
        this.loanDecision = loanDecision;
    }

    public int getLoanAmountDecision() {
        return loanAmountDecision;
    }

    public void setLoanAmountDecision(int loanAmountDecision) {
        this.loanAmountDecision = loanAmountDecision;
    }

    public int getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(int loanTime) {
        this.loanTime = loanTime;
    }

}
