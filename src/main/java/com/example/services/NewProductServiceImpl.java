package com.example.services;

import com.example.models.NewProduct;
import com.example.repositories.NewProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewProductServiceImpl implements NewProductService {
    @Autowired
    private NewProductRepository newProductRepo;


    @Override
    public List<NewProduct> retrieveAllNewProducts() {

        return newProductRepo.retrieveAllNewProduct();
    }

    @Override
    public List<NewProduct> allNewProductsPagination(int pageSize, int pageIndex) {

        return newProductRepo.allNewProductsPagination(pageSize,pageIndex);
    }

    @Override
    public List<NewProduct> allNewProductsFilter(String titleFilter,int pageSize, int pageIndex) {

        return newProductRepo.allNewProductFilter(titleFilter,pageSize,pageIndex);
    }

    @Override
    public void saveNewProduct(NewProduct newProduct) {
        newProductRepo.saveNewProduct(newProduct);
    }

    @Override
    public NewProduct findNewProductById(long id) {

        return newProductRepo.findNewProductById(id);
    }

    @Override
    public void updateNewProduct(long id,NewProduct newProduct) {
        newProductRepo.updateNewProduct(id,newProduct);
    }

    @Override
    public NewProduct deleteNewProduct(long id) {
        return newProductRepo.deleteNewProduct(id);
    }
}
