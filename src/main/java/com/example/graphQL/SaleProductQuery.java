package com.example.graphQL;

import com.example.connections.SaleProductConnection;
import com.example.connections.SaleProductEdge;
import com.example.models.SaleProduct;
import com.example.services.SaleProductService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SaleProductQuery {

    private SaleProductService saleProductService;

    @Autowired
    public SaleProductQuery(SaleProductService saleProductService) {
        this.saleProductService = saleProductService;
    }

    @GraphQLQuery(name="allNewProducts")
    public List<SaleProduct> getAllNewProducts(){

        return saleProductService.retrieveAllNewProducts();
    }

    @GraphQLQuery(name="allNewProductsFilter")
    public List<SaleProduct> allNewProductsFilter(@GraphQLArgument(name="titleFilter")String titleFilter,
                                                  @GraphQLArgument(name="pageSize")int pageSize,
                                                  @GraphQLArgument(name="pageIndex")int pageIndex){

        return saleProductService.allNewProductsFilter(titleFilter,pageSize,pageIndex);
    }

    @GraphQLQuery(name="allNewProductsPagination")
    public List<SaleProduct> allNewProductsPagination(@GraphQLArgument(name="first")int pageSize,
                                                      @GraphQLArgument(name="skip")int pageIndex){

        return saleProductService.allNewProductsPagination(pageSize,pageIndex);
    }

    @GraphQLQuery(name="findNewProductById")
    public SaleProduct getNewProductById(@GraphQLArgument(name="id") long id){

        return saleProductService.findNewProductById(id);
    }

    @GraphQLQuery(name="getSaleProductByIndex")
    public SaleProduct getSaleProductByIndex(@GraphQLArgument(name="index") int index){

        return saleProductService.findSaleProductByIndex(index);
    }

    @GraphQLQuery(name="getAllSaleProducts")
    public SaleProductConnection getAllSaleProducts(@GraphQLArgument(name="before") String before,
                                                    @GraphQLArgument(name="after") String after,
                                                    @GraphQLArgument(name="first") int first,
                                                    @GraphQLArgument(name="last") int last) throws Exception {

        return saleProductService.saleProductEdgeList(before, after, first, last);
    }

    @GraphQLMutation(name="createNewProduct")
    public SaleProduct saveNewProduct(@GraphQLArgument(name="newProductInput") SaleProduct saleProductToSave){
        saleProductService.saveNewProduct(saleProductToSave);

        return saleProductToSave;
    }

    @GraphQLMutation(name="editNewProduct")
    public SaleProduct updateNewProduct(@GraphQLArgument(name="id") long id,
                                        @GraphQLArgument(name="newProductInput") SaleProduct saleProduct){
        saleProductService.updateNewProduct(id, saleProduct);

        return saleProduct;
    }

    @GraphQLMutation(name="deleteNewProduct")
    public SaleProduct deleteNewProduct(@GraphQLArgument(name="id")long id) {

        return saleProductService.deleteNewProduct(id);
    }
}
