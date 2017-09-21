package com.example.graphQL;

import com.example.models.FeatureProduct;
import com.example.services.FeatureProductService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeatureProductQuery {

    private FeatureProductService featureProductService;
    @Autowired
    public FeatureProductQuery(FeatureProductService featureProductService) {
        this.featureProductService = featureProductService;
    }

    @GraphQLQuery(name="allFeatureProducts")
    public List<FeatureProduct> getAllFeatureProducts(){

        return featureProductService.retrieveAllFeatureProduct();
    }

    @GraphQLQuery(name="allFeatureProductsPagination")
    public List<FeatureProduct> getAllFeatureProductsPagination(@GraphQLArgument(name="pageSize") int pageSize,
                                                                @GraphQLArgument(name="pageIndex") int pageIndex){

        return featureProductService.allFeatureProductsPagination(pageSize, pageIndex);
    }

    @GraphQLQuery(name="getAllFeatureProductsFilter")
    public List<FeatureProduct> getAllFeatureProductsFilter(@GraphQLArgument(name="titleFilter") String titleFilter,
                                                            @GraphQLArgument(name="pageSize") int pageSize,
                                                            @GraphQLArgument(name="pageIndex") int pageIndex){

        return featureProductService.allFeatureProductsFilter(titleFilter, pageSize, pageIndex);
    }

    @GraphQLQuery(name="getFeatureProductById")
    public FeatureProduct getFeatureProductById(@GraphQLArgument(name="id") long id){

        return featureProductService.findFeatureProductById(id);
    }

    @GraphQLMutation(name="createFeatureProduct")
    public  FeatureProduct createFeatureProduct(@GraphQLArgument(name="featureProductInput") FeatureProduct featureProduct){
        featureProductService.saveFeatureProduct(featureProduct);

        return featureProduct;
    }

    @GraphQLMutation(name="editFeatureProduct")
    public FeatureProduct updateFeatureProduct(@GraphQLArgument(name="id") long id,
                                               @GraphQLArgument(name="featureProductInput") FeatureProduct featureProduct){
        featureProductService.updateFeatureProduct(id, featureProduct);

        return featureProduct;
    }

    @GraphQLMutation(name="deleteFeatureProduct")
    public FeatureProduct deleteFeatureProduct(@GraphQLArgument(name="id") long id){

        return featureProductService.deleteFeatureProduct(id);
    }
}

