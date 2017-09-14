package com.example.repositories;

import com.example.models.NewProduct;

import java.util.List;

public interface NewProductRepository {
    void saveNewProduct(NewProduct newProduct);
    List<NewProduct> retrieveAllNewProduct();
    List<NewProduct> allNewProductsPagination(int pageSize, int pageIndex);
    List<NewProduct> allNewProductFilter(String titleFilter,int pageSize, int pageIndex);
    NewProduct findNewProductById(long id);
    void updateNewProduct(long id,NewProduct newProduct);
    NewProduct deleteNewProduct(long id);
}
