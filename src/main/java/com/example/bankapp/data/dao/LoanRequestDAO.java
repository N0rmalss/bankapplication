package com.example.bankapp.data.dao;

import com.example.bankapp.data.entity.LoanRequest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoanRequestDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public List<LoanRequest> getAllRows() {
        List<LoanRequest> listOfEntities = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<LoanRequest> query = session.createQuery("FROM LoanRequest o WHERE o.loanDecision = true ", LoanRequest.class);
        listOfEntities = query.list();
        session.getTransaction().commit();
        session.close();
        return listOfEntities;
    }

    public LoanRequest getLoanRequest(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        LoanRequest loanRequest = session.get(LoanRequest.class, id);
        session.getTransaction().commit();
        session.close();
        return loanRequest;
    }

    public void saveCustomer(LoanRequest userLoanInf) {
        Session currentSession = sessionFactory.openSession();
        currentSession.beginTransaction();
        currentSession.saveOrUpdate(userLoanInf);
        currentSession.getTransaction().commit();
        currentSession.close();
    }

}
