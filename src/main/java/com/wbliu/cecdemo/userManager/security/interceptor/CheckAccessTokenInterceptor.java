package com.wbliu.cecdemo.userManager.security.interceptor;

import com.wbliu.cecdemo.userManager.status.StatusDTO;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.wbliu.cecdemo.userManager.property.Global.ACCESS_TOKEN;
import static com.wbliu.cecdemo.userManager.status.ReturnCodeEnum.UNAUTHORIZED_ERROR;
import static com.wbliu.cecdemo.userManager.status.ReturnCodeEnum.UNAUTHORIZED_ERROR_DESC;
import static org.apache.commons.lang.StringUtils.isBlank;

/**
 * @author wbliu
 * @create 2017-03-02 16:29
 **/

public class CheckAccessTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private DefaultTokenServices tokenServices;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        return true;
        String accessToken = request.getHeader(ACCESS_TOKEN);
        OAuth2AccessToken oAuth2AccessToken = tokenServices.readAccessToken(accessToken);
        if (isBlank(accessToken) || oAuth2AccessToken == null || oAuth2AccessToken.isExpired()) {
            StatusDTO statusDTO = new StatusDTO(UNAUTHORIZED_ERROR.toString(), UNAUTHORIZED_ERROR_DESC.toString());
            response.getWriter().write(new Gson().toJson(statusDTO));
            response.setContentType("application/json;charset=UTF-8");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
