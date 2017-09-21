package com.example.repositories;

import com.example.models.FeatureProduct;

import java.util.List;

public interface FeatureProductRepository {
    void saveFeatureProduct(FeatureProduct featureProduct);
    List<FeatureProduct> retrieveAllFeatureProduct();
    List<FeatureProduct> allFeatureProductsPagination(int pageSize, int pageIndex);
    List<FeatureProduct> allFeatureProductFilter(String titleFilter, int pageSize, int pageIndex);
    FeatureProduct findFeatureProductById(long id);
    void updateFeatureProduct(long id,FeatureProduct featureProduct);
    FeatureProduct deleteFeatureProduct(long id);
}
