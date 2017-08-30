import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
 
public class Fibonacci_biginter {
 
    //递归算法：不断调用自身实现深度计算
    public static BigInteger recursiveCompute(int n) {
        if (n <= 2)
            return BigInteger.ONE;
        else
            return recursiveCompute(n - 1).add(  recursiveCompute(n - 2) );
    }
 
    // 非递归cache算法：用一个List缓存所有从1~n的Fibonacci值
    public static BigInteger arraylist_cache(int n) {
        if (n <= 2)        return BigInteger.ONE;
        List<BigInteger> results = new ArrayList<BigInteger>();
        results.add(BigInteger.ONE);
        results.add(BigInteger.ONE);
        int length = -1;
        while ((length = results.size()) < n)
            results.add(results.get(length - 2).add(results.get(length - 1)  )  );
        return results.get(n - 1);
    }
 
    // 非递归swap算法：只用两个变量l1、l2来保存Fibonacci值，不断update和swap
    public static BigInteger swapCompute(int n) {
        if (n <= 2){
            return BigInteger.ONE;
        }
        BigInteger l1 = BigInteger.ONE;
        BigInteger l2 = BigInteger.ONE;
        int index = 2;
        BigInteger l;
        while (index < n - 1) {
            l = l2;
            l2 = l1.add(l2);
            l1 = l;
            index++;
        }
        return l1.add(l2);
    }
    
	public static BigInteger array_cache(int index){     //数组实现
		if(index<3)	  return BigInteger.ONE;
		BigInteger []a=new BigInteger[index];
		a[0]=a[1]=BigInteger.ONE;
		for(int i=2;i<index;i++)				a[i]=a[i-1].add(a[i-2]); 
		return a[index-1];
	}
    
    //公式法（利用公式F(n) = [@n/sqrt(5)]快速计算第n个斐波那契数）
    public static long formula(int n){
        double temp = Math.sqrt(5.0);
        return (long) ( 	(1/temp)*(Math.pow((1+temp)/2,n)-Math.pow((1-temp)/2, n))		);
    }
    public static BigInteger formula2(int n){
    	BigDecimal  temp = BigDecimal.valueOf(Math.sqrt(5.0) );
    	BigDecimal temp1 = BigDecimal.ONE.add(temp).divide( BigDecimal.valueOf(2.0) );
    	BigDecimal temp2 = BigDecimal.ONE.subtract(temp).divide( BigDecimal.valueOf(2.0) );
    	BigDecimal sub1 = temp1.pow(n);
    	BigDecimal sub2 = temp2.pow(n);
    	BigDecimal sub = sub1.subtract(sub2);
    	BigDecimal result1 = BigDecimal.ONE.divide(temp, 20,BigDecimal.ROUND_HALF_DOWN );
    	BigDecimal result = result1.multiply(sub);
    	return result.toBigInteger();
//    	return BigDecimal.ONE.divide(temp,BigDecimal.ROUND_HALF_DOWN).multiply(temp1.pow(n).subtract(temp2.pow(n))).toBigInteger() ;
    }
    
    
    // 关联矩阵  
    private static final BigInteger[][] UNIT = { { BigInteger.ONE, BigInteger.ONE }, { BigInteger.ONE, BigInteger.ZERO } };  
    // 全0矩阵  
    private static final BigInteger[][] ZERO = { { BigInteger.ZERO, BigInteger.ZERO }, { BigInteger.ZERO, BigInteger.ZERO } };  
    //  
    public static BigInteger[][] fb(int n) {  
        if (n == 0) {              return ZERO;          }  
        if (n == 1) {              return UNIT;          }  
        // n是奇数  
        if ((n & 1) == 0) {  
        	BigInteger[][] matrix = fb(n >> 1);  
            return matrixMultiply(matrix, matrix);  
        }  
        // n是偶数  
        BigInteger[][] matrix = fb((n - 1) >> 1);  
        return matrixMultiply(matrixMultiply(matrix, matrix), UNIT);  
    }  
      
    //矩阵相乘 
    public static BigInteger[][] matrixMultiply(BigInteger[][] m, BigInteger[][] n) {
        int rows = m.length;  
        int cols = n[0].length;  
        BigInteger[][] r = new BigInteger[rows][cols];  
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                r[i][j] = BigInteger.ZERO;  
                for (int k = 0; k < m[i].length; k++) {  
                    r[i][j] = r[i][j].add(  m[i][k].multiply(n[k][j]) );  
                }
            }
        }
        return r;  
    }
    //具体实现矩阵相乘算法
    public static BigInteger matrix(int n){
    	BigInteger[][] m = fb(n); 
        return m[0][1];
    }

 
    public static void main(String[] args) throws IOException {   	
		int n = 90;
		long start, end;
        long time =0;
        BigInteger result = BigInteger.ZERO;
        int i ;
        BigInteger result1[][]=new BigInteger[2][2];
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
}