import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 
public class Fibonacci {
 
    //递归算法：不断调用自身实现深度计算
    public static Long recursiveCompute(int n) {
        if (n <= 2)
            return 1L;
        else
            return recursiveCompute(n - 1) + recursiveCompute(n - 2);
    }
 
    // 非递归cache算法：用一个List缓存所有从1~n的Fibonacci值
    public static Long arraylist_cache(int n) {
        if (n <= 2)        return 1L;
        List<Long> results = new ArrayList<Long>();
        results.add(1L);
        results.add(1L);
        int length = -1;
        while ((length = results.size()) < n)
            results.add(results.get(length - 2) + results.get(length - 1));
        return results.get(n - 1);
    }
 
    // 非递归swap算法：只用两个变量l1、l2来保存Fibonacci值，不断update和swap
    public static Long swapCompute(int n) {
        if (n <= 2){
            return 1L;
        }
        long l1 = 1;
        long l2 = 1;
        int index = 2;
        long l;
        while (index < n - 1) {
            l = l2;
            l2 = l1 + l2;
            l1 = l;
            index++;
        }
        return l1 + l2;
    }
    
	public static long array_cache(int index){     //数组实现
		if(index<3)	  return 1L;
		long []a=new long[index];
		a[0]=a[1]=1L;
		for(int i=2;i<index;i++)				a[i]=a[i-1]+a[i-2]; 
		return a[index-1];
	}
    
    //公式法（利用公式F(n) = [@n/sqrt(5)]快速计算第n个斐波那契数）
    public static long formula(int n){
        double temp = Math.sqrt(5.0);
        return (long) ( 	(1/temp)*(Math.pow((1+temp)/2,n)-Math.pow((1-temp)/2, n))		);
    }
	
	 //新算法法
    public static long new_way(int n){
        //int a = 1,b = 1,c = 2,d = 3;
        long result = 0;   //定义最后一个斐波那契数
        //根据输入n,求出最后一个斐波那契数
        if(n == 0)
            result = 0;
        else if(n == 1 || n == 2)
            result =  1;
        else if(n == 3)
            result =  2;
        else if(n >= 4){    //若n大于4返回resul
            int a1 = n/4;
            int b1 = n%4;
            long a = new_way(a1);
            long b = new_way((a1+1));
            long c = new_way((a1-1));
            long d = new_way((a1+2));
            if(b1 == 0)
                result = (long) ((Math.pow(b,2) - Math.pow(c,2))*(Math.pow(c, 2) + 2*Math.pow(a, 2) + Math.pow(b,2)));
            if(b1 == 1)
                result = (long) (Math.pow((Math.pow(b,2) - Math.pow(c,2)),2) + Math.pow((Math.pow(a, 2) + Math.pow(b,2)),2));
            if(b1 == 2)
                result = (long) ((Math.pow(a, 2) + Math.pow(b,2))*(3*Math.pow(b,2)+Math.pow(a, 2)-2*Math.pow(c,2)));
            if(b1 == 3)
                result = (long) (Math.pow((Math.pow(a, 2) + Math.pow(b,2)),2) + Math.pow((Math.pow(d,2)-Math.pow(a,2)),2));
        }
            return result;
    }
    
    // 关联矩阵  
    private static final long[][] UNIT = { { 1, 1 }, { 1, 0 } };  
    // 全0矩阵  
    private static final long[][] ZERO = { { 0, 0 }, { 0, 0 } };  
    //  
    public static long[][] fb(int n) {  
        if (n == 0) {              return ZERO;          }  
        if (n == 1) {              return UNIT;          }  
        // n是奇数  
        if ((n & 1) == 0) {  
        	long[][] matrix = fb(n >> 1);  
            return matrixMultiply(matrix, matrix);  
        }  
        // n是偶数  
        long[][] matrix = fb((n - 1) >> 1);  
        return matrixMultiply(matrixMultiply(matrix, matrix), UNIT);  
    }  
      
