package com.example.repositories;

import com.example.models.NewProduct;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class NewProductRepositoryImpl implements NewProductRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewProductRepositoryImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public NewProductRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession(){

        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void saveNewProduct(NewProduct newProduct) {

        currentSession().merge(newProduct);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<NewProduct> retrieveAllNewProduct() {

        return currentSession().createQuery("from "+NewProduct.class.getName()).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<NewProduct> allNewProductsPagination(int pageSize, int pageIndex) {
        Criteria criteria = currentSession().createCriteria(NewProduct.class);
        criteria.addOrder(Order.desc("price"));
        criteria.setFirstResult((pageIndex-1)*pageSize);
        criteria.setMaxResults(pageSize);

        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<NewProduct> allNewProductFilter(String titleFilter,int pageSize, int pageIndex) {
        Criteria criteria = currentSession().createCriteria(NewProduct.class);
        criteria.add(Restrictions.like("title","%"+titleFilter+"%"));
        criteria.addOrder(Order.desc("price"));
        criteria.setFirstResult((pageIndex-1)*pageSize);
        criteria.setMaxResults(pageSize);

        return criteria.list();
    }

    @Override
    public NewProduct findNewProductById(long id) {

        return currentSession().load(NewProduct.class, id);
    }

    @Override
    public void updateNewProduct(long id, NewProduct newProduct) {
        NewProduct newProductToSave = currentSession().load(NewProduct.class, id);

        newProductToSave.setImg(newProduct.getImg() == null ? newProductToSave.getImg() : newProduct.getImg());
        newProductToSave.setTitle(newProduct.getTitle() == null ? newProductToSave.getTitle() : newProduct.getTitle());
        newProductToSave.setDescription(newProduct.getDescription() == null ? newProductToSave.getDescription() : newProduct.getDescription());
        newProductToSave.setDiscount(newProduct.getDiscount() == 0 && newProduct.getOriginalPrice() == newProduct.getPrice()? newProductToSave.getDiscount() : newProduct.getDiscount());
        newProductToSave.setOriginalPrice(newProduct.getOriginalPrice() == 0.0 ? newProductToSave.getOriginalPrice() : newProduct.getOriginalPrice());
        newProductToSave.setPrice(newProduct.getPrice() == 0.0 ? newProductToSave.getPrice() : newProduct.getPrice());

        currentSession().update(newProductToSave);
    }

    @Override
    public NewProduct deleteNewProduct(long id) {
        NewProduct newProductToDelete = currentSession().load(NewProduct.class, id);
        currentSession().delete(newProductToDelete);

        return newProductToDelete;
    }
}
