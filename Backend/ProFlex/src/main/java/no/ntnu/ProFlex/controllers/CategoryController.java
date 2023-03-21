package no.ntnu.ProFlex.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import no.ntnu.ProFlex.models.Category;
import no.ntnu.ProFlex.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * This class represent the controller for category.
 *
 * @author Ole Kristian
 * @version 1.0
 */
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService; // TODO Swagger documentasjon

    /**
     * Returns all categories.
     * Also, http status. OK if it works, NOT FOUND if nothing is found.
     *
     * @return All users, Http status.
     */
    @Operation(summary = "Get all categories", description = "Returns all categories form the category repository and returns https status.")
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories() {
        Iterable<Category> categories = this.categoryService.getAll();
        if (!categories.iterator().hasNext()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok((List<Category>) categories);
    }

    /**
     * Return a category form a given id.
     * Also, https status. OK if it works, NOT FOUND of its not found.
     *
     * @param id of the category you want to find.
     * @return a category. Http status.
     */
    @Operation(summary = "Get a category", description = "Returns a category form the category repository and return http status.")
    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getCategoryFromGivenId(
            @Parameter(name = "id", description = "Id of the category you want to return") @PathVariable int id) {
        Category category = this.categoryService.findById(id);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category);
    }

    /**
     * Create and add a category to the category repository.
     * Also, A http status. OK if it works, BAD REQUEST if something is wrong with
     * the category.
     *
     * @param category the category you want to add.
     * @return the created category, http status
     */
    @Operation(summary = "Create a category", description = "Create and add category to the category repository and return a http status")
    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(
            @Parameter(name = "category", description = "The category you want to add") @RequestBody Category category) {
        if (!this.categoryService.add(category)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(category); //TODO høre om created eller ok.
        }                                                                    //TODO høre om postman testen og.
    }

    /**
     * Update an existing category.
     * Also, http statement. OK if it works, NOT FOUND if the category with id does
     * not exists and INTERNAL SERVER ERROR if something wrong happens.
     *
     * @param id       the id of the category that you want to update.
     * @param category the category that you want to update the category to.
     * @return The updated category, http status.
     */
    @Operation(summary = "update a existing category", description = "update a existing category int the category repository and return a http status")
    @PutMapping("/category/{id}")
    public ResponseEntity<Category> updateCategory(
            @Parameter(name = "id", description = "the id of the category you want to update") @PathVariable int id,
            @Parameter(name = "category", description = "the cateogry you want the existing one to be update to") @RequestBody Category category) {
        Category oldCategory = this.categoryService.findById(id);
        if (oldCategory == null) {
            return ResponseEntity.notFound().build();
        }
        this.categoryService.update(id, category);
        if (this.categoryService.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } else {
            return ResponseEntity.ok(category);
        }
    }

    /**
     * Remove a category.
     * Also, http status. OK if it works, INTERNAL SERVER ERROR if it did not work.
     *
     * @param id of the category you want to remove.
     * @return http status
     */
    @Operation(summary = "Remove a category", description = "Removes a category form the category repository and return a http status")
    @DeleteMapping("/category/{id}")
    public ResponseEntity<Category> deleteCategory(
            @Parameter(name = "id", description = "the id of the category you want to remove") @PathVariable int id) {
        if (!this.categoryService.deleted(id)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } else {
            return ResponseEntity.ok().build();
        }
    }
}