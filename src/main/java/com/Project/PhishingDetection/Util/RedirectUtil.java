package com.Project.PhishingDetection.Util;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class RedirectUtil {

    public static  int  countRedirects(String urlstring){
        int redirectcount = 0;
        int maxredirects = 5;

        try {
            while (redirectcount<maxredirects){

                URL url = new URL(urlstring);

                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setInstanceFollowRedirects(false);
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);

                int status = conn.getResponseCode();

                if (status ==  301 || status == 302 || status == 307|| status == 308){
                 String newUrl = conn.getHeaderField("Location");

                 if (newUrl ==null) break;

                 urlstring = newUrl;
                 redirectcount++;
                }else{
                    break;
                }

            }
        }catch (Exception e){
        }
        return redirectcount;

    }
}
