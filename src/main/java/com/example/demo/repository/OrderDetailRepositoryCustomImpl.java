package com.example.demo.repository;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.example.demo.model.query.OrderDetailQuery;

public class OrderDetailRepositoryCustomImpl implements OrderDetailRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    static final String baseSelect = """
            select
                d.orderNumber,
                d.productCode,
                d.quantityOrdered,
                d.priceEach,
                d.orderLineNumber,
                p.productName,
                c.customerName
            from OrderDetails d
            left join Products p on d.productCode = p.productCode
            left join Orders o on d.orderNumber = o.orderNumber
            left join Customers c on c.customerNumber = o.customerNumber
            """;

    @Override
    public List<OrderDetailQuery> findQueryByProductCode(String productCode) {
        String sql = baseSelect + """
                where d.productCode = :productCode
                order by d.orderNumber, d.orderLineNumber
                """;

        Query query = entityManager.createNativeQuery(sql, OrderDetailQuery.class);
        query.setParameter("productCode", productCode);

        @SuppressWarnings("unchecked")
        List<OrderDetailQuery> orderDetails = query.getResultList();
        return orderDetails;
    };

    @Override
    public List<OrderDetailQuery> findQueryByOrderNumber(Long orderNumber) {
        String sql = baseSelect + """
                where d.orderNumber = :orderNumber
                order by d.orderNumber, d.orderLineNumber
                """;

        Query query = entityManager.createNativeQuery(sql, OrderDetailQuery.class);
        query.setParameter("orderNumber", orderNumber);

        @SuppressWarnings("unchecked")
        List<OrderDetailQuery> orderDetails = query.getResultList();
        return orderDetails;
    };

    @Override
    public List<OrderDetailQuery> findQueryAll() {
        String sql = baseSelect + """
                order by d.orderNumber, d.orderLineNumber
                """;

        Query query = entityManager.createNativeQuery(sql, OrderDetailQuery.class);

        @SuppressWarnings("unchecked")
        List<OrderDetailQuery> orderDetails = query.getResultList();
        return orderDetails;
    };

    @Override
    public Optional<OrderDetailQuery> findQueryByOrderNumberAndProductCode(Long orderNumber, String productCode) {
        String sql = baseSelect + """
                where d.orderNumber = :orderNumber and d.productCode = :productCode
                """;

        Query query = entityManager.createNativeQuery(sql, OrderDetailQuery.class);
        query.setParameter("orderNumber", orderNumber);
        query.setParameter("productCode", productCode);

        @SuppressWarnings("unchecked")
        List<OrderDetailQuery> orderDetails = query.getResultList();

        Optional<OrderDetailQuery> result = Optional.empty();
        if (orderDetails.size() > 0) {
            result = Optional.of(orderDetails.get(0));
        }
        return result;
    };

}
