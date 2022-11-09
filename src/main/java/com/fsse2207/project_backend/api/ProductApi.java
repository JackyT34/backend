package com.fsse2207.project_backend.api;

import com.fsse2207.project_backend.config.CrossOriginConfig;
import com.fsse2207.project_backend.data.product.ProductData;
import com.fsse2207.project_backend.data.product.dto.ProductResponseDto;
import com.fsse2207.project_backend.exception.ProductNotFoundException;
import com.fsse2207.project_backend.data.product.ProductDetailData;
import com.fsse2207.project_backend.data.product.dto.ProductDetailResponseDto;
import com.fsse2207.project_backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@CrossOrigin(origins = {CrossOriginConfig.devBaseUrl, CrossOriginConfig.productionBaseUrl, CrossOriginConfig.productionS3BaseUrl })
@RestController
public class ProductApi {
    private ProductService productService;

    @Autowired
    public ProductApi(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/public/product")
        public ArrayList<ProductResponseDto> getProduct(){
            ArrayList<ProductData> products = productService.getProduct();
            ArrayList<ProductResponseDto> allProducts = new ArrayList<>();
            for(ProductData productData : products){
                allProducts.add(new ProductResponseDto(productData));
            }
            return allProducts;
    }

    @GetMapping("public/product/{pid}")
        public ProductDetailResponseDto getProductById(@PathVariable Integer pid) throws ProductNotFoundException {
        ProductDetailData product = productService.getProductById(pid);
        ProductDetailResponseDto productDetailResponseDto = new ProductDetailResponseDto(product);
        return productDetailResponseDto;
    }

}
