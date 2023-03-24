package com.example.demo.repository;

import java.util.Optional;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.example.demo.model.query.OrderQuery;


public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    static final String baseSelect = """
        select 
            o.orderNumber, 
            o.orderDate, 
            o.requiredDate, 
            o.shippedDate, 
            o.status, 
            o.comments, 
            o.customerNumber, 
            c.customerName
        from Orders o 
        left join Customers c on o.customerNumber = c.customerNumber 
        """;

    @Override    
    public Optional<OrderQuery> findQueryById(Long orderNumber) {        
        String sql = baseSelect + """
            where o.orderNumber = :orderNumber 
            """;

        Query query = entityManager.createNativeQuery(sql, OrderQuery.class);
        query.setParameter("orderNumber", orderNumber);

        @SuppressWarnings("unchecked")
        List<OrderQuery> orders = query.getResultList();
        Optional<OrderQuery> result = Optional.empty();
        if( orders.size() > 0) {
            result = Optional.of(orders.get(0));
        }
        return result;
    }


    @Override    
    public List<OrderQuery> findQueryByCustomerNumber(Long customerNumber) {
        String sql = baseSelect + """
            where o.customerNumber = :customerNumber
            order by o.orderNumber
            """;

        Query query = entityManager.createNativeQuery(sql, OrderQuery.class);
        query.setParameter("customerNumber", customerNumber);

        @SuppressWarnings("unchecked")
        List<OrderQuery> orders = query.getResultList();
        return orders;
    }


    @Override 
    public List<OrderQuery> findQueryAll() {
        String sql = baseSelect + """
            order by o.orderNumber
            """;

        Query query = entityManager.createNativeQuery(sql, OrderQuery.class);
        
        @SuppressWarnings("unchecked")
        List<OrderQuery> orders = query.getResultList();
        return orders;
    }

}
