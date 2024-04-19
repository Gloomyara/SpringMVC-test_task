package ru.antonovmikhail.product.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.antonovmikhail.product.mapper.ProductMapper;
import ru.antonovmikhail.product.model.Product;
import ru.antonovmikhail.product.model.dto.NewProductDto;
import ru.antonovmikhail.product.model.dto.ProductDtoIn;
import ru.antonovmikhail.product.model.dto.ProductDtoOut;
import ru.antonovmikhail.product.repository.ProductRepository;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository repository;
    private ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @Override
    public ProductDtoOut getProductById(UUID id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new EntityNotFoundException()));
    }

    @Override
    public ProductDtoOut saveNewProduct(NewProductDto dto) {
        Product product = mapper.toEntity(dto);
        return mapper.toDto(repository.save(product));
    }

    @Override
    public ProductDtoOut update(ProductDtoIn dtoIn) {
        Product product = repository.findById(dtoIn.getId()).orElseThrow(() -> new EntityNotFoundException());
        mapper.update(dtoIn, product);
        return mapper.toDto(repository.save(product));
    }

    @Override
    public List<ProductDtoOut> findAll() {
        return mapper.toDto(repository.findAll());
    }

    @Override
    public ProductDtoOut delete(UUID id) {
        Product product = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        repository.deleteById(id);
        return mapper.toDto(product);
    }
}
