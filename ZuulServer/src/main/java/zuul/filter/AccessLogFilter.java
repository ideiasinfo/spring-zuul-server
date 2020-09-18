package zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Enumeration;

@Component
public class AccessLogFilter extends ZuulFilter {
 
    Logger logger = LoggerFactory.getLogger(AccessLogFilter.class);
 
    @Override
    public String filterType() {
        return "post";
    }
 
    @Override
    public int filterOrder() {
        return 1;
    }
 
    @Override
    public boolean shouldFilter() {
        return true;
    }
 
    @Override
    public Object run() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        HttpServletResponse response = RequestContext.getCurrentContext().getResponse();

        Enumeration<String> headersNames = request.getHeaderNames();
        while(headersNames.hasMoreElements()) {
        		String header = headersNames.nextElement();
        		logger.debug("REQUEST :: HEADERS " + header+"="+request.getHeader(header));
        }
        
        logger.debug("REQUEST :: < " + request.getScheme() + " ; " + request.getLocalAddr() + " ; " + request.getLocalPort()+" ; "+request.getMethod() + " ; " + request.getRequestURI() + "; " + request.getProtocol()); 
        logger.debug("RESPONSE:: > HTTP:" + response.getStatus());
        
        Collection<String> rheaderNames = response.getHeaderNames();
        for(String s :rheaderNames) {
        		logger.debug("RESPONSE :: HEADERS " + s+"="+response.getHeader(s));
        }
 
        return null;
    }
}
