package com.Picloud.utils;

import java.util.Arrays;
import java.util.List;

public class StringSplit {

        private static String div = "`";

        public static List<String> stringSplit(String string, String div) {
                String[] strings = string.split(div);
                List<String> list = Arrays.asList(strings);
                return list;
        }

        public static String descSplit(String str, int n) {
                String[] strs = str.split(div);
                return strs[n];
        }

}
