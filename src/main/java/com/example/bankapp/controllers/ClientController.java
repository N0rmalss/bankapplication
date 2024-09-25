package com.example.bankapp.controllers;

import com.example.bankapp.data.entity.Contract;
import com.example.bankapp.data.entity.LoanRequest;
import com.example.bankapp.data.entity.UserLoanInf;
import com.example.bankapp.data.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {
    @Autowired
    CreditService creditService;

    @GetMapping("/api/clients")
    public List<UserLoanInf> getClients() {
        return creditService.findAllUserLoanInf();
    }

    @GetMapping("/api/applications/approved")
    public List<LoanRequest> getApprovedApplications() {
        return creditService.findAllApprovedRequest();
    }

    @GetMapping("/api/contracts/signed")
    public List<Contract> getSignedApplications() {
        return creditService.findSignedContract();
    }

    @GetMapping("/api/clients/search")
    public List<UserLoanInf> searchClients(@RequestParam String term) {
        return creditService.searchByInf(term);
    }

}
