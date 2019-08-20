package com.ascending.training.repository;

import com.ascending.training.model.Customer;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CustomerDaoTest {
    private static CustomerDao customerDao;
    private static Logger logger = LoggerFactory.getLogger(CustomerDaoTest.class);

    @BeforeClass
    public static void beforeClass() {
        customerDao = new CustomerDaoImpl();
    }

    @AfterClass
    public static void afterClass() {
        customerDao = null;
    }

    @Before
    public void before() {
        Customer fCustomer = new Customer("Timothy", "255 A St NE, Washington, DC");
        long fRet = customerDao.save(fCustomer);
        Customer sCustomer = new Customer("Joe", "6787 Washington Blvd, Arlington, VA");
        long sRet = customerDao.save(sCustomer);
    }

    @After
    public void after() {
        Boolean fret = customerDao.delete("Joe");
        Boolean sret = customerDao.delete("Timothy");
    }

    @Test
    public void save() {
        Customer newCustomer = new Customer("Kenton", "2111 Jefferson Davis Hwy APT 703N, Arlington, VA");
        long ret = customerDao.save(newCustomer);
        Assert.assertNotNull(ret);

        customerDao.delete(newCustomer.getName());
        logger.info("First Test!");
    }

    @Test
    public void getCustomers() {
        List<Customer> customers = customerDao.getCustomers();

        for (Customer customer : customers) {
            System.out.println(customer);
        }

        Assert.assertNotEquals(0, customers.size());

        logger.info("Second Test!");
    }

    @Test
    public void delete() {
        Boolean ret = customerDao.delete("Joe");

        Assert.assertEquals(true, ret);

        logger.info("Third Test!");
    }

    @Test
    public void update() {
        Customer updatedCustomer = new Customer("Kenton", "3444 Fairfax Dr, Arlington, VA");
        Boolean ret = customerDao.update(updatedCustomer);

        Assert.assertEquals(true, ret);

        logger.info("Fourth Test!");
    }

    @Test
    public void getCustomerByName() {
        Customer customer = customerDao.getCustomerByName("Joe");

        Assert.assertNotNull(customer);
    }

}