package com.example.demo.service;

import com.example.demo.dto.request.CreateProductRequest;
import com.example.demo.dto.request.UpdateProductRequest;
import com.example.demo.dto.response.ProductResponse;
import com.example.demo.entity.Lessor;
import com.example.demo.entity.Product;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.repository.LessorRepository;
import com.example.demo.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {
    LessorRepository lessorRepository;
    ProductRepository productRepository;
    ProductMapper productMapper;
    LessorService lessorService;



    public ProductResponse createProduct(CreateProductRequest request, HttpServletRequest httpRequest) {

        String authorizationHeader = httpRequest.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }
        String token = authorizationHeader.substring(7);

        Integer lessorId = lessorService.extractUserId(token);

        Lessor lessor = lessorRepository.findById(lessorId)
                .orElseThrow(() -> new AppException(ErrorCode.LESSOR_NOT_EXISTED));

        Product product = productMapper.toProduct(request);
        product.setLessor(lessor);

        return productMapper.toProductResponse(productRepository.save(product));
    }


    public ProductResponse updateProduct(Integer productId, UpdateProductRequest request) {
        Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
        productMapper.updateProduct(product, request);
        return productMapper.toProductResponse(productRepository.save(product));
    }

    public void deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
    }

    public List<Product> getAll(int page, int size, String[] sort) {
        List<Order> orders = new ArrayList<>();
        if (sort == null || sort.length == 0) {
            orders.add(new Order(Sort.Direction.ASC, "ProductId"));
        } else {
            for (String sortParam : sort) {
                String[] sortSplit = sortParam.split(",");
                if (sortSplit.length == 2) {
                    orders.add(new Order(
                            sortSplit[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC,
                            sortSplit[0]
                    ));
                }
            }
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.getContent();
    }

    public List<Product> searchProduct(int page, int size, String keyword, String[] sort) {
        List<Order> orders = new ArrayList<>();

        if (sort == null || sort.length == 0) {
            orders.add(new Order(Sort.Direction.ASC, "ProductId"));
        } else {
            for (String sortParam : sort) {
                String[] sortSplit = sortParam.split(",");
                if (sortSplit.length == 2) {
                    orders.add(new Order(
                            sortSplit[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC,
                            sortSplit[0]
                    ));
                }
            }
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
        Page<Product> productPage = productRepository.searchByKeyword(keyword, pageable);
        return productPage.getContent();
    }

    public List<Product> getMyProducts(int page, int size, String[] sort) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof Lessor)) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }
        Lessor lessor = (Lessor) authentication.getPrincipal();
        List<Order> orders = new ArrayList<>();
        if (sort == null || sort.length == 0) {
            orders.add(new Order(Sort.Direction.ASC, "productId"));
        } else {
            for (String sortParam : sort) {
                String[] sortSplit = sortParam.split(",");
                if (sortSplit.length == 2) {
                    orders.add(new Order(
                            sortSplit[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC,
                            sortSplit[0]
                    ));
                }
            }
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
        return productRepository.findByLessor(lessor, pageable).getContent();
    }


}


