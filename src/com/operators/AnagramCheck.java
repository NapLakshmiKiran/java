package com.operators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Lakshmi kiran
 *
 *	This program finds the list of all anagram words and filter the top n elements with max length from given set of words
 *
 */
public class AnagramCheck {

	static final int NO_OF_CHARS = 256;
	
	public static void main(String[] args) {
		
		String arr[] = {"racecar", "carrace", "abcd", "xyz", "cat", "tac", "abcd", "bacd", "hometown", "townomeh"};
		 
		int n = arr.length; 
		findAllAnagrams(arr, n);

	}

	static void findAllAnagrams(String arr[], int n) 
    { 
    	List<String> res = new ArrayList<String>();
        for (int i = 0; i < n; i++) 
            for (int j = i+1; j < n; j++) 
                if (areAnagram(arr[i], arr[j])) {
                	
                	res.add(arr[i] +  " is anagram of " + arr[j]);
     }
                   
        Collections.sort(res, Comparator.comparing(String::length).reversed());
        res.stream().limit(3).forEach(System.out::println);
    } 
    
    static boolean areAnagram(String str1, String str2) 
    { 
        int[] count = new int[NO_OF_CHARS]; 
        int i; 
  
        for (i = 0; i < str1.length() && i < str2.length(); i++) 
        { 
            count[str1.charAt(i)]++; 
            count[str2.charAt(i)]--; 
        } 
  
        if (str1.length() != str2.length()) 
          return false; 
  
        for (i = 0; i < NO_OF_CHARS; i++) 
            if (count[i] != 0) 
                return false; 
         return true; 
    }
}
