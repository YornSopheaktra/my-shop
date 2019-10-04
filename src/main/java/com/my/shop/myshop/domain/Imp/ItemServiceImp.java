package com.my.shop.myshop.domain.Imp;

import com.my.shop.myshop.domain.ItemService;
import com.my.shop.myshop.entity.Item;
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
public class ItemServiceImp implements ItemService {

    private Logger log = LoggerFactory.getLogger(ItemServiceImp.class);

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<Item> getAllItem() {
        Session session=sessionFactory.openSession();;
        try {
            String sql = "FROM Item where status='active'";
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
    public Item getItemById(Integer id) {
        Session session=sessionFactory.openSession();
        try {
            String sql = "FROM Item where status='active' AND itemId= :id";
            Query query = session.createQuery(sql);
            query.setParameter("id",id);
            query.setMaxResults(1);
            return (Item) query.uniqueResult();

        }catch (Exception e){
            log.error("{}",e);
            throw e;
        }finally {
            session.close();
        }
    }

    @Override
    public Item saveOrUpdate(Item item) {
        Transaction txn=null;
        Session session=sessionFactory.openSession();
        try{
            txn = session.beginTransaction();
            session.saveOrUpdate(item);
            txn.commit();
        }catch (Exception e){
            log.error("{}",e);
            txn.rollback();
            throw e;
        }finally {
            session.close();
        }
        return item;
    }
}
