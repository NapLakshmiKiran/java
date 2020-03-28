package com.operators;

/**
 * @author Lakshmi kiran Napa
 *  
 * This program demonstrates the usage of post increment operator in while loop.
 * Can you guess the output without compiling it in editor? :-)
 *
 */
public class IncrementalOperatorTest {
	public static void main(String arg[]) {
		int i = 10;
		while(i++ <= 10 ) {
			i++;
		}
		System.out.println(i);
	}
}
