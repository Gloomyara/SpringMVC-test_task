package ru.antonovmikhail.product.service;


import ru.antonovmikhail.product.model.dto.NewProductDto;
import ru.antonovmikhail.product.model.dto.ProductDtoIn;
import ru.antonovmikhail.product.model.dto.ProductDtoOut;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductDtoOut getProductById(UUID id);

    ProductDtoOut saveNewProduct(NewProductDto dto);

    ProductDtoOut update(ProductDtoIn dtoIn);

    List<ProductDtoOut> findAll();

    ProductDtoOut delete(UUID id);
}
