package v005;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class France98_UVa542 {

	static double[][] P;
	
	static double[] calc(int l, int r)
	{
		if(l == r)
			return new double[] {1};
		int mid = l + r >> 1;
		double[] x = calc(l, mid), y = calc(mid + 1, r);
		double[] z = new double[r - l + 1];
		for(int i = l; i <= mid; ++i)
			for(int j = mid + 1; j <= r; ++j)
			{
				double p = x[i - l] * y[j - mid - 1];
				z[i - l] += P[i][j] * p;
				z[j - l] += P[j][i] * p;
			}
		return z;
	}
	
    public static void main(String[] args) throws IOException {
    
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        String[] names = new String[16];
        for(int i = 0; i < 16; ++i)
        	names[i] = sc.next();
        P = new double[16][16];
        for(int i = 0; i < 16; ++i)
        	for(int j = 0; j < 16; ++j)
        		P[i][j] = sc.nextInt() / 100.0;
        double[] ans = calc(0, 15);
        for(int i = 0; i < 16; ++i)
        	out.printf("%-10s p=%.2f%%\n", names[i], ans[i] * 100);
        out.flush();
        out.close();
    }
    

    static class Scanner 
    {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s){  br = new BufferedReader(new InputStreamReader(s));}

        public String next() throws IOException 
        {
            while (st == null || !st.hasMoreTokens()) 
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }
        
        public int nextInt() throws IOException {return Integer.parseInt(next());}

        public long nextLong() throws IOException {return Long.parseLong(next());}

        public String nextLine() throws IOException {return br.readLine();}

        public boolean ready() throws IOException {return br.ready(); }


    }
    
}