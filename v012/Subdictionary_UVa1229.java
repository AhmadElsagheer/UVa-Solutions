package v012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Subdictionary_UVa1229 {

	static boolean[][] adjMat;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			int N = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			if(N==0)
				break;
			String[][] dic = new String[N][];
			for(int i = 0; i < N; i++)
				dic[i] = br.readLine().split(" ");
			
			TreeMap<String, Integer> map = new TreeMap<String, Integer>();
			adjMat = new boolean[N][N];
			
			for(int i = 0; i < N; i++)
				adjMat[i][i] = true;
			
			for(int i = 0; i < N; i++)
				map.put(dic[i][0], i);
			for(int i = 0; i < N; i++)
				for(int j = 1; j < dic[i].length; j++)
				{
					int u = map.get(dic[i][j]);
					adjMat[u][i] = true;
				}
			
			for(int k = 0; k < N; k++)
				for(int i = 0; i < N; i++)
					for(int j = 0; j < N; j++)
						adjMat[i][j] = adjMat[i][j] || adjMat[i][k] && adjMat[k][j];
			
			int SCC = -1;
			boolean[] added = new boolean[N];
			TreeMap<Integer, Integer> num = new TreeMap<Integer, Integer>();
			ArrayList<ArrayList<Integer>> members = new ArrayList<ArrayList<Integer>>();
			ArrayList<String> sol = new ArrayList<String>();
			for(int i = 0; i < N; i++)
				if(!num.containsKey(i))
				{

					num.put(i, ++SCC);
					members.add(new ArrayList<Integer>());
					members.get(SCC).add(i);
					for(int j = i + 1; j < N; j++)
						if(adjMat[i][j] && adjMat[j][i])
						{
							num.put(j, SCC);
							members.get(SCC).add(j);
						}
					if(members.get(SCC).size() > 1)
						for(int j = 0, size = members.get(SCC).size(); j < size; j++)
						{
							int u = members.get(SCC).get(j);
							if(!added[u])
							{
								sol.add(dic[u][0]);
								added[u] = true;
							}
							for(int k = 0; k < N; k++)
								if(adjMat[k][u] && !added[k])
								{
									sol.add(dic[k][0]);
									added[k] = true;
								}
						}
				}
			
			Collections.sort(sol);
			sb.append(sol.size()+"\n");
			for(int i = 0; i < sol.size(); i++)
				sb.append(sol.get(i)).append(i==sol.size()-1?"\n":" ");
		}
		System.out.print(sb);
	}
}
