package com.ascending.training.repository;

import com.ascending.training.model.Book;
import com.ascending.training.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean save(Book book) {
        Transaction transaction = null;
        Boolean isSuccess = true;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
        } catch (Exception e) {
            isSuccess = false;
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage());
        }

        if (isSuccess) {
            logger.debug(String.format("The book %s has been inserted into the table.", book.toString()));
        }

        return isSuccess;
    }

    @Override
    public boolean update(Book book) {
        Transaction transaction = null;
        Boolean isSuccess = true;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(book);
            transaction.commit();
        } catch (Exception e) {
            isSuccess = false;
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage());
        }

        if (isSuccess) {
            logger.warn(String.format("The book %s has been updated.", book.toString()));
        }

        return isSuccess;
    }

    @Override
    public boolean delete(String bookName) {
        String hql = "DELETE Book where name = :bookName1";         //  Placeholder with a name, not anonymous
        int deletedCount = 0;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Book> query = session.createQuery(hql);
            query.setParameter("bookName1", bookName);
            transaction = session.beginTransaction();
            deletedCount = query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage());
        }

        logger.debug(String.format("The book %s has been deleted.", bookName));

        return deletedCount >= 1 ? true : false;
    }

    @Override
    public List<Book> getBooks() {
        List<Book> res = new ArrayList<>();

        return res;
    }

    @Override
    public Book getBookByName(String bookName) {
        if (bookName == null) {
            return null;
        }

        String hql = "FROM Book as bk where lower(bk.name) = :name";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Book> query = session.createQuery(hql);
            query.setParameter("name", bookName.toLowerCase());

            Book book = query.uniqueResult();
            logger.debug(book.toString());

            return book;
        }
    }

}
