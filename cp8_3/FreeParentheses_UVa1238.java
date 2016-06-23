package cp8_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FreeParentheses_UVa1238 {

	static final int OFFSET = 3000;
	static boolean[][][] memo;
	static int N, sign[], val[];
	static boolean[] visited;
	static int counter;
	
	public static void dp(int i, int nOpen, int sumSoFar)
	{
		if(i==N)
		{
			if(visited[sumSoFar+OFFSET])
				return;
			counter++;
			visited[sumSoFar+OFFSET] = true;
			return;
		}
		if(memo[i][nOpen][sumSoFar+OFFSET])
			return;
		
		int newSum = sumSoFar + val[i]*sign[i]*(nOpen%2==1?-1:1);
		
		if(sign[i]==-1)
			dp(i+1,nOpen+1,newSum);
		if(nOpen!=0)
			dp(i+1,nOpen-1,newSum);
		dp(i+1,nOpen,newSum);
		memo[i][nOpen][sumSoFar+OFFSET] = true;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		
		while(br.ready())
		{
			visited = new boolean[6000];
			val = new int[30];
			sign = new int[30];
			memo = new boolean[30][30][6000];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			val[0] = Integer.parseInt(st.nextToken());
			sign[0] = 1; N = 1;
			while(st.hasMoreTokens())
			{
				char c = st.nextToken().charAt(0);
				sign[N] = c=='+'?1:-1;
				val[N++] = Integer.parseInt(st.nextToken());
			}
			counter  = 0;
			dp(1,0,val[0]);
			
			sb.append(counter+"\n");			
		}
		System.out.print(sb);
	}
	
}
