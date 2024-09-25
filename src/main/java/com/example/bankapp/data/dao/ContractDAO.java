package com.example.bankapp.data.dao;

import com.example.bankapp.data.entity.Contract;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContractDAO {
    @Autowired
    SessionFactory sessionFactory;

    public void saveContract(Contract contract) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(contract);
        session.getTransaction().commit();
        session.close();
    }

    public List<Contract> getSighnedContract() {
        List<Contract> listOfEntities = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Contract> query = session.createQuery("FROM Contract o WHERE o.signatureStatus = true ", Contract.class);
        listOfEntities = query.list();
        session.getTransaction().commit();
        session.close();
        return listOfEntities;
    }

    public Contract getContractByRequsetId(int id) {
        String hql = "FROM Contract o WHERE o.loanRequest.id = :paramName";
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Contract> query = session.createQuery(hql, Contract.class);
        query.setParameter("paramName", id);
        Contract contract;
        try {
            contract = query.getResultList().get(0);
        } catch (IndexOutOfBoundsException e) {
            contract = null;
        }
        session.getTransaction().commit();
        session.close();
        return contract;
    }
}
