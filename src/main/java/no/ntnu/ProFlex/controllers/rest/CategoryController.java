package no.ntnu.ProFlex.controllers.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import no.ntnu.ProFlex.models.Category;
import no.ntnu.ProFlex.services.CategoryService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.logging.Logger;

/**
 * This class represent the controller for category.
 *
 * @author IDATA2306 Group 11
 * @version 1.0
 */
@RestController
@RequestMapping("/api/category")
@Api(value = "Category API", tags = "Category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService; // TODO Swagger documentasjon

    private static final String JSONEEXCEPTIONMESSAGE = "The Field(s) in the request is missing or is null";
    private static final String SEVERE = "An error occurred: ";
    private static final Logger LOGGER = Logger.getLogger(CategoryController.class.getName());
    /**
     * Returns all the products.
     *
     * @return all products
     */
    @ApiOperation(value = "Get all categories", notes = "Returns all categories form the category repository and returns http status.")
    @GetMapping("/getAll")
    public ResponseEntity<List<Category>> getCategories() {
        Iterable<Category> categories = this.categoryService.getAll();
        if (!categories.iterator().hasNext()) {
            return new ResponseEntity("Didn't find categories", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok((List<Category>) categories);
    }

    /**
     * Returns the category of a given ID.
     *
     * @param id the ID of the category to retrieve
     * @return the category of the given ID
     */
    @ApiOperation(value = "Get a category", notes = "Returns a category form the category repository and return http status.")
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryFromGivenId(
            @Parameter(name = "id", description = "Id of the category you want to return", required = true)
            @PathVariable int id) {
        Category category = this.categoryService.findById(id);
        if (category == null) {
            return new ResponseEntity("Didn't find category", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(category);
    }

    /**
     * Creates and adds a category.
     *
     * @param category the category that is getting created
     * @return a ResponseEntity with an HTTP status indicating the success or failure of the operation
     * @exception JSONException if an error occurs while creating the product
     */
    @ApiOperation(value = "Create a category", notes = "Create and add category to the category repository and return a http status")
    @PostMapping("/add")
    public ResponseEntity<Category> createCategory(
            @Parameter(name = "category", description = "The category you want to add", required = true)
            @RequestBody Category category) {
        try {
            if (!this.categoryService.add(category)) {
                return new ResponseEntity("Category was not added", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity("Category was added", HttpStatus.CREATED);
        }
        catch (JSONException e) {
            LOGGER.severe(SEVERE + e.getMessage());
            return new ResponseEntity(JSONEEXCEPTIONMESSAGE, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update the category for a given ID.
     *
     * @param id the ID of the category to update
     * @param category new category of the product
     * @return a ResponseEntity with an HTTP status indicating the success or failure of the operation
     * @exception JSONException if an error occurs while updating the product
     */
    @ApiOperation(value = "update a existing category", notes = "update a existing category int the category repository and return a http status")
    @PutMapping("/update/{id}")
    public ResponseEntity<Category> updateCategory(
            @Parameter(name = "id", description = "the id of the category you want to update", required = true)
            @PathVariable int id,
            @Parameter(name = "category", description = "the cateogry you want the existing one to be update to", required = true)
            @RequestBody Category category) {
        try {
            Category oldCategory = this.categoryService.findById(id);
            if (oldCategory == null) {
                return new ResponseEntity("didn't find category", HttpStatus.NOT_FOUND);
            }
            this.categoryService.update(id, category);
            if (this.categoryService.findById(id) == null) {
                return new ResponseEntity("Category didn't update", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity("Category was updated", HttpStatus.OK);
        }
        catch (JSONException e) {
            LOGGER.severe(SEVERE + e.getMessage());
            return new ResponseEntity(JSONEEXCEPTIONMESSAGE, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Deletes a category from the category list with the given ID.
     *
     * @param id the ID of the category to delete
     * @return a ResponseEntity with an HTTP status indicating the success or failure of the operation
     * @exception JSONException  if an error occurs while deleting the product
     */
    @ApiOperation(value = "Remove a category", notes = "Removes a category form the category repository and return a http status")
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Category> deleteCategory(
            @Parameter(name = "id", description = "the id of the category you want to remove", required = true)
            @PathVariable int id) {
        try {
            if (!this.categoryService.deleted(id)) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            return ResponseEntity.ok().build();
        }
        catch (JSONException e) {
            LOGGER.severe(SEVERE + e.getMessage());
            return new ResponseEntity(JSONEEXCEPTIONMESSAGE, HttpStatus.BAD_REQUEST);
        }
    }
}