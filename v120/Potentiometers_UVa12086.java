package v120;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Potentiometers_UVa12086 {

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		int k = 1;
		while(true)
		{
			int N = Integer.parseInt(br.readLine());
			if(N==0)
				break;
			if(k!=1)
				sb.append("\n");
			sb.append("Case "+k+++":\n");
			FenwickTree f = new FenwickTree(N);
			int[] x = new int[N+1];
			for(int i = 1; i <= N; i++)
			{
				x[i] = Integer.parseInt(br.readLine());
				f.update_point(i, x[i]);
			}
			boolean stop = false;
			while(!stop)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				char c = st.nextToken().charAt(0);
			
				switch(c)
				{
				case 'S':
					int idx = Integer.parseInt(st.nextToken());
					int val = Integer.parseInt(st.nextToken());
					f.update_point(idx, val - x[idx]);
					x[idx] = val;break;
				case 'M':
					sb.append(f.rsq(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()))).append("\n");break;
					default:stop = true;
				}
				
			}
		}
		out.print(sb);
		out.flush();
	}
}



class FenwickTree {

	int[] ft;
	
	int LSOne(int S) { return (S & (-S)); }
	
	FenwickTree(int n)
	{
		ft = new int[n+1];
	}
	
	int rsq(int b)
	{
		int sum = 0;
		while(b>0) { sum += ft[b]; b-=LSOne(b);}
		return sum;
	}
	
	int rsq(int a, int b)
	{
		return rsq(b) - rsq(a-1);
	}
	
	void update_point(int k, int val)
	{
		while(k < ft.length)
		{
			ft[k] += val;
			k += LSOne(k);
		}
	}
}
