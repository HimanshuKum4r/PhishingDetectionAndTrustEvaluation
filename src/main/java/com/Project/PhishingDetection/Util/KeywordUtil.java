package com.Project.PhishingDetection.Util;

import com.Project.PhishingDetection.dto.RiskSignalDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class KeywordUtil {

    private  final List<String> PhisingKeyword =  List.of( "login","verify","bank","secure","update",
            "account","otp","wallet","reward","bonus",
            "suspended","alert","confirm","kyc","login", "verify", "secure",
            "bank", "update", "reward", "otp", "wallet");

    public  List<RiskSignalDTO> KeywordRiskscore(String url){
        List<RiskSignalDTO> signals = new ArrayList<>();
        for (String keyword: PhisingKeyword){
            if (url.toLowerCase().contains(keyword)){
               signals.add(new RiskSignalDTO(
                       "suspicious keyword : "+keyword,
                       10
               ));
            }
        }
        return signals;
    }
}
