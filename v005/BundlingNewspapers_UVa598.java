package v005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BundlingNewspapers_UVa598 {

	static ArrayList<String> newspapers;
	static StringBuilder res;
	
	static void backtrack(int size, int i, String cur){
		
		if(size==0)
		{
			res.append(cur);
			return;
		}
		backtrack(size-1,i+1,cur+newspapers.get(i)+(size>1?", ":"\n"));
		
		if(newspapers.size() - i - 1 >= size)
			backtrack(size,i+1,cur);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		br.readLine();
		while(TC-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			newspapers  = new ArrayList<String>(12);
			while(br.ready())
			{
				String line = br.readLine();
				if(line.equals(""))
					break;
				newspapers.add(line);
			}
			int begin,end;
			if(st.countTokens()==1)
			{
				String line = st.nextToken();
				if(line.equals("*"))
				{
					begin = 1; end = newspapers.size() + 1;
				}
				else
				{
					begin = Integer.parseInt(line); end = begin + 1;
				}
			}
			else
			{
				begin = Integer.parseInt(st.nextToken());
				end = Integer.parseInt(st.nextToken()) + 1; 
			}
			for(int i = begin; i < end; i++)
			{
				sb.append("Size "+i+"\n");
				res = new StringBuilder();	
				backtrack(i,0,"");
				
				sb.append(res).append("\n");
			}
			if(TC!=0)
				sb.append("\n");
		}
		System.out.print(sb);
	}
}
