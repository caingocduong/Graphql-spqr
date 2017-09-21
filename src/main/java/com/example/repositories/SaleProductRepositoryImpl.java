package com.example.repositories;

import com.example.connections.PageInfo;
import com.example.connections.SaleProductConnection;
import com.example.connections.SaleProductEdge;
import com.example.models.SaleProduct;
import io.leangen.graphql.execution.relay.Page;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.DatatypeConverter;

@Repository
@Transactional
public class SaleProductRepositoryImpl implements SaleProductRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(SaleProductRepositoryImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public SaleProductRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession(){

        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void saveNewProduct(SaleProduct saleProduct) {

        currentSession().merge(saleProduct);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SaleProduct> retrieveAllNewProduct() {

        return currentSession().createQuery("from "+SaleProduct.class.getName()).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SaleProduct> allNewProductsPagination(int pageSize, int pageIndex) {
        Criteria criteria = currentSession().createCriteria(SaleProduct.class);
        //criteria.addOrder(Order.desc("price"));
        criteria.setFirstResult((pageIndex-1)*pageSize);
        criteria.setMaxResults(pageSize);

        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SaleProduct> allNewProductFilter(String titleFilter, int pageSize, int pageIndex) {
        Criteria criteria = currentSession().createCriteria(SaleProduct.class);
        criteria.add(Restrictions.like("title","%"+titleFilter+"%"));
        criteria.addOrder(Order.desc("price"));
        criteria.setFirstResult((pageIndex-1)*pageSize);
        criteria.setMaxResults(pageSize);

        return criteria.list();
    }

    @Override
    public SaleProduct findNewProductById(long id) {

        return currentSession().load(SaleProduct.class, id);
    }


    @Override
    public void updateNewProduct(long id, SaleProduct saleProduct) {
        SaleProduct saleProductToSave = currentSession().load(SaleProduct.class, id);

        saleProductToSave.setImg(saleProduct.getImg() == null ? saleProductToSave.getImg() : saleProduct.getImg());
        saleProductToSave.setTitle(saleProduct.getTitle() == null ? saleProductToSave.getTitle() : saleProduct.getTitle());
        saleProductToSave.setDescription(saleProduct.getDescription() == null ? saleProductToSave.getDescription() : saleProduct.getDescription());
        saleProductToSave.setDiscount(saleProduct.getDiscount() == 0 && saleProduct.getOriginalPrice() == saleProduct.getPrice()? saleProductToSave.getDiscount() : saleProduct.getDiscount());
        saleProductToSave.setOriginalPrice(saleProduct.getOriginalPrice() == 0.0 ? saleProductToSave.getOriginalPrice() : saleProduct.getOriginalPrice());
        saleProductToSave.setPrice(saleProduct.getPrice() == 0.0 ? saleProductToSave.getPrice() : saleProduct.getPrice());

        currentSession().update(saleProductToSave);
    }

    @Override
    public SaleProduct deleteNewProduct(long id) {
        SaleProduct saleProductToDelete = currentSession().load(SaleProduct.class, id);
        currentSession().delete(saleProductToDelete);

        return saleProductToDelete;
    }

    @Override
    public SaleProductConnection getAllSaleProductPagination(String before, String after, int first, int last) throws Exception {
        SaleProductConnection productConnection = new SaleProductConnection();
        List<SaleProduct> saleProducts = retrieveAllNewProduct();
        List<SaleProductEdge> saleProductEdges = new ArrayList<>();
        int i=0;
        for(SaleProduct node : saleProducts){
            //String cursor = DatatypeConverter.printBase64Binary(createSalt());
            String cursor = ""+ i++;
            PageInfo pageInfo = new PageInfo();
            SaleProductEdge saleProductEdge = new SaleProductEdge(node,cursor);
            saleProductEdges.add(saleProductEdge);
            productConnection.setPageInfo(pageInfo);
        }

        productConnection.setCount(saleProducts.size());
        productConnection.setEdges(productConnection.edgesToReturned(saleProductEdges,before,after,first,last));

        return productConnection;
    }

    private  byte[] createSalt(){
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[24];
        secureRandom.nextBytes(salt);

        return salt;
    }
}
