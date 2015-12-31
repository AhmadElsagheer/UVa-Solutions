package cp8_4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class SemiHPrimes_UVa11105 {
	
	static int[] ans;
	static void compute(int n)
	{
		boolean[] h = new boolean[n + 1];
		ArrayList<Integer> hPrimes = new ArrayList<Integer>(n);
		Arrays.fill(h, true);
		for(int i = 1; (i<<2) + 1 <= n; i++)
			if(h[(i<<2) + 1])
			{
				hPrimes.add((i<<2) + 1);
				if((long) ((i<<2) + 1) * ((i<<2) + 1) <= n)
					for(int k = (i<<2) + 1, j = k * k; j <= n; j += k)
						h[j] = false;
			}
		
		boolean[] semi = new boolean[n + 1];
		for(int i = 0; i < hPrimes.size(); i++)
		{
			long x = hPrimes.get(i);
			for(int j = i; j < hPrimes.size() && hPrimes.get(j) * x <= n; j++)
				semi[(int)(hPrimes.get(j) * x)] = true;			
		}
		ans = new int[n + 1];
		for(int i = 1; i <= n; i++)
			ans[i] = ans[i - 1] + (semi[i]?1:0);
	}
	
    public static void main(String[] args) throws IOException 
    {
        compute(1000000);
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        while(true)
        {
        	int n = sc.nextInt();
        	if(n == 0)
        		break;
        	out.format("%d %d\n", n, ans[n]);
        }
        out.flush();
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