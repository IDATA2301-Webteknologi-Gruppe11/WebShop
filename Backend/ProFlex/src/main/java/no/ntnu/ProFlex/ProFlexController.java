package no.ntnu.ProFlex;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the Rest Controller class for the ProFlex WebSite API
 *
 * @author Ole Kristian Dvergsdal
 * @author Mikkel
 * @author Håvard Vestbø
 * @author Christian
 * @version 1.0
 */
@RestController
public class ProFlexController {

    public void proFlexController() {
        initializeData();
    }

    /**
     * Initilize data that is used in the controller.
     */
    private void initializeData() {
    }
}
