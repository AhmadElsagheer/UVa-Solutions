package cp3_2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class MappingTheSwaps_UVa331 {

	static int N;
	static int counter;
	
	static void backtrack(int[] array)
	{
		if(sorted(array))
		{
			++counter;
			return;
		}
		for(int i = 0; i < N - 1; i++)
			if(array[i] > array[i+1])
			{
				swap(i,array);
				backtrack(array);
				swap(i,array);
			}
	}
	
	static void swap(int i, int[] array)
	{
		int tmp = array[i];
		array[i] = array[i+1];
		array[i+1] = tmp;
	}
	
	static boolean sorted(int[] array)
	{
		for(int i = 0; i < N - 1; i++)
			if(array[i]>array[i+1])
				return false;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int k = 1;
		StringTokenizer st;
		while(true)
		{
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			if(N==0)
				break;
			int[] array = new int[N];
			for(int i = 0; i < N; i++)
				array[i]  =  Integer.parseInt(st.nextToken());
			
			counter = 0;
			backtrack(array);
			if(sorted(array))
				out.printf("There are %d swap maps for input data set %d.\n",0,k++);
			else
				out.printf("There are %d swap maps for input data set %d.\n",counter,k++);
		}
		out.flush();
	}
}
