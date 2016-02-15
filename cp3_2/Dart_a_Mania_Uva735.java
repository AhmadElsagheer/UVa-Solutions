package cp3_2;

	
import java.io.*; 
import java.util.*;
public class Dart_a_Mania_Uva735{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		ArrayList<Integer> scores = new ArrayList<Integer>();
		for(int i = 0; i <= 20; i++)
			scores.add(i);
		for(int i = 22; i <= 42; i+=2)
			scores.add(i);
		for(int i = 45; i <= 60; i+=3)
			scores.add(i);
		scores.add(21);scores.add(27);scores.add(33);scores.add(39);scores.add(50);
		Collections.sort(scores);
		
		String asterisks = "";
		for(int i = 0; i < 7; i++)
			asterisks += "**********";
		
		while(true)
		{
			int score = Integer.parseInt(br.readLine());
			if(score<=0)
				break;
			int combinations = 0; int permutations = 0;
			for(int a = 0; a < scores.size(); a++)
				for(int b = 0; b < scores.size(); b++)
					for(int c = 0; c < scores.size(); c++)
						if(scores.get(a)+scores.get(b)+scores.get(c)==score)
						{
							permutations++;
							if(b>=a && c>=b)
								combinations++;
						}
			if(permutations==0)
				sb.append("THE SCORE OF "+score+" CANNOT BE MADE WITH THREE DARTS.\n");
			else
			{
				sb.append("NUMBER OF COMBINATIONS THAT SCORES "+score+" IS "+combinations+".\n");
				sb.append("NUMBER OF PERMUTATIONS THAT SCORES "+score+" IS "+permutations+".\n");
			}
			sb.append(asterisks+"\n");
			
		}
	
		sb.append("END OF OUTPUT\n");
		System.out.print(sb);

		
		
	}
	
	
	
	
	

	
}	
