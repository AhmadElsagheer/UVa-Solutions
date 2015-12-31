package cp3_2;
import java.util.*;
import java.io.*;

public class PrimeRingProblem_UVa524 {

	static 	PrintWriter out = new PrintWriter(System.out);
	static int N;
	static boolean[] used;
	static ArrayList<Integer>[] next;
	static int[] array;
	static boolean[] isPrime;
	public static void backtrack(int k)
	{
		if(k==N+1)
		{
			if(isPrime[array[N]+array[1]])
				print();
		}
		else
		{
			int pre = array[k-1];
			for(int i = 0, size = next[pre].size(); i < size; i++)
			{
				int cur = next[pre].get(i);
				if(!used[cur])
				{
					used[cur] = true;
					array[k] = cur;
					backtrack(k+1);
					used[cur] = false;
				}
			}
		}
	}
	
	
	

	public static void print()
	{	
		for(int i = 1; i < N; i++)
			out.printf("%d ",array[i]);
		out.println(array[N]);
	}
	
	public static void sieve(int N)
	{
		isPrime = new boolean[N+1];
		Arrays.fill(isPrime, true);
		isPrime[1] = isPrime[1] = false;
		for(int i = 2; i*i <= N; i++)
			if(isPrime[i])
				for(int j = i * i; j <= N; j+=i)
					isPrime[j] = false;
	}
	
	public static void main(String[] args) throws IOException
	{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sieve(40);
		
		int k  = 1;
		while(br.ready())
		{
			if(k!=1)
				out.println();
			out.printf("Case %d:\n",k++);
			N = Integer.parseInt(br.readLine());
			next = new ArrayList[N+1];
			for(int i = 1; i <= N; i++)
			{
				next[i] = new ArrayList<Integer>(8);
				for(int j = i%2 == 0 ? 3 : 2; j <= N; j+=2)
					if(isPrime[i+j])
						next[i].add(j);
			}
			array = new int[N+1];
			used = new boolean[N+1];
			used[1] = true;
			array[1] = 1;
			backtrack(2);
		}
		out.flush();
		out.close();
	}
}
