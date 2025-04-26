package com.domain.devstore_backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.domain.devstore_backend.services.FakeApiService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/products")
public class FakeApiController {

    private final FakeApiService fakeApiService;

    @PostMapping(value = "/import")
    public ResponseEntity<String> importProductsFromFakeApi() {
        try {
            fakeApiService.fromFakeApiToDevStore();
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Produtos importados com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro no importe dos produtos" + e.getMessage());
        }
    }
}
