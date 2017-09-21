package com.example.repositories;

import com.example.connections.SaleProductConnection;
import com.example.models.SaleProduct;

import java.util.List;

public interface SaleProductRepository {
    void saveNewProduct(SaleProduct saleProduct);
    List<SaleProduct> retrieveAllNewProduct();
    List<SaleProduct> allNewProductsPagination(int pageSize, int pageIndex);
    List<SaleProduct> allNewProductFilter(String titleFilter, int pageSize, int pageIndex);
    SaleProduct findNewProductById(long id);
    void updateNewProduct(long id,SaleProduct saleProduct);
    SaleProduct deleteNewProduct(long id);
    SaleProductConnection getAllSaleProductPagination(String before, String after, int first, int last) throws Exception;
}
