package cp2_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class BallotEvaluation_UVa11629 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int p = Integer.parseInt(st.nextToken());
		int g = Integer.parseInt(st.nextToken());
		TreeMap<String, Integer> map = new TreeMap<String,Integer>();
		
		int score,count,target;
		boolean res;
		while(p-->0)
		{
			st = new StringTokenizer(br.readLine());
			map.put(st.nextToken(), mult(st.nextToken()));
		}
		for(int k = 1; k <= g; k++)
		{
			sb.append("Guess #").append(k);
			st = new StringTokenizer(br.readLine());
			count = st.countTokens() - 1;
			score = map.get(st.nextToken()); 
			while(count>2)
			{
				st.nextToken();
				score += map.get(st.nextToken());
				count -= 2;
			}
			String comp= st.nextToken();
			target = Integer.parseInt(st.nextToken()) * 10; 
			if(comp.equals("="))
				res = score == target;
			else
				if(comp.equals("<="))
					res = score <= target;
				else
					if(comp.equals(">="))
						res = score >= target;
					else
						if(comp.equals("<"))
							res = score < target;
						else
							res = score > target;
				
			if(res)
				sb.append(" was correct.\n");
			else
				sb.append(" was incorrect.\n");
	}
		out.print(sb);
		out.flush();
		out.close();
	}
   public static int mult(String x){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<x.length();i++){
            if(x.charAt(i)>='0' &&x.charAt(i)<='9'){
                sb.append(x.charAt(i));
            }
        }
        return Integer.parseInt(sb.toString());
    }
}
	
