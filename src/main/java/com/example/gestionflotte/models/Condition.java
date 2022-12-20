package com.example.gestionflotte.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Condition {

    Pattern regex;
    String[] methods;

    public Condition(String regex, String... methods){
        this.regex=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        this.methods=methods;
    }

    public boolean allowed(String url,String method){
        Matcher m=regex.matcher(url);
        return m.matches()&&checkMethod(method);
    }

    private boolean checkMethod(String method){
        for (String s : methods) {
            if (method.equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }
}
