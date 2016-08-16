
package by.bsu.kolodyuk.bettingapp.utility;

import java.util.ResourceBundle;


public class PathManager {
    
    private static final String PAGE_PATH_PROPERTY_PREFIX = "path.page.";
    private static final String CONFIG_FILE_NAME = "config";
    
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(CONFIG_FILE_NAME);
    
    private PathManager() {
        
    }
    
    public static String getPagePath(String page) {
        return resourceBundle.getString(PAGE_PATH_PROPERTY_PREFIX + page);
    }
    
}
