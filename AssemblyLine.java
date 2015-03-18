public class AssemblyLine
{
    
    private int ns;
    private int[][] f;
    private int fast_way;
    private int[][] l;
    private int line_No;

    
    public AssemblyLine(int[][] a, int[][] t,int[] e, int[] x, int ns)
    {
    	this.ns = ns;

    	f = new int[2][ns];
    	l = new int[2][ns];

    	fastestPath(a, t, e, x, ns);
    }

    
    private void fastestPath(int [][] a, int[][] t, int[] e, int[] x, int n)
    {
    	f[0][0] = e[0] + a[0][0];
    	f[1][0] = e[1] + a[1][0];
	
    	for (int j = 1; j < n; j++) {
    		if (f[0][j-1] + a[0][j] <= f[1][j-1] + t[1][j-1] + a[0][j]) {
    			f[0][j] = f[0][j-1] + a[0][j];
    			l[0][j] = 0;
    		} 
    		else {
    			f[0][j] = f[1][j-1] + t[1][j-1] + a[0][j];
    			l[0][j] = 1;
    		}

		    if (f[1][j-1] + a[1][j] <= f[0][j-1] + t[0][j-1] + a[1][j]) 
		    {
		    	f[1][j] = f[1][j-1] + a[1][j];
		    	l[1][j] = 1;
		    } 
		    else 
		    {
		    	f[1][j] = f[0][j-1] + t[0][j-1] + a[1][j];
		    	l[1][j] = 0;
		    }
		}
		
		if (f[0][n-1] + x[0] <= f[1][n-1] + x[1]) 
		{
		    fast_way = f[0][n-1] + x[0];
		    line_No = 0;
		} 
		else 
		{
			fast_way = f[1][n-1] + x[1];
		    line_No = 1;
		}
		
		
	    }

    public int[] FastestRoute()
    {
		int[] ret = new int[ns];	
		int i = line_No;
		
		ret[ns-1] = i;
	
		for (int j = ns-1; j >= 1; j--) {
		    i = l[i][j];
		    ret[j-1] = i;
		}
	
		return ret;
    }

  
    public String toString()
    {
	    System.out.println("Optimal Time is: "+fast_way);
		System.out.println("Optimal line is: "+line_No);
		int[] route = FastestRoute();
		String path = "";

		for (int i = 0; i < route.length; i++)
		{
		    path += "Line " + (route[i] + 1) + ", Station " + (i + 1) + "\n";
		}
		return path;
	 }
    
    public static void main(String args[]) 
    { 
    	int [][] a={{7,9,3,4,8,4},
    	               {8,5,6,4,5,7}};
    	int [][] t={{2,3,1,3,4},
    			{2,1,2,2,1}};
    	int [] e = {2,4};
    	int [] x = {3,3};
    	int n = 6;
    	AssemblyLine tester = new AssemblyLine(a,t,e,x,n);
    	String k = tester.toString();
    	System.out.println(" ");
    	System.out.println("Optimal Path:");
    	System.out.println(k);
    	
    }
}

