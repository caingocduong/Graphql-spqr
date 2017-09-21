package com.example.services;

import com.example.connections.SaleProductConnection;
import com.example.connections.SaleProductEdge;
import com.example.models.SaleProduct;
import com.example.repositories.SaleProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleProductServiceImpl implements SaleProductService {
    @Autowired
    private SaleProductRepository saleProductRepo;


    @Override
    public List<SaleProduct> retrieveAllNewProducts() {

        return saleProductRepo.retrieveAllNewProduct();
    }

    @Override
    public List<SaleProduct> allNewProductsPagination(int pageSize, int pageIndex) {

        return saleProductRepo.allNewProductsPagination(pageSize,pageIndex);
    }

    @Override
    public List<SaleProduct> allNewProductsFilter(String titleFilter, int pageSize, int pageIndex) {

        return saleProductRepo.allNewProductFilter(titleFilter,pageSize,pageIndex);
    }

    @Override
    public void saveNewProduct(SaleProduct saleProduct) {
        saleProductRepo.saveNewProduct(saleProduct);
    }

    @Override
    public SaleProduct findNewProductById(long id) {

        return saleProductRepo.findNewProductById(id);
    }

    @Override
    public SaleProduct findSaleProductByIndex(int index) {

        return saleProductRepo.retrieveAllNewProduct().get(index);
    }

    @Override
    public void updateNewProduct(long id,SaleProduct saleProduct) {
        saleProductRepo.updateNewProduct(id, saleProduct);
    }

    @Override
    public SaleProduct deleteNewProduct(long id) {
        return saleProductRepo.deleteNewProduct(id);
    }

    @Override
    public SaleProductConnection saleProductEdgeList(String before, String after, int first, int last) throws Exception {

        return saleProductRepo.getAllSaleProductPagination(before, after, first, last);
    }
}
