package org.example.api.mvc.domain.product.service;

import lombok.RequiredArgsConstructor;
import org.example.api.mvc.domain.product.repository.ProductEntityMvc;
import org.example.api.mvc.domain.product.repository.ProductRepositoryMvc;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceMvc {

    /**
     * TODO: 상품 CRUD 서비스 로직 구현
     */

    private final ProductRepositoryMvc productRepositoryMvc;

    // Create
    public ProductEntityMvc create(
        Long adminId,
        String name,
        Long price,
        String category,
        String thumbnailUrl
    ) {
        return productRepositoryMvc.save(
            ProductEntityMvc.builder()
                .adminId(adminId)
                .name(name)
                .price(price)
                .category(category)
                .thumbnailUrl(thumbnailUrl)
                .build()
        );
    }

    // Read
    public List<ProductEntityMvc> findAll() {
        return productRepositoryMvc.findAll();
    }

    public ProductEntityMvc findById(Long id) {
        return Optional.ofNullable(productRepositoryMvc.findById(id))
            .orElseThrow(() -> new RuntimeException("요청한 상품 ID가 존재하지 않습니다."))
            .get();
    }

    // Update
    public ProductEntityMvc update(
        Long id,
        Long adminId,
        String name,
        Long price,
        String category,
        String thumbnailUrl
    ) {
        ProductEntityMvc product = productRepositoryMvc.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 상품이 존재하지 않습니다.");
        });

        product.setAdminId(adminId);
        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);
        product.setThumbnailUrl(thumbnailUrl);

        return productRepositoryMvc.save(product);
    }

    // Delete
    public void deleteById(Long id) {
        productRepositoryMvc.deleteById(id);
    }
}
