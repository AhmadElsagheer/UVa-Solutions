package cp4_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WeddingOfSultan_UVa12582 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		boolean[][] adjMatrix;
		int[] parent;
		for(int k = 1; k <= TC; k++)
		{
			sb.append("Case ").append(k).append("\n");
			adjMatrix = new boolean[26][26];
			parent = new int[26];
			String traverse  = br.readLine();
			int u = traverse.charAt(0) -'A';
			for(int i = 1, length = traverse.length() - 1; i < length; i++)
			{
				int v = traverse.charAt(i) - 'A';
				if(v==u) u = parent[u];
				else
				{
					adjMatrix[u][v] = adjMatrix[v][u] = true;
					parent[v] = u;
					u = v;
				}
			}
			for(int i = 0; i < 26; i++)
			{
				int count = 0;
				for(int j = 0; j < 26; j++)
					if(adjMatrix[i][j])
						count++;
				if(count!=0)
					sb.append((char)(i + 'A')).append(" = ").append(count).append("\n");
			}
			
		}
		System.out.print(sb);
	}
}
