package com.bytedance.android.lesson.restapi.solution.bean;

import com.bytedance.android.lesson.restapi.solution.utils.NetworkUtils;

/**
 * @author Xavier.S
 * @date 2019.01.17 18:08
 */
public class Cat {

    // TODO-C1 (1) Implement your Cat Bean here according to the response json
    private String URL;

    public String getURL(){
        return URL;
    }

    public void setURL(String path){
        String s = NetworkUtils.getResponseWithHttpURLConnection(path);
        String[] str = s.split("\"");
        for(int i=0;i<str.length;i++){
            if(str[i].equals("url")){
                this.URL = str[i+2];
                break;
            }
        }

        System.out.println(this.URL);
    }
}
