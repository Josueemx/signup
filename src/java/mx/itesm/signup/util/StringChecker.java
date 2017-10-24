/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itesm.signup.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Morales
 */
public class StringChecker {
    
    public static boolean hasDigits(String str){
        for(int i=0; i<str.length(); i++){
            if(Character.isDigit(str.charAt(i)))
                return true;
        }
        return false;
    }
    
    public static boolean hasUpperAndLowerCase(String str){
        boolean lower = false;
        boolean upper = false;
        for(int i=0; i<str.length(); i++){
            if(Character.isLowerCase(str.charAt(i))){
                lower = true;
            }
            if(Character.isUpperCase(str.charAt(i))){
                upper = true;
            }
        }
        return lower&&upper;
    }
    
    public static boolean isAlphanumeric(final String str) {
        for(int i=0; i<str.length(); i++){
            if(!Character.isLetterOrDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
    
    public static boolean isValidName(final String str) {
        for(int i=0; i<str.length(); i++){
            char chr = str.charAt(i);
            if(!(Character.isLetter(chr)||chr==' '||chr=='.'))
                return false;
        }
        return true;
    }
}
