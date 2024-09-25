package com.example.bankapp.controllers;

import com.example.bankapp.data.entity.Contract;
import com.example.bankapp.data.entity.LoanRequest;
import com.example.bankapp.data.entity.UserLoanInf;
import com.example.bankapp.data.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class MainController {
    @Autowired
    CreditService creditService;

    @GetMapping(value = "/new")
    public String loanApplication() {
        return "loanRequest";
    }

    @PostMapping("/submit-application")
    public String submitApplication(@ModelAttribute UserLoanInf userLoanInf, Model model) {
        LoanRequest loanRequest = creditService.makeDecision(userLoanInf);
        model.addAttribute("loanApplication", loanRequest);
        if (loanRequest.getLoanAmountDecision() == 0)
            return "applicationFailure";
        else
            return "applicationSuccess";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/signСontract/{contractId}")
    public String signContract(@PathVariable Integer contractId, Model model) {
        Contract contract = creditService.findOrCreateContractById(contractId);
        model.addAttribute("contract", contract);
        return "signContract";
    }

    @PostMapping("/sign-contract")
    @ResponseBody
    public void signContract(@RequestParam("intValue") int requestId) {
        Contract contract = creditService.findOrCreateContractById(requestId);
        creditService.sighDateContract(contract);

    }

    @GetMapping("/clients")
    public String clients() {
        return "clients";
    }

    @GetMapping("/approved-applications")
    public String approvedApplications() {
        return "approved-applications";
    }

    @GetMapping("/signed-contracts")
    public String signedСontracts() {
        return "signed-contracts";
    }

    @GetMapping("/search-client")
    public String searchClient() {
        return "search-client";
    }


}
