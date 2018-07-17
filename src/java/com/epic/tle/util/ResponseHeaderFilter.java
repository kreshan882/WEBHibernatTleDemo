/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author chandana_l
 */
public class ResponseHeaderFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException{}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse)response;
        res.addHeader("X-Frame-Options", "DENY");
        res.addHeader("Content-Security-Policy-Report-Only", "object-src 'none'; style-src 'self' 'unsafe-inline'; img-src 'self'; media-src 'none'; frame-src 'none'; font-src 'self'; connect-src 'self'; report-uri REDACTED");
        res.addHeader("X-Content-Security-Policy-Report-Only", " object-src 'none'; style-src 'self' 'unsafe-inline'; img-src 'self'; media-src 'none'; frame-src 'none'; font-src 'self'; connect-src 'self'; report-uri REDACTED");
        chain.doFilter(request, res);
    }

    @Override
    public void destroy(){}

}
