package no.ntnu.ProFlex.services;

import no.ntnu.ProFlex.models.Category;
import no.ntnu.ProFlex.controllers.web.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Returns all categories.
     *
     * @return categories
     */
    public Iterable<Category> getAll() {
        return this.categoryRepository.findAll();
    }

    /**
     * Find and return a category based on a given ID.
     *
     * @param id the id of the category you want to find-
     * @return the category.
     */
    public Category findById(int id) {
        return this.categoryRepository.findById(id).orElse(null);
    }

    /**
     * Add a category to the category repository.
     *
     * @param category the category you want to add to the repository.
     * @return a boolean statement. If it is added it return ture, else it return false.
     */
    public boolean add(Category category) {
        boolean added = false;
        if(canBeAdded(category)) {
            this.categoryRepository.save(category);
            added = true;
        }
        return added;
    }

    /**
     * Checks if a category can be added to the repository.
     *
     * @param category the category you want to check if it can be added.
     * @return boolean statement. Return true if can be added, false if it can not.
     */
    public boolean canBeAdded(Category category) {
        return category  !=null && category.isValid();
    }

    /**
     * Removes a category form the category repository.
     *
     * @param id the id of the category you want to remove.
     * @return a boolean statement. True if it was removed, false if it was not.
     */
    public boolean deleted(int id) {
        boolean deleted = false;
        if(findById(id) != null){
            this.categoryRepository.deleteById(id);
            deleted = true;
        }
        return deleted;
    }

    /**
     * Update a existing product.
     *
     * @param id The id of the category you want to update.
     * @param category the category you want the category to be updated to.
     */
    public void update(int id, Category category) {
        Category existingCategory = findById(id);
        String errorMessage = null;
        if (existingCategory == null) {
            errorMessage = "No catecory exists with the id " + id;
        }
        if(category == null || !category.isValid()) {
            errorMessage = "Wrong data in request body";
        }

        else if (category.getCid() != id) {
            errorMessage = "The ID of the category in the URL does not match anny ID in the JSON data";
        }
        if(errorMessage == null) {
            this.categoryRepository.save(category);
        }
    }
}
