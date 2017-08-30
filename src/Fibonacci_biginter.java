import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
 
public class Fibonacci_biginter {
 
    //�ݹ��㷨�����ϵ�������ʵ����ȼ���
    public static BigInteger recursiveCompute(int n) {
        if (n <= 2)
            return BigInteger.ONE;
        else
            return recursiveCompute(n - 1).add(  recursiveCompute(n - 2) );
    }
 
    // �ǵݹ�cache�㷨����һ��List�������д�1~n��Fibonacciֵ
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
 
    // �ǵݹ�swap�㷨��ֻ����������l1��l2������Fibonacciֵ������update��swap
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
    
	public static BigInteger array_cache(int index){     //����ʵ��
		if(index<3)	  return BigInteger.ONE;
		BigInteger []a=new BigInteger[index];
		a[0]=a[1]=BigInteger.ONE;
		for(int i=2;i<index;i++)				a[i]=a[i-1].add(a[i-2]); 
		return a[index-1];
	}
    
    //��ʽ�������ù�ʽF(n) = [@n/sqrt(5)]���ټ����n��쳲���������
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
    
    
    // ��������  
    private static final BigInteger[][] UNIT = { { BigInteger.ONE, BigInteger.ONE }, { BigInteger.ONE, BigInteger.ZERO } };  
    // ȫ0����  
    private static final BigInteger[][] ZERO = { { BigInteger.ZERO, BigInteger.ZERO }, { BigInteger.ZERO, BigInteger.ZERO } };  
    //  
    public static BigInteger[][] fb(int n) {  
        if (n == 0) {              return ZERO;          }  
        if (n == 1) {              return UNIT;          }  
        // n������  
        if ((n & 1) == 0) {  
        	BigInteger[][] matrix = fb(n >> 1);  
            return matrixMultiply(matrix, matrix);  
        }  
        // n��ż��  
        BigInteger[][] matrix = fb((n - 1) >> 1);  
        return matrixMultiply(matrixMultiply(matrix, matrix), UNIT);  
    }  
      
    //������� 
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
    //����ʵ�־�������㷨
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
            System.out.println( "��ʼ : "+start+"\t������"+end+"\t��ʱ��"+(end -start) );  
        }
	    System.out.println("array_cache�����\t "+result + "   array_cache��ʱ : \t"+time/10 );  
	    
//        for(i=1;i<11;i++){
//    		start = System.nanoTime();
//            result = formula2(n);
//            end = System.nanoTime();
//            time += (end - start);
//        }
//	    System.out.println("formula2�����\t\t "+result+ "   formula2��ʱ : \t\t "+time/10 );  
    }
}