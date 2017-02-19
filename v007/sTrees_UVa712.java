package v007;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class sTrees_UVa712 {

	static int[] terminal;
	static int N;
	static int[] x;
	
	static int dfs(int node, int depth)
	{

		if(depth==N)
			return terminal[node];
		if(x[depth]==0)
			return dfs(node<<1, depth+1);
		return dfs((node<<1)+1, depth+1);
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int k = 1;
		while(true)
		{
			N = Integer.parseInt(br.readLine());
			if(N==0)
				break;
			x = new int[N];
			int[] map = new int[N];
			terminal = new int[(1<<N+1)];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++)
				map[st.nextToken().charAt(1) - '0' - 1] = i;
			String line = br.readLine();
			for(int i = 1<<N, j = 0; i < (1<<N+1); i++, j++)
				terminal[i] = line.charAt(j) - '0';
			
			int Q = Integer.parseInt(br.readLine());
			sb.append("S-Tree #"+k+++":\n");
			while(Q-->0)
			{
				line = br.readLine();
				for(int i = 0; i < N; i++)
					x[map[i]] = line.charAt(i) - '0';

				sb.append(dfs(1, 0));
			}
			sb.append("\n\n");
		}
		System.out.print(sb);
	}
}
