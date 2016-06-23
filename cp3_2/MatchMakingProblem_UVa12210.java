package cp3_2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class MatchMakingProblem_UVa12210 {
		

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int k = 0;
		while(true)
		{
			k++;
			StringTokenizer st = new StringTokenizer(br.readLine());
			int b = Integer.parseInt(st.nextToken());
			if(b==0)
				break;
			int s = Integer.parseInt(st.nextToken());
			
			int[] bachelors = new int[b];
			LinkedList<Integer> spinsters = new LinkedList<Integer>(); 
			for(int i = 0; i < b; i++)
				bachelors[i] = Integer.parseInt(br.readLine());
			for(int i = 0; i < s; i++)
				spinsters.add(Integer.parseInt(br.readLine()));
			
			Arrays.sort(bachelors);
			Collections.sort(spinsters);
			
			int leftBachelors = b;
			int leftSpinsters = s;
			
			while(leftBachelors>0)
			{
				if(leftSpinsters==0)
					break;
				int indexS = Collections.binarySearch(spinsters, bachelors[leftBachelors-1]);
				if(indexS<0)
				{
					int greaterS = -(indexS + 1);
					int smallerS = greaterS - 1;
					if(greaterS==0)
						indexS = greaterS;
					else
						if(greaterS==spinsters.size())
							indexS = smallerS;
						else
							if(bachelors[leftBachelors-1] - spinsters.get(smallerS) > spinsters.get(greaterS) - bachelors[leftBachelors-1])
								indexS = greaterS;
							else
								indexS = smallerS;
				}
				
				spinsters.remove(indexS);
				leftBachelors--;
				leftSpinsters--;
			}
			
			sb.append("Case "+k+": ");
			if(leftBachelors==0)
				sb.append("0\n");
			else
				sb.append(leftBachelors+" "+bachelors[0]+"\n");
			
		}
		System.out.print(sb);
	}
	
	
	

}
