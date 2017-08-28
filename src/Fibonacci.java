import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 
public class Fibonacci {
 
    //�ݹ��㷨�����ϵ�������ʵ����ȼ���
    public static Long recursiveCompute(int n) {
        if (n <= 2)
            return 1L;
        else
            return recursiveCompute(n - 1) + recursiveCompute(n - 2);
    }
 
    // �ǵݹ�cache�㷨����һ��List�������д�1~n��Fibonacciֵ
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
 
    // �ǵݹ�swap�㷨��ֻ����������l1��l2������Fibonacciֵ������update��swap
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
    
    
	public static long array_cache(int index){     //����ʵ��
		if(index<3)	  return 1L;
		long []a=new long[index];
		a[0]=a[1]=1L;
		for(int i=2;i<index;i++)				a[i]=a[i-1]+a[i-2];
		return a[index-1];
	}
    
    //��ʽ�������ù�ʽF(n) = [@n/sqrt(5)]���ټ����n��쳲���������
    public static long formula(int n){
        double temp = Math.sqrt(5.0);
        return (long) ( 	(1/temp)*(Math.pow((1+temp)/2,n)-Math.pow((1-temp)/2, n))		);
    }
	
	 //���㷨��
    public static long new_way(int n){
        //int a = 1,b = 1,c = 2,d = 3;
        long result = 0;   //�������һ��쳲�������
        //��������n,������һ��쳲�������
        if(n == 0)
            result = 0;
        else if(n == 1 || n == 2)
            result =  1;
        else if(n == 3)
            result =  2;
        else if(n >= 4){    //��n����4����resul
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
    
    // ��������  
    private static final long[][] UNIT = { { 1, 1 }, { 1, 0 } };  
    // ȫ0����  
    private static final long[][] ZERO = { { 0, 0 }, { 0, 0 } };  
    //  
    public static long[][] fb(int n) {  
        if (n == 0) {              return ZERO;          }  
        if (n == 1) {              return UNIT;          }  
        // n������  
        if ((n & 1) == 0) {  
        	long[][] matrix = fb(n >> 1);  
            return matrixMultiply(matrix, matrix);  
        }  
        // n��ż��  
        long[][] matrix = fb((n - 1) >> 1);  
        return matrixMultiply(matrixMultiply(matrix, matrix), UNIT);  
    }  
      
    //������� 
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
    //����ʵ�־�������㷨
    public static long matrix(int n){
        long[][] m = fb(n); 
        return m[0][1];
    }
 
    public static void main(String[] args) throws IOException {   	
		int n = 10;
		long start, end;
        long time =0;
        long result = 0;
        int i ;
	    
        for(i=0;i<10;i++){
    		start = System.nanoTime();
            result = swapCompute(n);
            end = System.nanoTime();
            time += (end - start);
            System.out.println( "��ʼ : "+start+"\t������"+end+"\t��ʱ��"+(end -start) );  
        }
	    System.out.println("swapCompute�����\t "+result + "   swapCompute��ʱ : \t"+time/10 );  
	    
//        for(i=1;i<11;i++){
//    		start = System.nanoTime();
//            result = formula2(n);
//            end = System.nanoTime();
//            time += (end - start);
//        }
//	    System.out.println("formula2�����\t\t "+result+ "   formula2��ʱ : \t\t "+time/10 );  
    }
}