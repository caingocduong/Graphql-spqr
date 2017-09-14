package com.example.services;

import com.example.models.NewProduct;

import java.util.List;

public interface NewProductService {
    List<NewProduct> retrieveAllNewProducts();
    List<NewProduct> allNewProductsPagination(int pageSize, int pageIndex);
    List<NewProduct> allNewProductsFilter(String filter,int pageSize, int pageIndex);
    void saveNewProduct(NewProduct newProduct);
    NewProduct findNewProductById(long id);
    void updateNewProduct(long id,NewProduct newProduct);
    NewProduct deleteNewProduct(long id);

}
