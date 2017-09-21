package com.example.repositories;

import com.example.models.FeatureProduct;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class FeatureProductRepositoryImpl implements  FeatureProductRepository {

    private SessionFactory sessionFactory;
    @Autowired
    public FeatureProductRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession(){

        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void saveFeatureProduct(FeatureProduct featureProduct) {
        currentSession().merge(featureProduct);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<FeatureProduct> retrieveAllFeatureProduct() {

        return currentSession().createQuery("from "+FeatureProduct.class.getName()).list();
    }

    @Override
    public List<FeatureProduct> allFeatureProductsPagination(int pageSize, int pageIndex) {
        Criteria criteria = currentSession().createCriteria(FeatureProduct.class);
        criteria.addOrder(Order.desc("price"));
        criteria.setFirstResult((pageIndex-1)*pageSize);
        criteria.setMaxResults(pageSize);

        return criteria.list();
    }

    @Override
    public List<FeatureProduct> allFeatureProductFilter(String titleFilter, int pageSize, int pageIndex) {
        Criteria criteria = currentSession().createCriteria(FeatureProduct.class);
        criteria.addOrder(Order.desc("price"));
        criteria.add(Restrictions.like("title","%"+titleFilter+"%"));
        criteria.setFirstResult((pageIndex-1)*pageSize);
        criteria.setMaxResults(pageSize);

        return criteria.list();
    }

    @Override
    public FeatureProduct findFeatureProductById(long id) {

        return currentSession().load(FeatureProduct.class, id);
    }

    @Override
    public void updateFeatureProduct(long id, FeatureProduct featureProduct) {
        FeatureProduct featureProductToUpdate = currentSession().load(FeatureProduct.class, id);

        featureProductToUpdate.setImg(featureProduct.getImg() == null ? featureProductToUpdate.getImg() : featureProduct.getImg());
        featureProductToUpdate.setContent(featureProduct.getContent() == null ? featureProductToUpdate.getImg() : featureProduct.getImg());
        featureProductToUpdate.setTitle(featureProduct.getTitle() == null ? featureProductToUpdate.getTitle() : featureProduct.getTitle());
        featureProductToUpdate.setPrice(featureProduct.getPrice() == 0.0 ? featureProductToUpdate.getPrice() : featureProduct.getPrice());

        currentSession().update(featureProductToUpdate);
    }

    @Override
    public FeatureProduct deleteFeatureProduct(long id) {
        FeatureProduct featureProduct = currentSession().load(FeatureProduct.class, id);
        currentSession().delete(featureProduct);

        return featureProduct;
    }
}
