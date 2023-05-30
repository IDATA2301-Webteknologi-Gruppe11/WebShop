package no.ntnu.ProFlex.services;

import jakarta.servlet.http.HttpServletRequest;

/***
 * Works as utility for email service
 *
 * @author IDATA2306 Group 11
 * @version 1.0
 */
public class Utility {
    public static String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
