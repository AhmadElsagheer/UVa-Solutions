package v005;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class SumItUp_UVa574 {

	static int[] list;
	static int[] sel;
	static int N, T;
	static PrintWriter out = new PrintWriter(System.out);
	static int counter;
	static int[] unmap;
	
	public static void backtrack(int i)
	{
		if(i==-1) {if(possible()) print();return;}
		for(int k = list[i]; k >= 0; k--)
		{
			sel[i] = k;
			backtrack(i-1);
		}
	}
	
	public static boolean possible()
	{
		int sum = 0;
		for(int i = N - 1; i >= 0; i--)
			sum += sel[i]*unmap[i];
		return sum == T;
	}
	
	public static void print()
	{
		counter++;
		boolean first = true;
		for(int i = N - 1; i >= 0; i--)
		{
			int x = sel[i]; 
			while(x-->0)
			{
				if(first)
					first = false;
				else
					out.print("+");
				out.printf("%d",unmap[i]);
			}
		}
		out.println();
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		while(true)
		{
			st = new StringTokenizer(br.readLine());
			T = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			if(T==0 && n==0)
				break;
			list = new int[n];
			unmap = new int[n];
			TreeMap<Integer,Integer> map = new TreeMap<Integer,Integer>();
			N = 0;
			int[] tmp = new int[n];
			for(int i = 0; i < n; i++)
				tmp[i] = Integer.parseInt(st.nextToken());
			Arrays.sort(tmp);
			for(int i = 0; i < n; i++)
			{
				int cur = tmp[i];
				if(!map.containsKey(cur))
				{
					unmap[N] = cur;
					map.put(cur, N++);
				}
				list[map.get(cur)]++;
				
			}
			
			sel = new int[N];
			counter = 0;
			out.printf("Sums of %d:\n",T);
			backtrack(N-1);
			if(counter==0)
				out.println("NONE");
		}
		
		out.flush();
	}
}
