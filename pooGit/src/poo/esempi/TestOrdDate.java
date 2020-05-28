package poo.esempi;

import poo.date.Data;
import poo.util.Array;
import java.util.*;

public class TestOrdDate {
	public static void main(String[]args) {
		Data[] a = { new Data(14, 3, 2011), new Data(13, 3, 2011), new Data(16, 2, 2010), new Data(23, 06, 2011) };
		System.out.println(Arrays.toString(a));
		Array.selectionSort(a);
		System.out.println(Arrays.toString(a));
	} // main
} // TestOrdData
