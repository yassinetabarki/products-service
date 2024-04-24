package micro.service.products.Controllers;

import lombok.AllArgsConstructor;
import micro.service.products.Responses.ResponseHandler;
import micro.service.products.Services.ProductService;
import micro.service.products.TDOs.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "api/v1/products", produces = "application/json")
@AllArgsConstructor
@CrossOrigin(origins = {"http://127.0.0.1:3000/", "http://localhost:3000/"})
public class ProductController {
    private final ProductService service;

    @GetMapping
    public List<ProductDTO> getProducts() {
        return service.getProducts();
    }


    @PostMapping
    public ProductDTO store(@RequestBody ProductDTO product) {
        return service.addProduct(product);
    }

    @PutMapping("{productId}")
    public ResponseEntity<Object> update(@PathVariable("productId") Long productId, @RequestBody ProductDTO product) {
        try {
            ProductDTO response = service.updateProduct(productId, product);

            return ResponseHandler.responseHandler("product updated successfully !!!", HttpStatus.OK, response);
        } catch (Exception ex) {
            return ResponseHandler.responseHandler("Product Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{productId}")
    public ResponseEntity<?> getProduct(@PathVariable Long productId) {
        try {
            ProductDTO response = service.getProduct(productId);
            return ResponseHandler.responseHandler(HttpStatus.OK, response);
        } catch (Exception ex) {
            return ResponseHandler.responseHandler("product not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        try {
             service.destroyProduct(productId);
            return ResponseHandler.responseHandler("product deleted successfully",HttpStatus.OK);
        } catch (Exception ex) {
            return ResponseHandler.responseHandler("product not found", HttpStatus.NOT_FOUND);
        }
    }
}
