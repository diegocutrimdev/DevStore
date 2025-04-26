package com.domain.devstore_backend.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import com.domain.devstore_backend.dto.ProductDto;
import org.springframework.web.client.RestTemplate;
import com.domain.devstore_backend.entities.Product;
import com.domain.devstore_backend.mapper.ProductMapper;
import com.domain.devstore_backend.services.FakeApiService;
import org.springframework.core.ParameterizedTypeReference;
import com.domain.devstore_backend.repositories.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FakeApiServiceImpl implements FakeApiService {

    private final RestTemplate restTemplate;
    private final ProductRepository productRepository;

    String API_URL = "https://fakestoreapi.com/products";

    @Override
    public void fromFakeApiToDevStore() {

        ResponseEntity<List<ProductDto>> externalProductsList = restTemplate.exchange(API_URL, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<ProductDto>>() {
                });
        if (externalProductsList.getStatusCode() == HttpStatus.OK) {
            List<ProductDto> productDtoList = externalProductsList.getBody();

            if (productDtoList != null && !productDtoList.isEmpty()) {
                List<Product> productList = ProductMapper.toListProduct(productDtoList);
                productRepository.saveAll(productList);
            }
        } else {
            throw new RuntimeException("Erro consumindo a API");
        }
    }
}
