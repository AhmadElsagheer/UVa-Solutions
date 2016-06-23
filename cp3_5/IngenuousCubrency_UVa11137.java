package cp3_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class IngenuousCubrency_UVa11137 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		long[][] memo = new long[21][10001];

		int[] value = new int[21];
		for(int i = 1; i <= 21; i++)
			value[i-1] = i * i * i;
		for(int i = 0; i < 21; i++)
			memo[i][0] = 1;
		for(int i = 1; i <= 10000; i++)
			memo[0][i] = 1;
		for(int i = 1; i <= 10000; i++)
			for(int j = 1; j < 21; j++)
				if(value[j]>i)
					memo[j][i] = memo[j-1][i];
				else
					memo[j][i] = memo[j][i-value[j]] + memo[j-1][i];
		
		while(br.ready())
		{
			int money = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			out.println(memo[20][money]);
		}
		out.flush();
		out.close();
	}
}
