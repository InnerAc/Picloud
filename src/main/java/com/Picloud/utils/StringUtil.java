package com.Picloud.utils;

public class StringUtil {
        
        public static boolean isBlank(String str){
                if(str == null || str.length() == 0)
                        return false;
                return true;
        }

}
