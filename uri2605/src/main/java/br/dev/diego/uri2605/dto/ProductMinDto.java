package br.dev.diego.uri2605.dto;

import br.dev.diego.uri2605.projection.ProductMinProjection;

public class ProductMinDto {

    private String name;
    private String providerName;

    public ProductMinDto(){
    }

    public ProductMinDto(String name, String providerName){
        this.name = name;
        this.providerName = providerName;
    }

    public ProductMinDto(ProductMinProjection projection){
        name = projection.getName();
        providerName = projection.getProvider();
    }


    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getProviderName(){
        return providerName;
    }

    public void setProviderName(String providerName){
        this.providerName = providerName;
    }

    @Override
    public String toString(){
        return "ProductMinDto{" + "name='" + name + '\'' + ", providerName='" + providerName + '\'' + '}';
    }
}
