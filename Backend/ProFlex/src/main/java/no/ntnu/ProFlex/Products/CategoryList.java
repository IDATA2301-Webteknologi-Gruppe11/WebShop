package no.ntnu.ProFlex.Products;

import no.ntnu.ProFlex.entities.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryList {

    private List<Category> categoryList = new ArrayList();

    private final Category tax = new Category(1, "tax");
    private final Category planning = new Category(2,"planning");
    private final Category scheduling = new Category(3,"scheduling");
    private final Category automation = new Category(4,"automation");
    private final Category processes = new Category(5,"processes");
    private final Category legal = new Category(6,"legal");
    private final Category accounting = new Category(6,"accounting");
    private final Category finance = new Category(6,"finance");

    public CategoryList() {

    }

    public void intialize() {
        this.categoryList.add(this.tax);
        this.categoryList.add(this.planning);
        this.categoryList.add(this.scheduling);
        this.categoryList.add(this.automation);
        this.categoryList.add(this.processes);
        this.categoryList.add(this.legal);
        this.categoryList.add(this.accounting);
        this.categoryList.add(this.finance);
    }

    /**
     * Merge sort algorithm that sorts the products in the production from low to high number baste of the product ID.
     * @param list the list that is getting sorted.
     */
    private static void mergeSort(List<Category> list) {
        int n = list.size();
        if (n < 2) {
            return;
        }

        int mid = n / 2;
        List<Category> left = new ArrayList<>(list.subList(0, mid));
        List<Category> right = new ArrayList<>(list.subList(mid, n));

        mergeSort(left);
        mergeSort(right);
        merge(list, left, right);
    }

    /**
     * Merges the different sub arrays.
     * @param list the list that is sorted.
     * @param left left sub array.
     * @param right right sub array.
     */
    private static void merge(List<Category> list, List<Category> left, List<Category> right) {
        int i = 0;
        int j = 0;
        int k = 0;
        int leftSize = left.size();
        int rightSize = right.size();

        while (i < leftSize && j < rightSize) {
            if (left.get(i).getCid() <= right.get(j).getCid()) {
                list.set(k++, left.get(i++));
            } else {
                list.set(k++, right.get(j++));
            }
        }

        while (i < leftSize) {
            list.set(k++, left.get(i++));
        }
        while (j < rightSize) {
            list.set(k++, right.get(j++));
        }
    }

    /**
     * Search for the index of a given category ID in the category list.
     * Returns -1 if the id dose not exists in the list.
     * @param list the category list.
     * @param left left most index index.
     * @param right right most index.
     * @param cid the category id that you are searching for.
     * @return the index of the category.
     */
    private static int binarySearch(List<Category> list, int left, int right, int cid) {
        if(right >= left) {
            int mid = left + (right - left) / 2;
            if(list.get(mid).getCid() == cid) {
                return mid;
            }
            if(list.get(mid).getCid() > cid) {
                return binarySearch(list, left, mid - 1, cid);
            }
            return binarySearch(list, mid + 1, right, cid);
        }
        return -1;
    }

    /**
     * Returns categories.
     * @return categories.
     */
    public List<Category> getCategories() {
        return this.categoryList;
    }

    /**
     * Returns the category by a given cid.
     * @param cid the cid of the category that you want.
     * @return category.
     */
    public Category getCategoryByGivenId(int cid) {
        mergeSort(categoryList);
        int i = binarySearch(this.categoryList, 0, this.categoryList.size() - 1, cid);
        if(i == -1) {
            return null;
        }
        return this.categoryList.get(i);
    }
}
