package no.ntnu.ProFlex.controllers;

import io.swagger.v3.oas.annotations.Operation;
import no.ntnu.ProFlex.models.Category;
import no.ntnu.ProFlex.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @Operation
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories() {
        Iterable<Category> categories = this.categoryRepository.findAll();
        if(!categories.iterator().hasNext()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok((List<Category>) categories);
    }

    @Operation
    @GetMapping("/category/{id}")
    public ResponseEntity<Optional<Category>> getCategoryFromGivenId(int id) {
        Optional<Category> categoryOptional = this.categoryRepository.findById(id);
        if(categoryOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(this.categoryRepository.findById(id));
    }

    @Operation
    @PutMapping("/category/{id}")
    public ResponseEntity<Category> UpdateCategory(int id, Category category) {
        Optional<Category> oldCategoryOptional = this.categoryRepository.findById(id);
        if(oldCategoryOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Category oldCategory = oldCategoryOptional.get();
        Category updateCategory = this.categoryRepository.save(category);
        if(updateCategory.equals(oldCategory)) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
        else {
            return ResponseEntity.ok(updateCategory);
        }
    }

    @Operation
    @DeleteMapping("/category/{id}")
    public ResponseEntity<Category> deleteCategory (int id) {
        if(this.categoryRepository.existsById(id)) {
            this.categoryRepository.deleteById(id);
            if(this.categoryRepository.existsById(id)) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            else {
                return ResponseEntity.ok().build();
            }
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
