package com.joshua.summonerswar.global.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private String errorPage;

    private ObjectMapper objectMapper = new ObjectMapper();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void handle(final HttpServletRequest request, final HttpServletResponse response, final AccessDeniedException accessDeniedException) throws IOException, ServletException {

//        if (WebUtil.isAjax(request)) {
//            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//            response.getWriter().write(this.objectMapper.writeValueAsString(ResponseEntity.status(HttpStatus.FORBIDDEN)));
//        } else {
            String deniedUrl = errorPage + "?exception=" + "Access is denied";
            redirectStrategy.sendRedirect(request, response, deniedUrl);
//        }
    }

    public void setErrorPage (String errorPage) {
        if ((errorPage != null) && !errorPage.startsWith("/")) {
            throw new IllegalArgumentException("errorPage must begin with '/'");
        }

        this.errorPage = errorPage;
    }

}
