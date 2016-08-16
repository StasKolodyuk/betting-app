
package by.bsu.kolodyuk.bettingapp.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class EncodingFilter implements Filter {
    
    private static final String ENCODING_INIT_PARAMETER = "encoding";
    
    private String encoding;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter(ENCODING_INIT_PARAMETER);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String currentEncoding = request.getCharacterEncoding();
        if(encoding != null && !encoding.equalsIgnoreCase(currentEncoding)) {
            request.setCharacterEncoding(encoding);
            response.setCharacterEncoding(encoding);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        
    }
    
}