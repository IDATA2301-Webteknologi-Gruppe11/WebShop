package no.ntnu.proflex.products;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Legal Online Scheduling Software Product.
 *
 * @author Ole Kristian Dvergsdal
 * @version 1.0
 */
@Schema(description = "A product", title = "Online Scheduling Software")
public class OnlineSchedulingSoftwareProduct extends Product {

    /**
     * Create a Online Scheduling Software Product
     */
    public OnlineSchedulingSoftwareProduct() throws IllegalArgumentException {
        super("Online Scheduling Software", 1, 2000, "planning, scheduling");
    }
}
