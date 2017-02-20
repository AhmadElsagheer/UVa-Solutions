package v001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class SpreadSheet_UVa196 {

	static Cell[][] sheet;
	static int R,C;
	
	static void compute(int i, int j)
	{
		Cell cur = sheet[i][j];
		int sum = 0;
		while(!cur.stack.isEmpty())
		{
			Pair e = cur.stack.pop();
			if(sheet[e.r][e.c].isFormula)
				compute(e.r,e.c);
			sum += sheet[e.r][e.c].val;
		}
		cur.val = sum;
		cur.isFormula = false;
	}
	static void compute()
	{
		for(int i = 1; i <= R; i++)
			for(int j = 1; j <= C; j++)
				if(sheet[i][j].isFormula)
					compute(i,j);
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			sheet = new Cell[R+1][C+1];
			for(int i = 1; i <= R; i++)
			{
				st = new StringTokenizer(br.readLine());
				for(int j = 1; j <= C; j++)
				{
					String cell = st.nextToken();
					if(cell.charAt(0)=='=')
						sheet[i][j] = new Cell(cell);
					else
						sheet[i][j] = new Cell(Integer.parseInt(cell));
				}
			}
			compute();
			
			for(int i = 1; i <= R; i++)
			{
				for(int j = 1; j < C; j++)
					sb.append(sheet[i][j].val+" ");
				sb.append(sheet[i][C].val+"\n");
			}
		}
		
		System.out.print(sb);
	}

	static class Cell
	{
		int val;
		boolean isFormula;
		Stack<Pair> stack;
		
		Cell(int v){val = v;}
		
		Cell(String exp)
		{
			
			isFormula = true;
			stack = new Stack<Pair>();
			exp = exp.substring(1);
			StringTokenizer st = new StringTokenizer(exp,"+");
			int count = st.countTokens();
			while(count-->0)
				stack.push(getCell(st.nextToken()));
			
		}
		
		static Pair getCell(String x)
		{
			String col = "";
			int i;
			for(i = 0; i < x.length(); i++)
			{
				if(x.charAt(i)>='0'&&x.charAt(i)<='9')
					break;
				col += x.charAt(i);
			}
			int factor = 1;
			int c = 0;
			for(int k = 0; k < i; k++)
			{
				c += factor * (col.charAt(i-k-1) - 'A' + 1);
				factor *= 26;
			}
			
			int r = Integer.parseInt(x.substring(i));
			return new Pair(r,c);
		}

	}

	static class Pair
	{
		int r,c;
		Pair(int i, int j){r = i; c = j;}
	}
}