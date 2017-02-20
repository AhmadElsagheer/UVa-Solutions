package v102;

import java.util.Scanner;

public class HanoiTowerTroublesAgain_UVa10276 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		for(int k = 1; k <= TC; k++)
		{
			int n = sc.nextInt();
			int[] pegs = new int[n];
			
			boolean added = true;
			int i;
			for(i = 1; added; i++)
			{
				added = false;
				for(int j = 0; j < n && !added; j++)				
					if(pegs[j]==0 || pegs[j]+i == ((int)(Math.sqrt(pegs[j]+i))*((int)Math.sqrt(pegs[j]+i))))
					{
						pegs[j] = i;
						added = true;
					}
				
			}
			System.out.println(i-2);
		}
			
	}
}
