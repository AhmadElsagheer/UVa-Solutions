package cp5_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class RationalSpiral_UVa493 {

	static int[][] rationals;		//0-num : 1=den
	
	public static int gcd(int a, int b){
		return b == 0? a: gcd(b,a%b);
	}
	public static void generateRationals(int N)
	{
		rationals =  new int[N+10000][2];
		int incValue = 1;int num = 0, den = 0;
		boolean increasing = true;
		for(int i  = 0; i <= N; i++)
		{
			if(increasing)
			{
				if(num<incValue)
					num++;
				else
				{
					den++;
					if(den==num)
						increasing = false;
				}
			}
			else
			{
				if(num>-incValue)
					num--;
				else
				{
					den--;
					if(den == num)
					{
						increasing = true;incValue++;
					}
				}
			}
//			System.out.println(num+" "+den+" "+increasing);
			
			if(den==0 || den <= 0 && num <= 0||  gcd(Math.abs(den),Math.abs(num)) != 1 || num <= -den && den < 0 && num > 0 || den < -num && num < 0 && den > 0)
				i--;
			else
			{
				
				rationals[i][0] = num; rationals[i][1] = den;
				if(den<0)
				{
					rationals[i][0] *= -1;rationals[i][1]*=-1;
				}
				
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		LinkedList<Integer> numbers = new LinkedList<Integer>();
		int max = 0;
		while(br.ready())
		{
			int current = (int) Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			if(current> max)
				max = current;
			numbers.add(current);
		}
		generateRationals(max);
		while(!numbers.isEmpty())
		{
			int current = numbers.remove();
			int num = rationals[current][0];
			int den = rationals[current][1];
			sb.append(num+" / "+den+"\n");
		}
		System.out.print(sb);
		
	}
}
