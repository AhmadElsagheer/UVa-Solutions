package cp2_2;

import java.util.*;
import java.io.*;

public class AutomateTheGrades_UVa11777 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		char[] grade = new char[101];
		for(int i = 0; i < 60; i++)
			grade[i] = 'F';
		for(int i = 60; i < 70; i++)
			grade[i] = 'D';
		for(int i = 70; i < 80; i++)
			grade[i] = 'C';
		for(int i = 80; i < 90; i++)
			grade[i] = 'B';
		for(int i = 90; i < 101; i++)
			grade[i] = 'A';
		
		for(int i = 1; i <= tc; i++)
		{
			int f = sc.nextInt();
			int t1 = sc.nextInt(); int t2 = sc.nextInt();
			int att = sc.nextInt();
			int[] tests = new int[]{sc.nextInt(),sc.nextInt(),sc.nextInt()};
			int max1 = 0, max2 = 0;
			if(tests[0]>tests[1])
			{
				max1 = tests[0];
				if(tests[1]>tests[2])
					max2 = tests[1];
				else
					max2 = tests[2];
			}
			else
			{
				max1 = tests[1];
				if(tests[0]>tests[2])
					max2 = tests[0];
				else
					max2 = tests[2];
			}
			max1 = (max1+max2)/2;
			int sum = f + t1 + t2 + att + max1;
			out.printf("Case %d: %c\n",i,grade[sum]);
		}
		out.flush();
	}
}
