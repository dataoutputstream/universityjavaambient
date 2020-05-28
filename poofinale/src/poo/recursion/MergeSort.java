package poo.recursion;
import poo.util.*;

public final class MergeSort {

	private enum OP{ MERGE, MERGESORT }

	public static <T extends Comparable<? super T>> void mergeSort( T []v ){
		mergeSortIterativo( v, 0, v.length-1 );
	}

	private static <T extends Comparable<? super T>> void mergeSort( T []v, int inf, int sup ){
		if( inf<sup ){
			int med=(inf+sup)/2;
			mergeSort(v,inf,med);
			mergeSort(v,med+1,sup);
			merge(v,inf,med,sup);
		}
	}//mergeSort

	private static <T extends Comparable<? super T>> void mergeSortIte( T []v, int inf, int sup ){
		class AreaDati{
			int inf, med, sup;
			OP op;
			public AreaDati( int inf, int med, int sup, OP op ){
				this.inf=inf; this.med=med; this.sup=sup; this.op=op;
			}
			public AreaDati( int inf, int sup, OP op ){
				this.inf=inf; this.sup=sup; this.op=op;
			}
		}//AreaDati
		Stack<AreaDati> stack=new StackConcatenato<>();
		stack.push( new AreaDati(inf,sup,OP.MERGESORT)); //push area dati prima attivazione
		while( !stack.isEmpty() ){
			AreaDati ac=stack.pop(); //area dati attivazione corrente
			if( ac.op==OP.MERGE ) merge(v,ac.inf,ac.med,ac.sup);
			else{ //op==OP.MERGESORT
				if( ac.inf<ac.sup ){
					int med=(ac.inf+ac.sup)/2;
					stack.push( new AreaDati( ac.inf, med, ac.sup, OP.MERGE ) );
					stack.push( new AreaDati( med+1, ac.sup, OP.MERGESORT ) );
					stack.push( new AreaDati( ac.inf, med, OP.MERGESORT ) );
				}
			}
		}
	}//mergeSortIte

	private static <T extends Comparable<? super T>> void mergeSortIterativo( T []v, int infe, int supe ){
		final int DIM=500;
		int INF[]=new int[DIM];
		int MED[]=new int[DIM];
		int SUP[]=new int[DIM];
		OP op[]=new OP[DIM];
		int top=0;
		int inf, med, sup;
		INF[top]=infe; SUP[top]=supe; op[top]=OP.MERGESORT; top++; //prima attivazione
		while( top!=0 ){
			top--;
			inf=INF[top]; med=MED[top]; sup=SUP[top]; OP o=op[top]; //attivazione corrente
			if( o==OP.MERGE ) merge( v, inf, med, sup );
			else{ //o==OP.MERGESORT
				if( inf<sup ){
					med=(inf+sup)/2;
					INF[top]=inf; MED[top]=med; SUP[top]=sup; op[top]=OP.MERGE; top++;
					INF[top]=med+1; SUP[top]=sup; op[top]=OP.MERGESORT; top++;
					INF[top]=inf; SUP[top]=med; op[top]=OP.MERGESORT; top++;
				}
			}
		}
	}//mergeSortIterativo

	@SuppressWarnings("unchecked")
	private static <T extends Comparable<? super T>> void merge( T []v, int inf, int med, int sup ){
		T[] aux=(T[]) new Comparable[sup-inf+1];
		int i=inf, j=med+1, k=0;
		while( i<=med && j<=sup ){
			if( v[i].compareTo(v[j])<0 ){ aux[k]=v[i]; i++; }
			else{ aux[k]=v[j]; j++; }
			k++;
		}
		while( i<=med ){
			aux[k]=v[i]; i++; k++;
		}
		while( j<=sup ){
			aux[k]=v[j]; j++; k++;
		}
		for( k=0; k<aux.length; k++ ) v[k+inf]=aux[k];
	}//merge

	public static void main( String[] args ){
		String s[]={"zaino","casa","sole","abaco","turing","java","flamenco","scala","lisp","php"};
		mergeSort(s);
		System.out.println(java.util.Arrays.toString(s));
	}
}//MergeSort
