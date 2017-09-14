package com.example.controllers;

import com.example.graphQL.NewProductQuery;
import graphql.ExecutionResult;
import graphql.GraphQLError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.AnnotatedResolverBuilder;
import io.leangen.graphql.metadata.strategy.query.PublicResolverBuilder;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class GraphQLController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GraphQLController.class);
    private GraphQL graphQL;

    @Autowired
    public GraphQLController(NewProductQuery newProductQuery){
        GraphQLSchema schema = new GraphQLSchemaGenerator()
                .withResolverBuilders(
                        new AnnotatedResolverBuilder(),
                        new PublicResolverBuilder("com.example")
                )
                .withOperationsFromSingleton(newProductQuery)
                .withValueMapperFactory(new JacksonValueMapperFactory())
                .generate();

        graphQL = GraphQL.newGraphQL(schema).build();
        LOGGER.info("Generated GraphQL schema using SPQR");
    }

    @PostMapping(value = "/graphql",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Object graphqlEndpoint(@RequestBody Map<String, Object> request){
        ExecutionResult executionResult = graphQL.execute(request.get("query").toString());

        if(!executionResult.getErrors().isEmpty()){

            return extractErrorResponse(executionResult);
        }

        return executionResult;
    }

    private Object extractErrorResponse(ExecutionResult executionResult){

        return executionResult.getErrors()
                .stream()
                .map(GraphQLError::getMessage)
                .collect(Collectors.toList());
    }
}
