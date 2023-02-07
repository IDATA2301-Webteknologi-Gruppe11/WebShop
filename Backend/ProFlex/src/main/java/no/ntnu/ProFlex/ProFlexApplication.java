package no.ntnu.ProFlex;

import no.ntnu.ProFlex.Controllers.ProFlexController;
import no.ntnu.ProFlex.Controllers.ProductController;
import no.ntnu.ProFlex.Products.ProductList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProFlexApplication {

	//Controllers
	ProductController productController = new ProductController();

	//data
	ProductList productList;

	public static void main(String[] args) {
		SpringApplication.run(ProFlexApplication.class, args);
	}

	public ProFlexApplication() {
		//this.productList = new ProductList();
		//initializeData();
	}

	/**
	 * Initilize data that is used in the controller.
	 */
	private void initializeData() {
		//productController.setProductList(this.productList);
	}

	/**
	 * Returns the productlist
	 * @return productlist
	 */
	public ProductList getProductList() {
		return this.productList;
	}

}
