package br.org.doasoft.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by romildopaiter on 05/10/16.
 */
@Component
public class PostFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(PostFilter.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public Object run() {
        
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        logger.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
        
        try {
            InputStream is = ctx.getResponseDataStream();
            String responseBody = IOUtils.toString(is, "UTF-8");

            if (responseBody.contains("refresh_token")) {
                Map<String, Object> responseMap = mapper.readValue(responseBody, new TypeReference<Map<String, Object>>() {});
                String refreshToken = responseMap.get("refresh_token").toString();
                responseMap.remove("refresh_token");
                responseBody = mapper.writeValueAsString(responseMap);

                Cookie cookie = new Cookie("refreshToken", refreshToken);
                cookie.setHttpOnly(true);
                cookie.setSecure(true);
                cookie.setPath(ctx.getRequest().getContextPath() + "/oauth/token");
                cookie.setMaxAge(2592000); // 30 days
                ctx.getResponse().addCookie(cookie);

            }
            ctx.setResponseBody(responseBody);

        } catch (IOException e) {
            logger.error("Error occured in zuul post filter", e);
        }
        return null;
    }
 
            @Override
    public boolean shouldFilter() {
        return true;
    }
 
            @Override
    public int filterOrder() {
        return 10;
    }
 
            @Override
    public String filterType() {
        return "post";
    }

}
