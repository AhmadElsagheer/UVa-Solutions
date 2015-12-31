package cp1_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TransactionProcessing_UVa187 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] names = new String[1000];
		while(true)
		{
			String line = br.readLine();
			int account = Integer.parseInt(line.substring(0,3));
			if(account == 0)
				break;
			names[account] = line.substring(3);
		}
		
		ArrayList<ArrayList<Pair>> t = new ArrayList<ArrayList<Pair>>();
		ArrayList<Integer> ss = new ArrayList<Integer>();
		int last = -1;
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			String sssnnn = st.nextToken();
			int s = Integer.parseInt(sssnnn.substring(0,3));
			int n = Integer.parseInt(sssnnn.substring(3));
			if(s==0)
				break;
			if(s!=last)
			{
				last = s;
				ss.add(s);
				t.add(new ArrayList<Pair>());
			}
			t.get(t.size()-1).add(new Pair(n,Integer.parseInt(st.nextToken())));
		}
		for(int i = 0; i < t.size(); i++)
		{
			long total = 0;
			for(int j = 0; j < t.get(i).size(); j++)
				total += t.get(i).get(j).val;
			if(total==0)
				continue;
			out.printf("*** Transaction %3d is out of balance ***\n",ss.get(i));
			for(int j = 0; j < t.get(i).size(); j++)
			{
				out.printf("%3d %s",t.get(i).get(j).account,names[t.get(i).get(j).account]);
				int length = 41 - names[t.get(i).get(j).account].length();
				out.printf("%"+length+"s\n",new DecimalFormat("0.00").format(t.get(i).get(j).val/100.0));
			}
			out.printf("999 Out of Balance%27s\n\n",new DecimalFormat("0.00").format(Math.abs(total)/100.0));
		}
		
		out.flush();
	}
}

class Pair
{
	int account, val;
	Pair(int x, int y)
	{
		account = x;
		val = y;
	}
}
