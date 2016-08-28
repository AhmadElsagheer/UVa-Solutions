package v104;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CowsAndCars_UVa10491 {

    public static void main(String[] args) throws IOException {
    
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        while(sc.ready())
        {
        	double cows = sc.nextInt(), cars = sc.nextInt(), tot = cows + cars, shows = sc.nextInt();
            out.printf("%.5f\n", cars*(cars-1) / (tot*(tot-shows-1)) + cows*cars / (tot*(tot-shows-1)));
        }
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