package transport.management.controller.productsControllers;


import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import transport.management.model.dto.request.productsRequest.ProductCategoryRequest;
import transport.management.model.dto.request.productsRequest.ProductRequest;
import transport.management.model.dto.response.othersResponse.ApiResponse;
import transport.management.model.dto.response.productsResponse.ProductCategoryResponse;
import transport.management.model.dto.response.productsResponse.ProductResponse;
import transport.management.services.products.ProductCategoryService;
import transport.management.services.products.ProductService;
import transport.management.utills.EndPoints.ProductEndPoints;
import transport.management.utills.EndpointParam;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static transport.management.utills.EndPoints.ProductEndPoints.DELETE_PRODUCT;
import static transport.management.utills.EndPoints.ProductEndPoints.DELETE_PRODUCT_CATEGORY;

@RestController
@RequestMapping(ProductEndPoints.product)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductCategoryService productCategoryService;



                                            //FIND_LISTS_OF_PRODUCTS

    @GetMapping(ProductEndPoints.FIND_PRODUCT)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for retrieving lists of product", response = ProductResponse.class, responseContainer = "List")
    public ApiResponse<List<ProductResponse>> getListOfProduct(@RequestParam(value= EndpointParam.PAGE, defaultValue = EndpointParam.PAGE_DEFAULT) int page,
                                                         @RequestParam(value= EndpointParam.SIZE,defaultValue= EndpointParam.SIZE_DEFAULT) int size) {
        return productService.getListOfProduct(page,size);
    }

    @GetMapping(ProductEndPoints.FIND_PRODUCT_CATEGORY)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for retrieving lists of productCategory", response = ProductCategoryResponse.class, responseContainer = "List")
    public ApiResponse<List<ProductCategoryResponse>> getListOfProductCategory(@RequestParam(value= EndpointParam.PAGE, defaultValue = EndpointParam.PAGE_DEFAULT) int page,
                                                               @RequestParam(value= EndpointParam.SIZE,defaultValue= EndpointParam.SIZE_DEFAULT) int size) {
        return productCategoryService.getListOfProductCategory(page,size);
    }



                                                //ADD_PRODUCTS

    @PostMapping(ProductEndPoints.ADD_PRODUCT)
    @PreAuthorize(" hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for adding new product to database", response = String.class)
    public ApiResponse<String> addProduct(@Valid @RequestBody ProductRequest request) {
        return productService.addProducts(request);
    }

    @PostMapping(ProductEndPoints.ADD_PRODUCT_CATEGORY)
    @PreAuthorize(" hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for adding new productCategory to database", response = String.class)
    public ApiResponse<String> addProductCategory(@Valid @RequestBody ProductCategoryRequest request) {
        return productCategoryService.addProductCategory(request);
    }



                                                         //FIND_PRODUCTS_BY_ID

    @GetMapping(ProductEndPoints.FIND_PRODUCT_BY_ID)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for fetching products by id from database", response = ProductResponse.class)
    public ApiResponse<ProductResponse> getProductById(@PathVariable(value = "id") UUID product_id) {
        return productService.getProductById(product_id);

    }

    @GetMapping(ProductEndPoints.FIND_PRODUCT_CATEGORY_BY_ID)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for fetching productCategory by id from database", response = ProductCategoryResponse.class)
    public ApiResponse<ProductCategoryResponse> getProductCategoryById(@PathVariable(value = "id") UUID productCategory_id) {
        return productCategoryService.getProductCategoryById(productCategory_id);

    }


                                            //FIND_PRODUCTS_BY_NAME

    @GetMapping(ProductEndPoints.SEARCH_PRODUCT_BY_NAME)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for retrieving lists of product by Name", response = ProductResponse.class, responseContainer = "List")
    public ApiResponse<List<ProductResponse>> searchListOfProductByName(@RequestParam(value= EndpointParam.PAGE, defaultValue = EndpointParam.PAGE_DEFAULT) int page,
                                                                        @RequestParam(value= EndpointParam.SIZE,defaultValue= EndpointParam.SIZE_DEFAULT) int size,
                                                                        @RequestParam String name ) {
        return productService.searchProductsByName(name);
    }




                                                //UPDATE_PRODUCTS

    @PutMapping(ProductEndPoints.UPDATE_PRODUCT)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    @ApiOperation(value = "Endpoint for updating product by id from database", response = String.class)
    public ApiResponse<String> updateProduct(@PathVariable(value = "id") UUID product_id,
                                                     @RequestBody ProductRequest request) {
        return productService.updateProduct(product_id, request);
    }

    @PutMapping(ProductEndPoints.UPDATE_PRODUCT_CATEGORY)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for updating productCategory by id from database", response = String.class)
    public ApiResponse<String> updateProductCategory(@PathVariable(value = "id") UUID productCategory_id,
                                                     @RequestBody ProductCategoryRequest request) {
        return productCategoryService.updateProductCategory(productCategory_id, request);
    }



                                            //DELETE_PRODUCTS

    @DeleteMapping(DELETE_PRODUCT)
    @ApiOperation(value = "Endpoint for deleting product by id from database", response = String.class)
    public ApiResponse<String> deleteProduct(@PathVariable(value = "id") UUID product_id) {
        return productService.deleteProduct(product_id);
    }

    @DeleteMapping(DELETE_PRODUCT_CATEGORY)
    @ApiOperation(value = "Endpoint for deleting productCategory by id from database", response = String.class)
    public ApiResponse<String> deleteProductCategory(@PathVariable(value = "id") UUID productCategory_id) {
        return productCategoryService.deleteProductCategory(productCategory_id);
    }




}
