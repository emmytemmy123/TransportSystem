package logistic.management.services.products;

import logistic.management.model.dto.enums.AppStatus;
import logistic.management.model.dto.request.productsRequest.ProductRequest;
import logistic.management.model.dto.response.othersResponse.ApiResponse;
import logistic.management.model.dto.response.productsResponse.ProductResponse;
import logistic.management.model.entity.products.ProductCategory;
import logistic.management.model.entity.user.Sellers;
import logistic.management.repo.products.ProductCategoryRepository;
import logistic.management.repo.products.ProductRepository;
import logistic.management.repo.user.SellerRepository;
import logistic.management.utills.MessageUtil;
import logistic.management.exception.RecordNotFoundException;
import logistic.management.mapper.Mapper;
import logistic.management.model.entity.products.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final SellerRepository sellerRepository;

    @Override
    /**
     * @Finding the list of all products*
     * @Validate if the List of products is empty otherwise return record not found*
     * @return the list of products and a Success Message* *
     * * */
    public ApiResponse<List<ProductResponse>> getListOfProduct(int page, int size) {

            List<Product> productList = productRepository.findAll(PageRequest.of(page,size)).toList();
            if(productList.isEmpty())
                throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(productList, ProductResponse.class));

    }


    @Override
    /**
     * @Search the list of all products by name
     * @Validate if the List of products is empty otherwise return record not found*
     * @return the list of products by name* *
     * * */
    public ApiResponse<List<ProductResponse>> searchProductsByName(String name) {
        List<Product> searchProductsByName = productRepository.searchProductsByName(name);

        if(searchProductsByName.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(searchProductsByName, ProductResponse.class));

    }


    @Override
    public ApiResponse<List<ProductResponse>> findProductsByProductCategoryUuid(UUID productCategoryUuid) {

        List<Product> productList = productRepository.findByProductCategoryUuid(productCategoryUuid);

        if(productList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(productList, ProductResponse.class));

    }


    @Override
    /**
     * @Finding the list of all productOptional by uuid*
     * @Validate if the List of productOptional is empty otherwise return record not found
     * Create the product definition and get the product Optional by uuid
     * @return the list of products and a Success Message* *
     * * */
    public ApiResponse<ProductResponse> getProductById(UUID productId) {

        Optional<Product> productOptional = productRepository.findByUuid(productId);

        if(productOptional.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        Product product = productOptional.get();

        return new ApiResponse<ProductResponse>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertObject(product,ProductResponse.class));
    }


    /**
     * @validating products by uuid*
     * @Validate if products is empty otherwise return record not found
     * @return product
     * * */
    private Product validateProducts(UUID uuid){
        return productRepository.findByUuid(uuid).orElseThrow(()->new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));
    }

    /**
     * @Validating existingProductOptional by name*
     * @Validate if the List of existingProductOptional is empty otherwise return Duplicate Record*
     * * */
    private void validateDuplicationProduct(String name){
        Optional<Product> existingProductOptional = productRepository.findByName(name);
        if(existingProductOptional.isPresent())
            throw new RecordNotFoundException("Duplicate record");
    }


    @Override
    /**
     * @Validate that no duplicate product allow*
     * @Validate that product category exists otherwise return record not found*
     * @Validate that user creating the product exists, otherwise return user not found*
     * Create the product definition and save
     * @return success message
     * * */
    public ApiResponse<String> addProducts(ProductRequest request) {

        ProductCategory existingProductCategory = productCategoryRepository.findByUuid(request.getCategoryId())
                .orElseThrow(()->new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

        Sellers existingSeller  = sellerRepository.findByUuid(request.getPostedById())
                .orElseThrow(()->new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

        Product product = new Product();

        product.setCategory(request.getCategory());
        product.setModel(request.getModel());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setQuantity(request.getQuantity());
        product.setSalesPrice(request.getSalesPrice());
        product.setPhoto(request.getPhoto());
        product.setPostedBy(existingSeller.getName());
        product.setProductCategory(existingProductCategory);
        product.setCapacity(request.getCapacity());
        product.setColour(request.getColour());
        product.setDepth(request.getDepth());
        product.setHeight(request.getHeight());
        product.setSellers(existingSeller);
        product.setWarranty(request.getWarranty());
        product.setWeight(request.getWeight());
        product.setWidth(request.getWidth());
        product.setDeliveryCost(request.getDeliveryCost());

        productRepository.save(product);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record created successfully");

    }


    @Override
    /**
     * @Validating the list of existingProductCategory by uuid*
     * @Validate if the List of existingProductCategory is empty otherwise return record not found
     * Create the product definition and save
     * @return a Success Message* *
     * * */
    public ApiResponse<String> updateProduct(UUID productId, ProductRequest request) {

        Product product = validateProducts(productId);

        product.setCategory(request.getCategory());
        product.setModel(request.getModel());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setQuantity(request.getQuantity());
        product.setSalesPrice(request.getSalesPrice());
        product.setPhoto(request.getPhoto());
        product.setCapacity(request.getCapacity());
        product.setColour(request.getColour());
        product.setDepth(request.getDepth());
        product.setHeight(request.getHeight());
        product.setWarranty(request.getWarranty());
        product.setWeight(request.getWeight());
        product.setWidth(request.getWidth());


        productRepository.save(product);

        return new ApiResponse<String>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    "Record updated successfully");
        }



    @Override
    /**
     * @validating products by uuid*
     * @Validate if products is empty otherwise return record not found
     * @Delete product
     * @return a Success Message* *
     * * */
    public ApiResponse<String> deleteProduct(UUID productId) {
            Product product = validateProducts(productId);
            productRepository.delete(product);
            return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    "Record Deleted successfully");
        }


}
