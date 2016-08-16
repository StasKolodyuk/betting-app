
package by.bsu.kolodyuk.bettingapp.utility;


import by.bsu.kolodyuk.bettingapp.exception.TechnicalException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;


public class JSONUtil {
    
    private JSONUtil() {}
    
    public static <T> T fromJSON(String content, Class<T> clazz) throws TechnicalException {
        try {
            return new ObjectMapper().readValue(content, clazz);
        } catch (IOException e) {
            throw new TechnicalException(e);
        }
    }
    
    public static <T> T fromJSON(String content, TypeReference<T> typeReference) throws TechnicalException {
        try {
            return new ObjectMapper().readValue(content, typeReference);
        } catch (IOException e) {
            throw new TechnicalException(e);
        }
    }
    
    public static String toJSON(Object obj) throws TechnicalException {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            throw new TechnicalException(ex);
        }
    }

}
