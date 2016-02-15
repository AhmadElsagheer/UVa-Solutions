package cp2_3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Genes_UVa939 {
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int N = sc.nextInt();
		TreeMap<String, Person> map = new TreeMap<String, Person>();
		while(N-->0)
		{
			String x = sc.next(), y = sc.next();
			Person p1 = map.get(x);
			if(p1 == null)
				map.put(x, p1 = new Person(x));
			int g = getGene(y);
			if(g != -1)
				p1.gene = g;
			else
			{
				Person p2 = map.get(y);
				if(p2 == null)
					map.put(y, p2 = new Person(y));
				if(p2.p1 == null)
					p2.p1 = p1;
				else
					p2.p2 = p1;
			}
		}
		Person[] people = new Person[map.size()];
		int nxt = 0;
		for(Entry<String, Person> entry :map.entrySet())
			people[nxt++] = entry.getValue();
		for(int i = 0; i < people.length; i++)
			putGene(people[i]);
		Arrays.sort(people);
		for(int i = 0; i < people.length; i++)
			out.format("%s %s\n", people[i].name, findGene(people[i].gene));
		out.flush();
	}
	
	static void putGene(Person p)
	{
		if(p.gene != -1)
			return;
		putGene(p.p1);
		putGene(p.p2);
		if(p.p1.gene == 1 || p.p2.gene == 1 || p.p1.gene == 2 && p.p2.gene == 2)
			if((p.p1.gene == 1 || p.p2.gene == 1) && (p.p1.gene != 0 && p.p2.gene !=0))
				p.gene = 1;
			else
				p.gene = 2;
		else
			p.gene = 0;
		
	}
	static int getGene(String x)
	{
		if(x.equals("dominant"))
			return 1;
		else
			if(x.equals("recessive"))
				return 2;
			else
				if(x.equals("non-existent"))
					return 0;
		return -1;
	}
	
	static String findGene(int x)
	{
		switch(x)
		{
		case 0: return "non-existent";
		case 1:	return "dominant";
			default: return "recessive";
		}
	}
	
	static class Person implements Comparable<Person>
	{
		String name;
		int gene = -1;
		Person p1, p2;
		
		Person(String x) { name = x; }
		
		public int compareTo(Person p)
		{
			return name.compareTo(p.name);
		}
		
		public String toString()
		{
			return name + " " + gene;
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


	}
}
