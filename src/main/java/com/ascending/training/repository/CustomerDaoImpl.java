package com.ascending.training.repository;

import com.ascending.training.model.Customer;
import com.ascending.training.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public long save(Customer customer) {
        Transaction transaction = null;
        long customerId = 0;
        Boolean isSuccess = true;

        logger.warn("Before getSessionFactory.openSession().");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            logger.warn("Enter getSessionFactory.openSession().");
            transaction = session.beginTransaction();

            logger.warn("Enter beginTransaction().");
            customerId = (long) session.save(customer);

            logger.warn("session.save() completes.");
            transaction.commit();
        } catch (Exception e) {
            isSuccess = false;
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage());
        }

        if (isSuccess) {
            logger.warn(String.format("The customer %s has been inserted into the table.", customer.toString()));
        }

        return customerId;
    }

    @Override
    public boolean update(Customer customer) {
        Transaction transaction = null;
        Boolean isSuccess = true;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(customer);
            transaction.commit();
        } catch (Exception e) {
            isSuccess = false;
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage());
        }

        if (isSuccess) {
            logger.warn(String.format("The customer %s has been updated.", customer.toString()));
        }

        return isSuccess;
    }

    @Override
    public boolean delete(String customerName) {
        String hql = "delete Customer where name = :customerName";         //  Placeholder with a name, not anonymous
        int deletedCount = 0;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Customer> query = session.createQuery(hql);
            query.setParameter("customerName", customerName);
            transaction = session.beginTransaction();
            deletedCount = query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage());
        }

        logger.warn(String.format("The customer %s has been deleted.", customerName));

        return deletedCount >= 1 ? true : false;
    }

    @Override
    public List<Customer> getCustomers() {
        String hql = "from Customer";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Customer> query = session.createQuery(hql);
            return query.list();
        }
    }

    @Override
    public Customer getCustomerByName(String customerName) {
        if (customerName == null) {
            return null;
        }

        String hql = "from Customer as ct " +
                "left join fetch ct.issueStatuses as is " +
                "where lower(ct.name) = :customerName";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Customer> query = session.createQuery(hql);
            query.setParameter("customerName", customerName.toLowerCase());

            Customer customer = query.uniqueResult();
            logger.warn(customer.toString());

            return customer;
        }
    }
}
