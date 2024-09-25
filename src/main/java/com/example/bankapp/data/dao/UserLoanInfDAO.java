package com.example.bankapp.data.dao;

import com.example.bankapp.data.entity.UserLoanInf;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserLoanInfDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public List<UserLoanInf> getAllRows() {
        List<UserLoanInf> listOfEntities = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<UserLoanInf> query = session.createQuery("FROM UserLoanInf", UserLoanInf.class);
        listOfEntities = query.list();
        session.getTransaction().commit();
        session.close();
        return listOfEntities;
    }

    public void saveCustomer(UserLoanInf userLoanInf) {
        Session currentSession = sessionFactory.openSession();
        currentSession.beginTransaction();
        currentSession.saveOrUpdate(userLoanInf);
        currentSession.getTransaction().commit();
        currentSession.close();
    }

    public List<UserLoanInf> searchUserByString(String string) {
        List<UserLoanInf> listOfEntities = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<UserLoanInf> query = session.createQuery("FROM UserLoanInf u WHERE u.fullName = :mystring or u.phone = :mystring or u.passport = :mystring", UserLoanInf.class);
        query.setParameter("mystring", string);
        listOfEntities = query.list();
        session.getTransaction().commit();
        session.close();
        return listOfEntities;
    }

}
