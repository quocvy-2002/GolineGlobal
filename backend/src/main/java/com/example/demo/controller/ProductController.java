package com.example.demo.controller;

import com.example.demo.dto.request.CreateProductRequest;
import com.example.demo.dto.request.UpdateProductRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.ProductResponse;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Controller
@RestController
@RequestMapping("/products")
public class ProductController {
    ProductService productService;

    @PostMapping
    public ApiResponse<ProductResponse> createProduct(
            @Valid @RequestBody CreateProductRequest request,
            HttpServletRequest httpRequest) {

        return ApiResponse.<ProductResponse>builder()
                .result(productService.createProduct(request, httpRequest))
                .build();
    }



    @PutMapping("/{productId}")
    ApiResponse<ProductResponse> updateProduct(@PathVariable Integer productId, @Valid @RequestBody UpdateProductRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.updateProduct(productId, request))
                .build();
    }

    @DeleteMapping("/{productId}")
    ApiResponse<ProductResponse> deleteProduct(@PathVariable Integer productId) {
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setCode(202);
        productService.deleteProduct(productId);
        apiResponse.setMessage("product had been delete");
        return apiResponse;
    }

    @GetMapping
    ResponseEntity<List<Product>> getAllProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(required = false) String[] sort) {
        List<Product> products = productService.getAll(page, size, sort);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search")
    ResponseEntity<List<Product>> searchProduct(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam String keyword, @RequestParam(required = false) String[] sort) {
        List<Product> products = productService.searchProduct(page, size, keyword, sort);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/my-products")
    ResponseEntity<List<Product>> getMyProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(required = false) String[] sort) {
        List<Product> products = productService.getMyProducts(page, size, sort);
        return ResponseEntity.ok(products);
    }

}
