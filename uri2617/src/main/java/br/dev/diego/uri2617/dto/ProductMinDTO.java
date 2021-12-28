package br.dev.diego.uri2617.dto;

import br.dev.diego.uri2617.projections.ProductMinProjection;

public class ProductMinDTO {

    private String productName;
    private String providerName;

    public ProductMinDTO(){
    }

    public ProductMinDTO(String productName, String providerName){
        this.productName = productName;
        this.providerName = providerName;
    }

    public ProductMinDTO(ProductMinProjection projection){
        productName = projection.getProductName();
        providerName = projection.getProviderName();
    }

    public String getProductName(){
        return productName;
    }

    public String getProviderName(){
        return providerName;
    }

    @Override
    public String toString(){
        return "ProductDTO{" + "productName='" + productName + '\'' + ", providerName='" + providerName + '\'' + '}';
    }
}
