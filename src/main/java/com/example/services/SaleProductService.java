package com.example.services;

import com.example.connections.SaleProductConnection;
import com.example.connections.SaleProductEdge;
import com.example.models.SaleProduct;

import java.util.List;

public interface SaleProductService {
    List<SaleProduct> retrieveAllNewProducts();
    List<SaleProduct> allNewProductsPagination(int pageSize, int pageIndex);
    List<SaleProduct> allNewProductsFilter(String filter, int pageSize, int pageIndex);
    void saveNewProduct(SaleProduct saleProduct);
    SaleProduct findNewProductById(long id);
    SaleProduct findSaleProductByIndex(int index);
    void updateNewProduct(long id,SaleProduct saleProduct);
    SaleProduct deleteNewProduct(long id);
    SaleProductConnection saleProductEdgeList(String before, String after, int first, int last) throws Exception;
}
