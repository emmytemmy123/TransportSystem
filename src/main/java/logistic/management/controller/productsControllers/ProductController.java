package logistic.management.controller.productsControllers;


import io.swagger.annotations.ApiOperation;
import logistic.management.model.dto.request.productsRequest.ProductCategoryRequest;
import logistic.management.model.dto.request.productsRequest.ProductRequest;
import logistic.management.model.dto.response.othersResponse.ApiResponse;
import logistic.management.model.dto.response.productsResponse.ProductCategoryResponse;
import logistic.management.model.dto.response.productsResponse.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import logistic.management.services.products.ProductCategoryService;
import logistic.management.services.products.ProductService;
import logistic.management.utills.EndPoints.ProductEndPoints;
import logistic.management.utills.EndpointParam;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static logistic.management.utills.EndPoints.ProductEndPoints.*;


@RestController
@RequestMapping(ProductEndPoints.product)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductCategoryService productCategoryService;



                                            //FIND_LISTS_OF_PRODUCTS

    @GetMapping(FIND_PRODUCT)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for retrieving lists of product", response = ProductResponse.class, responseContainer = "List")
    public ApiResponse<List<ProductResponse>> getListOfProduct(@RequestParam(value= EndpointParam.PAGE, defaultValue = EndpointParam.PAGE_DEFAULT) int page,
                                                               @RequestParam(value= EndpointParam.SIZE,defaultValue= EndpointParam.SIZE_DEFAULT) int size) {
        return productService.getListOfProduct(page,size);
    }

    @GetMapping(FIND_PRODUCT_CATEGORY)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for retrieving lists of productCategory", response = ProductCategoryResponse.class, responseContainer = "List")
    public ApiResponse<List<ProductCategoryResponse>> getListOfProductCategory(@RequestParam(value= EndpointParam.PAGE, defaultValue = EndpointParam.PAGE_DEFAULT) int page,
                                                               @RequestParam(value= EndpointParam.SIZE,defaultValue= EndpointParam.SIZE_DEFAULT) int size) {
        return productCategoryService.getListOfProductCategory(page,size);
    }



                                                //ADD_PRODUCTS

    @PostMapping(ADD_PRODUCT)
    @ApiOperation(value = "Endpoint for adding new product to database", response = String.class)
    public ApiResponse<String> addProduct(@Valid @RequestBody ProductRequest request) {
        return productService.addProducts(request);
    }

    @PostMapping(ADD_PRODUCT_CATEGORY)
    @PreAuthorize(" hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for adding new productCategory to database", response = String.class)
    public ApiResponse<String> addProductCategory(@Valid @RequestBody ProductCategoryRequest request) {
        return productCategoryService.addProductCategory(request);
    }



                                                         //FIND_PRODUCTS_BY_ID

    @GetMapping(FIND_PRODUCT_BY_ID)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for fetching products by id from database", response = ProductResponse.class)
    public ApiResponse<ProductResponse> getProductById(@PathVariable(value = "id") UUID product_id) {
        return productService.getProductById(product_id);

    }

    @GetMapping(FIND_PRODUCT_CATEGORY_BY_ID)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for fetching productCategory by id from database", response = ProductCategoryResponse.class)
    public ApiResponse<ProductCategoryResponse> getProductCategoryById(@PathVariable(value = "id") UUID productCategory_id) {
        return productCategoryService.getProductCategoryById(productCategory_id);

    }


                                            //FIND_PRODUCTS_BY_NAME

    @GetMapping(SEARCH_PRODUCT_BY_NAME)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for retrieving lists of product by Name", response = ProductResponse.class, responseContainer = "List")
    public ApiResponse<List<ProductResponse>> searchListOfProductByName(@RequestParam(value= EndpointParam.PAGE, defaultValue = EndpointParam.PAGE_DEFAULT) int page,
                                                                        @RequestParam(value= EndpointParam.SIZE,defaultValue= EndpointParam.SIZE_DEFAULT) int size,
                                                                        @RequestParam String name ) {
        return productService.searchProductsByName(name);
    }




                                                //UPDATE_PRODUCTS

    @PutMapping(UPDATE_PRODUCT)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    @ApiOperation(value = "Endpoint for updating product by id from database", response = String.class)
    public ApiResponse<String> updateProduct(@PathVariable(value = "id") UUID product_id,
                                                     @RequestBody ProductRequest request) {
        return productService.updateProduct(product_id, request);
    }

    @PutMapping(UPDATE_PRODUCT_CATEGORY)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for updating productCategory by id from database", response = String.class)
    public ApiResponse<String> updateProductCategory(@PathVariable(value = "id") UUID productCategory_id,
                                                     @RequestBody ProductCategoryRequest request) {
        return productCategoryService.updateProductCategory(productCategory_id, request);
    }



                                //FIND_PRODUCTS_BY_PRODUCT_CATEGORY_ID

    @GetMapping(FIND_PRODUCT_BY_PRODUCT_CATEGORY_ID)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for fetching products by productCategoryId from database", response = ProductResponse.class)
    public ApiResponse<List<ProductResponse>> findProductByProductCategoryId(@PathVariable(value = "id") UUID productCategoryUuid) {
        return productService.findProductsByProductCategoryUuid(productCategoryUuid);

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
