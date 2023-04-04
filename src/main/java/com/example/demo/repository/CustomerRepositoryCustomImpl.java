package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.example.demo.model.Customer;
import com.example.demo.model.query.CustomerBalanceLineQuery;
import com.example.demo.model.Country;

public class CustomerRepositoryCustomImpl implements CustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<String> getCountries() {
        String sql = """
                select distinct(country) as country
                from customers
                order by 1
                """;

        Query query = entityManager.createNativeQuery(sql, Country.class);

        @SuppressWarnings("unchecked")
        List<Country> countries = query.getResultList();

        List<String> result = new ArrayList<String>();
        for (Country country : countries) {
            result.add(country.getCountry());
        }
        return result;
    }

    // see https://www.baeldung.com/spring-data-jpa-query
    // see
    // https://www.baeldung.com/rest-api-search-language-spring-data-specifications
    // see https://www.baeldung.com/hibernate-criteria-queries
    // see https://vladmihalcea.com/spring-data-findall-anti-pattern/
    // see
    // https://medium.com/quick-code/spring-boot-how-to-design-efficient-search-rest-api-c3a678b693a0
    @Override
    public List<Customer> getCustomers(String customerName, String contactLastName,
            String contactFirstName, String country) {
        String where = "";
        if (!isNullOrBlank(customerName) ||
                !isNullOrBlank(contactLastName) ||
                !isNullOrBlank(contactFirstName) ||
                !isNullOrBlank(country)) {

            if (!isNullOrBlank(customerName)) {
                where += " customerName like :customerName ";
            }

            if (!isNullOrBlank(contactLastName)) {
                if (where.length() > 0) {
                    where += " and ";
                }
                where += " contactLastName like :contactLastName ";
            }
            if (!isNullOrBlank(contactFirstName)) {
                if (where.length() > 0) {
                    where += " and ";
                }
                where += " contactFirstName like :contactFirstName ";
            }
            if (!isNullOrBlank(country)) {
                if (where.length() > 0) {
                    where += " and ";
                }
                where += " country like :country ";
            }

            where = " where " + where;
        }

        String sql = """
                select
                c.customerNumber,
                c.customerName,
                c.contactLastName,
                c.contactFirstName,
                c.phone,
                c.addressLine1,
                c.addressLine2,
                c.city,
                c.state,
                c.postalCode,
                c.country,
                c.salesRepEmployeeNumber,
                c.creditLimit,
                e.lastName as salesRepEmployeeName
                from Customers c
                left join Employees e on c.salesRepEmployeeNumber = e.employeeNumber """
                + where +
                "order by c.customerNumber";

        Query query = entityManager.createNativeQuery(sql, Customer.class);

        if (!isNullOrBlank(customerName)) {
            query.setParameter("customerName", "%" + customerName.trim() + "%");
        }
        if (!isNullOrBlank(contactLastName)) {
            query.setParameter("contactLastName", "%" + contactLastName.trim() + "%");
        }
        if (!isNullOrBlank(contactFirstName)) {
            query.setParameter("contactFirstName", "%" + contactFirstName.trim() + "%");
        }
        if (!isNullOrBlank(country)) {
            query.setParameter("country", "%" + country.trim() + "%");
        }

        @SuppressWarnings("unchecked")
        List<Customer> result = query.getResultList();

        return result;
    }

    @Override
    public List<CustomerBalanceLineQuery> getCustomerBalanceLines(Long customerNumber) {
        String sql = """
                (
                    select
                        concat('Order ',o.orderNumber) as id,
                        coalesce(o.shippedDate, o.requiredDate, o.orderNumber, null) as transactionDate,
                        o.status,
                        coalesce((select sum(quantityOrdered * priceEach)
                            from orderdetails od
                            where od.ordernumber = o.orderNumber),0) * -1 as amount
                    from Orders o
                    where o.customerNumber = :customerNumber
                    union
                    select
                        concat('Payment ', customerNumber, '/',checkNumber) as id,
                        paymentDate as transactionDate,
                        'Payment' as status,
                        amount
                    from payments p
                    where p.customerNumber = :customerNumber
                    )
                    order by transactionDate
                    """;

        Query query = entityManager.createNativeQuery(sql, CustomerBalanceLineQuery.class);
        query.setParameter("customerNumber", customerNumber);

        @SuppressWarnings("unchecked")
        List<CustomerBalanceLineQuery> result = query.getResultList();

        return result;
    }

    static boolean isNullOrBlank(String s) {
        return (s == null || s.isBlank());
    }
}
