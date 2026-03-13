package com.Project.PhishingDetection.Util;

import java.util.List;

public class KeywordUtil {

    private static final List<String> PhisingKeyword =  List.of( "login","verify","bank","secure","update",
            "account","otp","wallet","reward","bonus",
            "suspended","alert","confirm","kyc");

    public static int KeywordRiskscore(String url){
        int score = 0;
        for (String keyword: PhisingKeyword){
            if (url.toLowerCase().contains(keyword)){
                score+=8;
            }
        }
        return score;
    }
}
