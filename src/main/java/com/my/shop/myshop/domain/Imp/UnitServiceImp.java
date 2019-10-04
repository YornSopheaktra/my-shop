package com.my.shop.myshop.domain.Imp;

import com.my.shop.myshop.domain.UnitService;
import com.my.shop.myshop.entity.Unit;
import com.my.shop.myshop.service.process.imp.UnitProcessorImp;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitServiceImp implements UnitService {
    @Autowired
    private SessionFactory sessionFactory;

    private Logger log = LoggerFactory.getLogger(UnitProcessorImp.class);

    @Override
    public List<Unit> getAllUnit() {
        Session session=sessionFactory.openSession();;
        try {
            String sql = "FROM Unit where status='active'";
            Query query = session.createQuery(sql);
            return query.list();

        }catch (Exception e){
            log.error("{}",e);
            throw e;
        }finally {
            session.close();
        }
    }

    @Override
    public Unit getUnitById(Integer id) {
        Session session=sessionFactory.openSession();
        try {
            String sql = "FROM Unit where status='active' AND unitId= :id";
            Query query = session.createQuery(sql);
            query.setParameter("id",id);
            query.setMaxResults(1);
            return (Unit) query.uniqueResult();

        }catch (Exception e){
            log.error("{}",e);
            throw e;
        }finally {
            session.close();
        }
    }

    @Override
    public Unit saveOrUpdate(Unit unit) {
        Transaction txn=null;
        Session session=sessionFactory.openSession();
        try{
            txn = session.beginTransaction();
            session.saveOrUpdate(unit);
            txn.commit();
        }catch (Exception e){
            log.error("{}",e);
            txn.rollback();
            throw e;
        }finally {
            session.close();
        }
        return unit;
    }
}
