package v101;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class CDVII_UVa10138 {
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int[] fare = new int[24];
			for(int i = 0; i < 24; ++i)
				fare[i] = sc.nextInt();
			
			TreeMap<String, ArrayList<Record>> map = new TreeMap<String, ArrayList<Record>>();
			while(sc.ready() && !sc.nextEmpty())
			{
				String license = sc.next();
				Record record = new Record(sc.next(), sc.next(), sc.nextInt());
				ArrayList<Record> records = map.get(license);
				if(records == null)
					map.put(license, records = new ArrayList<Record>());
				records.add(record);
			}
			for(Entry<String, ArrayList<Record>> e: map.entrySet())
			{
				String license = e.getKey();
				ArrayList<Record> records = e.getValue();
				Collections.sort(records);
				int bill = 200;
				for(int i = 0; i < records.size() - 1; ++i)
				{
					Record r1 = records.get(i), r2 = records.get(i + 1);
					if(r1.enter && !r2.enter)
						bill += 100 + Math.abs(r1.loc - r2.loc) * fare[r1.hour];
				}
				if(bill > 200)
					out.printf("%s $%.2f\n", license, bill / 100.0);
			}
			if(tc != 0)
				out.println();
		}
		out.flush();
		out.close();
	}
	
	static class Record implements Comparable<Record>
	{
		int hour, loc;
		boolean enter;
		String time;
		
		Record(String s, String t, int l)
		{
			time = s;
			hour = Integer.parseInt(s.substring(6, 8));
			if(t.equals("enter"))
				enter = true;
			loc = l;
		}
		
		public int compareTo(Record r)
		{
			return time.compareTo(r.time);
		}
	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public double nextDouble() throws IOException
		{
			String x = next();
			StringBuilder sb = new StringBuilder("0");
			double res = 0, f = 1;
			boolean dec = false, neg = false;
			int start = 0;
			if(x.charAt(0) == '-')
			{
				neg = true;
				start++;
			}
			for(int i = start; i < x.length(); i++)
				if(x.charAt(i) == '.')
				{
					res = Long.parseLong(sb.toString());
					sb = new StringBuilder("0");
					dec = true;
				}
				else
				{
					sb.append(x.charAt(i));
					if(dec)
						f *= 10;
				}
			res += Long.parseLong(sb.toString()) / f;
			return res * (neg?-1:1);
		}

		public boolean ready() throws IOException {return br.ready();}
		
		public boolean nextEmpty() throws IOException
		{
			String s = br.readLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}


	}
}