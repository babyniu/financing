package com.github.financing.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Crazz on 2/2/16.
 */
public class ApiUtils {

    public static String genSSN(){
        return UUID.randomUUID().toString().replaceAll("-","").substring(2);
    }

    public static void sign(Map<String,String> formData){
        Object[] keyArray = formData.keySet().toArray();
        Arrays.sort(keyArray);
        List<String> list = new ArrayList<String>();
        for(Object key:keyArray){
            list.add(formData.get(key));
        }
        String src = "";
//        String src = StringUtils.collectionToDelimitedString(list,"|");
//        System.out.println("BEFORE SIGN:"+src);
        formData.put("signature",CipherUtils.rsaSign(AppConst.MCHNT_RSA_PRIVATE_KEY,src));
    }
}
