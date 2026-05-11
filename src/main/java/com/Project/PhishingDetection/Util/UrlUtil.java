package com.Project.PhishingDetection.Util;

import java.net.URI;
import java.net.URISyntaxException;

public class UrlUtil {
    // extract domain
    public  static  String extractDomain(String url){

        try{
            URI uri = new URI(url);

            String domain = uri.getHost();

            if (domain ==null) return null;

            if (domain.startsWith("www.")){
                domain = domain.substring(4);
            }

            return domain.toLowerCase();

        } catch (Exception e) {
            return null ;
        }

    }
}
