
package by.bsu.kolodyuk.bettingapp.utility;


public class URIUtil {
    
    public static String getPageFromURI(String requestURI) {
        String page = "";
        int lastSlashIndex = requestURI.lastIndexOf('/');
        int lastDotIndex = requestURI.lastIndexOf('.');
        if(lastSlashIndex != -1 && lastDotIndex != -1) {
            page = requestURI.substring(lastSlashIndex+1, lastDotIndex);
        }
        return page;
    }
    
}
