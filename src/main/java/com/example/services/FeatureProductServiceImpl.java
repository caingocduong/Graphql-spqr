package com.example.services;


import com.example.models.FeatureProduct;
import com.example.repositories.FeatureProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeatureProductServiceImpl implements FeatureProductService {

    private FeatureProductRepository featureProductRepo;
    @Autowired
    public FeatureProductServiceImpl(FeatureProductRepository featureProductRepo) {
        this.featureProductRepo = featureProductRepo;
    }

    @Override
    public List<FeatureProduct> retrieveAllFeatureProduct() {

        return featureProductRepo.retrieveAllFeatureProduct();
    }

    @Override
    public FeatureProduct findFeatureProductById(long id) {

        return featureProductRepo.findFeatureProductById(id);
    }

    @Override
    public List<FeatureProduct> allFeatureProductsPagination(int pageSize, int pageIndex) {

        return featureProductRepo.allFeatureProductsPagination(pageSize,pageIndex);
    }

    @Override
    public List<FeatureProduct> allFeatureProductsFilter(String titleFilter, int pageSize, int pageIndex) {

        return featureProductRepo.allFeatureProductFilter(titleFilter,pageSize,pageIndex);
    }

    @Override
    public void saveFeatureProduct(FeatureProduct featureProduct) {
        featureProductRepo.saveFeatureProduct(featureProduct);
    }

    @Override
    public void updateFeatureProduct(long id, FeatureProduct featureProduct) {
        featureProductRepo.updateFeatureProduct(id, featureProduct);
    }

    @Override
    public FeatureProduct deleteFeatureProduct(long id) {

        return featureProductRepo.deleteFeatureProduct(id);
    }
}
