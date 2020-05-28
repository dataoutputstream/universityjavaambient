package poo.esempi;

import poo.razionali.*;
import poo.util.Array;
import java.util.*;

public class TestOrdRaz {
	public static void main(String[]args) {
		Razionale[] a = { new Razionale(3,7), new Razionale(4,5), new Razionale(-2,3), new Razionale(3,2) };
		System.out.println(Arrays.toString(a));
		Array.selectionSort(a);
		System.out.println(Arrays.toString(a));
	} // main
} // TestOrdRaz
