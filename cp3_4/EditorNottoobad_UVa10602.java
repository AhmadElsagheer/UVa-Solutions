package cp3_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EditorNottoobad_UVa10602 {

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			int N = Integer.parseInt(br.readLine());
			String last = br.readLine();
			int X = last.length();
			String[] words = new String[N-1];
			for(int i = 0; i < N - 1; i++)
				words[i] = br.readLine();
			StringBuilder x = new StringBuilder();
			while(true)
			{
				x.append(last).append("\n");
				int max = -1;int cur = -1;
				for(int i = 0; i < N -1; i++)
					if(words[i]!=null)
					{
						int match = match(last,words[i]);
						if(match>max)
						{
							max = match;
							cur =i;
						}
					}
				if(cur==-1)
					break;
				last =  words[cur];
				X += words[cur].length() - max;
				words[cur] = null;
					
			}
			sb.append(X+"\n").append(x);
			
		}
		System.out.print(sb);
	}
	
	static int match(String x, String y)
	{
		int i = 0;
		for(; i < x.length() && i < y.length(); i++)
			if(x.charAt(i)!=y.charAt(i))
				return i;
		return i;
	}
}
