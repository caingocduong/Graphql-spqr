package com.example.services;

import com.example.models.FeatureProduct;

import java.util.List;

public interface FeatureProductService {
    List<FeatureProduct> retrieveAllFeatureProduct();
    FeatureProduct findFeatureProductById(long id);
    List<FeatureProduct> allFeatureProductsPagination(int pageSize, int pageIndex);
    List<FeatureProduct> allFeatureProductsFilter(String titleFilter, int pageSize, int pageIndex);
    void saveFeatureProduct(FeatureProduct featureProduct);
    void updateFeatureProduct(long id, FeatureProduct featureProduct);
    FeatureProduct deleteFeatureProduct(long id);
}
