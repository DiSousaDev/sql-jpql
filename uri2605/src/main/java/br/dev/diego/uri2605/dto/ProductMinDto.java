package br.dev.diego.uri2605.dto;

import br.dev.diego.uri2605.projection.ProductMinProjection;

public class ProductMinDto {

    private String productName;
    private String providerName;

    public ProductMinDto(){
    }

    public ProductMinDto(String productName, String providerName){
        this.productName = productName;
        this.providerName = providerName;
    }

    public ProductMinDto(ProductMinProjection projection){
        productName = projection.getProductName();
        providerName = projection.getProviderName();
    }

    public String getProductName(){
        return productName;
    }

    public void setProductName(String productName){
        this.productName = productName;
    }

    public String getProviderName(){
        return providerName;
    }

    public void setProviderName(String providerName){
        this.providerName = providerName;
    }

    @Override
    public String toString(){
        return "ProductMinDto{" + "productName='" + productName + '\'' + ", providerName='" + providerName + '\'' + '}';
    }
}
