package com.example.bankapp.data.service;

import com.example.bankapp.data.dao.ContractDAO;
import com.example.bankapp.data.dao.LoanRequestDAO;
import com.example.bankapp.data.dao.UserLoanInfDAO;
import com.example.bankapp.data.entity.Contract;
import com.example.bankapp.data.entity.LoanRequest;
import com.example.bankapp.data.entity.UserLoanInf;
import com.example.bankapp.util.RandomDecisionGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CreditService {
    @Autowired
    LoanRequestDAO loanRequestDAO;
    @Autowired
    UserLoanInfDAO userLoanInfDAO;
    @Autowired
    ContractDAO contractDAO;

    public LoanRequest makeDecision(UserLoanInf userLoanInf) {
        userLoanInfDAO.saveCustomer(userLoanInf);
        LoanRequest loanRequest = new LoanRequest(userLoanInf);
        RandomDecisionGenerate.fillRequest(loanRequest);
        loanRequestDAO.saveCustomer(loanRequest);
        return loanRequest;
    }

    public Contract findOrCreateContractById(int id) {
        Contract contract = contractDAO.getContractByRequsetId(id);
        if (contract == null) {
            contract = new Contract();
            contract.setLoanRequest(loanRequestDAO.getLoanRequest(id));
            contractDAO.saveContract(contract);
        }
        return contract;
    }

    public void sighDateContract(Contract contract) {
        contract.setSignatureStatus(true);
        contract.setDateSigning(LocalDate.now());
        contractDAO.saveContract(contract);
    }

    public List<UserLoanInf> findAllUserLoanInf() {
        return userLoanInfDAO.getAllRows();
    }

    public List<LoanRequest> findAllApprovedRequest() {
        return loanRequestDAO.getAllRows();
    }

    public List<Contract> findSignedContract() {
        return contractDAO.getSighnedContract();
    }

    public List<UserLoanInf> searchByInf(String inf) {
        return userLoanInfDAO.searchUserByString(inf);
    }
}
