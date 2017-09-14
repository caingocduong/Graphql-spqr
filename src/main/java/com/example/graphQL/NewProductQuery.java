package com.example.graphQL;

import com.example.models.NewProduct;
import com.example.services.NewProductService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NewProductQuery {

    private NewProductService newProductService;

    @Autowired
    public NewProductQuery(NewProductService newProductService) {
        this.newProductService = newProductService;
    }

    @GraphQLQuery(name="allNewProducts")
    public List<NewProduct> getAllNewProducts(){
        if(newProductService == null){
            return null;
        }

        return newProductService.retrieveAllNewProducts();
    }

    @GraphQLQuery(name="allNewProductsFilter")
    public List<NewProduct> allNewProductsFilter(@GraphQLArgument(name="titleFilter")String titleFilter,
                                                 @GraphQLArgument(name="pageSize")int pageSize,
                                                 @GraphQLArgument(name="pageIndex")int pageIndex){

        return newProductService.allNewProductsFilter(titleFilter,pageSize,pageIndex);
    }

    @GraphQLQuery(name="allNewProductsPagination")
    public List<NewProduct> allNewProductsPagination(@GraphQLArgument(name="pageSize")int pageSize,
                                                 @GraphQLArgument(name="pageIndex")int pageIndex){

        return newProductService.allNewProductsPagination(pageSize,pageIndex);
    }

    @GraphQLQuery(name="findNewProductById")
    public NewProduct getNewProductById(@GraphQLArgument(name="id") long id){

        return newProductService.findNewProductById(id);
    }

    @GraphQLMutation(name="createNewProduct")
    public NewProduct saveNewProduct(@GraphQLArgument(name="newProductInput") NewProduct newProductToSave){
        newProductService.saveNewProduct(newProductToSave);

        return newProductToSave;
    }

    @GraphQLMutation(name="editNewProduct")
    public NewProduct updateNewProduct(@GraphQLArgument(name="id") long id,
                                       @GraphQLArgument(name="newProductInput") NewProduct newProduct){
        newProductService.updateNewProduct(id,newProduct);

        return newProduct;
    }

    @GraphQLMutation(name="deleteNewProduct")
    public NewProduct deleteNewProduct(@GraphQLArgument(name="id")long id){

        return newProductService.deleteNewProduct(id);
    }
}
