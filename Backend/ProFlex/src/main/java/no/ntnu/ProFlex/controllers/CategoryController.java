package no.ntnu.ProFlex.controllers;

import io.swagger.v3.oas.annotations.Operation;
import no.ntnu.ProFlex.models.Category;
import no.ntnu.ProFlex.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CategoryController {

    private CategoryService categoryService = new CategoryService();

    @Operation
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories() {
        Iterable<Category> categories = this.categoryService.getAll();
        if(!categories.iterator().hasNext()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok((List<Category>) categories);
    }

    @Operation
    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getCategoryFromGivenId(int id) {
        Category category = this.categoryService.findById(id);
        if(category == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category);
    }

    @Operation
    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(Category category) {
        if(!this.categoryService.add(category)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else {
            return new ResponseEntity<>(category, HttpStatus.CREATED);
        }
    }

    @Operation
    @PutMapping("/category/{id}")
    public ResponseEntity<Category> UpdateCategory(int id, Category category) {
        Category oldCategory = this.categoryService.findById(id);
        if(oldCategory == null) {
            return ResponseEntity.notFound().build();
        }
        this.categoryService.update(id, category);
        if(this.categoryService.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        else {
            return ResponseEntity.ok(category);
        }
    }

    @Operation
    @DeleteMapping("/category/{id}")
    public ResponseEntity<Category> deleteCategory (int id) {
        if(!this.categoryService.deleted(id)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        else {
            return ResponseEntity.ok().build();
        }
    }
}
