package com.example.bankapp.data.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate dateSigning;
    private boolean signatureStatus;
    @OneToOne
    private LoanRequest loanRequest;

    public LocalDate getDateSigning() {
        return dateSigning;
    }

    public void setDateSigning(LocalDate dateSigning) {
        this.dateSigning = dateSigning;
    }

    public boolean isSignatureStatus() {
        return signatureStatus;
    }

    public void setSignatureStatus(boolean signatureStatus) {
        this.signatureStatus = signatureStatus;
    }

    public LoanRequest getLoanRequest() {
        return loanRequest;
    }

    public void setLoanRequest(LoanRequest loanRequest) {
        this.loanRequest = loanRequest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
