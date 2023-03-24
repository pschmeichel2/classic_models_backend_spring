package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.example.demo.model.Product;
import com.example.demo.model.query.ProductLineName;
import com.example.demo.model.query.ProductVendor;

public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override    
    public List<String> getProductLines() {
        String sql = """
            select distinct(productLine) as productLineName
            from products
            order by 1                    
            """;

        Query query = entityManager.createNativeQuery(sql, ProductLineName.class);

        @SuppressWarnings("unchecked")
        List<ProductLineName> productLineNames = query.getResultList();
        
        List<String> result = new ArrayList<String>();
        for(ProductLineName productLineName: productLineNames) {
            result.add(productLineName.getProductLineName());
        }
        return result;
    }

    @Override  
    public List<String> getProductVendors() {
        String sql = """
            select distinct(productVendor) as productVendor
            from products
            order by 1                    
            """;

        Query query = entityManager.createNativeQuery(sql, ProductVendor.class);

        @SuppressWarnings("unchecked")
        List<ProductVendor> productVendors = query.getResultList();
        
        List<String> result = new ArrayList<String>();
        for(ProductVendor productVendor: productVendors) {
            result.add(productVendor.getProductVendor());
        }
        return result;
    }

    @Override  
    public List<Product> getProducts(
        String productCode, String productName, 
        String productLine, String productVendor) {
        String where = "";
        if( !isNullOrBlank(productCode) ||
            !isNullOrBlank(productName) ||
            !isNullOrBlank(productLine) ||
            !isNullOrBlank(productVendor)) {
            
            if( !isNullOrBlank(productCode)) {
                where += " productCode like :productCode ";
            }

            if( !isNullOrBlank(productName)) {
                if( where.length() > 0 ) {
                    where += " and ";
                }
                where += " productName like :productName ";
            }
            if( !isNullOrBlank(productLine)) {
                if( where.length() > 0 ) {
                    where += " and ";
                }
                where += " productLine like :productLine ";
            }
            if( !isNullOrBlank(productVendor)) {
                if( where.length() > 0 ) {
                    where += " and ";
                }
                where += " productVendor like :productVendor ";
            }

            where = " where " + where;
        }

        String sql = """
            select
            c.productCode, 
            c.productName, 
            c.productLine, 
            c.productScale, 
            c.productVendor, 
            c.productDescription, 
            c.quantityInStock, 
            c.buyPrice, 
            c.MSRP
            from Products c 
            """
            + where +         
            "order by c.productCode";
            
        Query query = entityManager.createNativeQuery(sql, Product.class);

        if( !isNullOrBlank(productCode)) {
            query.setParameter("productCode", "%"+productCode.trim()+"%");
        }
        if( !isNullOrBlank(productName)) {
            query.setParameter("productName", "%"+productName.trim()+"%");
        }
        if( !isNullOrBlank(productLine)) {
            query.setParameter("productLine", "%"+productLine.trim()+"%");
        }
        if( !isNullOrBlank(productVendor)) {
            query.setParameter("productVendor", "%"+productVendor.trim()+"%");
        }

        @SuppressWarnings("unchecked")
        List<Product> result = query.getResultList();
        
        return result;
    }

    static boolean isNullOrBlank(String s) {
        return (s==null || s.isBlank());
    }
    
}