    //矩阵相乘 
    public static long[][] matrixMultiply(long[][] m, long[][] n) {
        int rows = m.length;  
        int cols = n[0].length;  
        long[][] r = new long[rows][cols];  
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                r[i][j] = 0;  
                for (int k = 0; k < m[i].length; k++) {  
                    r[i][j] += m[i][k] * n[k][j];  
                }
            }
        }
        return r;  
    }
    //具体实现矩阵相乘算法
    public static long matrix(int n){
        long[][] m = fb(n); 
        return m[0][1];
    }

    
    /************************************************************************/
    /*  下面矩阵的n次幂，结果保存在      Result[2][2]                   */
    //   矩阵的n次幂，采用分治法，与x的n 次方算法一致，时间复杂度T(n)=o(logn)
//        1    1
//        1    0
    /************************************************************************/
    public static long Matrix_power(int n, long Result[][])//求矩阵幂
//    public static long Matrix_power(int n)//求矩阵幂
    {
          long AResult[][] = new long[2][2];
          long zResult1[][]= new long[2][2];
         long zResult2[][]= new long[2][2];
        AResult[0][0]=1;AResult[0][1]=1;AResult[1][0]=1;AResult[1][1]=0;
        if (1==n)
        {
            Result[0][0]=1;Result[0][1]=1;Result[1][0]=1;Result[1][1]=0;
        }
        else if (n%2==0)
        {
            Matrix_power(n/2,zResult1);
            MUL(zResult1,zResult1,Result);
        }
        else if (n%2 == 1)
        {
            Matrix_power((n-1)/2,zResult1);
            MUL(zResult1,zResult1,zResult2);
            MUL(zResult2,AResult,Result);
        }
        return Result[0][1];
    }
    
    /************************************************************************/
    /* 两个 矩阵相乘  、结果矩阵保存在 MatrixResult[2][2]中       */
    /************************************************************************/
    public static void MUL(  long MatrixA[][],  long MatrixB[][],  long MatrixResult[][] )//矩阵相乘
    {
        for (int i=0;i<2 ;i++)
        {
            for (int j=0;j<2 ;j++)
            {
                MatrixResult[i][j]=0;
                for (int k=0;k<2 ;k++)
                {
                    MatrixResult[i][j]=MatrixResult[i][j]+MatrixA[i][k]*MatrixB[k][j];
                }
            }
        }
    }
 
    public static void main(String[] args) throws IOException {   	
		int n = 10;
		long start, end;
        long time =0;
        long result = 0;
        int i ;
	    long result1[][]=new long[2][2];
        for(i=0;i<10;i++){
    		start = System.nanoTime();
            result = formula2(n);
//            result = Matrix_power(n,result1);
            end = System.nanoTime();
            time += (end - start);
            System.out.println( "开始 : "+start+"\t结束："+end+"\t耗时："+(end -start) );  
        }
	    System.out.println("array_cache结果：\t "+result + "   array_cache耗时 : \t"+time/10 );  
	    
//        for(i=1;i<11;i++){
//    		start = System.nanoTime();
//            result = formula2(n);
//            end = System.nanoTime();
//            time += (end - start);
//        }
//	    System.out.println("formula2结果：\t\t "+result+ "   formula2耗时 : \t\t "+time/10 );  
    }
    public static long formula2(int n){
        double c = Math.sqrt(5.0);
        double temp1=(1+c)/2;
        double temp2=(1-c)/2;
//        return (long) ( 	(1/c)*(Math.pow((1+c)/2,n)-Math.pow((1-c)/2, n))		);
        if((n&1) ==0){
        	for(int i =1;i<n/2;i++){
        		temp1 *= (1+c)/2;
        		temp2 *= (1-c)/2;
        	}
        	temp1 *= temp1;
        	temp2 *= temp2;
        }else{
        	for(int i =1;i<(n-1)/2;i++){
        		temp1 *= (1+c)/2;
        		temp2 *= (1-c)/2;
        	}
        	temp1 *= temp1*(1+c)/2;
        	temp2 *= temp2*(1-c)/2;
        }
        return (long) ((1/c)*(temp1-temp2));
    }
    
}