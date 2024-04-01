package com.example.RestExample_validate;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final List<Product> products = new ArrayList<>();
    @PostMapping
    public ResponseEntity<?> addProduct(@Valid @RequestBody Product product, BindingResult result) {
    	List<String> displayErrors = new ArrayList<String>();
        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            for(FieldError err:errors) {
            	displayErrors.add(err.getField() + ": " + err.getDefaultMessage());
            	System.out.println(displayErrors);
            }
            return ResponseEntity.badRequest().body(displayErrors);
        }
        products.add(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
    
    @GetMapping
    public List<Product> getProduct(){
    	return products;
    }

