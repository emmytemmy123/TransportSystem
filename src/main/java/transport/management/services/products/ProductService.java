package transport.management.services.products;

import transport.management.model.dto.request.productsRequest.ProductRequest;
import transport.management.model.dto.response.othersResponse.ApiResponse;
import transport.management.model.dto.response.productsResponse.ProductResponse;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ApiResponse<List<ProductResponse>> getListOfProduct(int page, int size);

    ApiResponse<String> addProducts(ProductRequest request);

    ApiResponse<ProductResponse> getProductById(UUID productId);

    ApiResponse<String> updateProduct(UUID productId, ProductRequest request);

    ApiResponse<String> deleteProduct(UUID productId);

    ApiResponse<List<ProductResponse>> searchProductsByName(String name);

//    ApiResponse<List<ProductResponse>> searchProductsByProductCategory(String productCategory);



}
