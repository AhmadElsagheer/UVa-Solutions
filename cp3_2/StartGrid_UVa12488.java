package cp3_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class StartGrid_UVa12488 {

	static void shift(int[] a, int from, int to)
	{
		int tmp = a[from];
		for(int i = from; i > to; i--)
			a[i] = a[i-1];
		a[to] = tmp;
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(br.ready())
		{
		
			int n = Integer.parseInt(br.readLine());
			int[] a = new int[n];
			int[] b = new int[n];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++)
				a[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++)
				b[i] = Integer.parseInt(st.nextToken());
			int count = 0;
			for(int i = 0; i < n; i++)
				for(int j = i; j < n; j++)
					if(b[i]==a[j])
					{
						count += j - i;
						shift(a,j,i);
						break;
					}
			sb.append(count).append("\n");
		}
		
		System.out.print(sb);
	}
}
