/**
 * FunctionTest.java
 * 
 *   $Id: FunctionTest.java,v 1.4 2014/10/08 00:14:14 agm1392 Exp $
 * 
 *   $Log: FunctionTest.java,v $
 *   Revision 1.4  2014/10/08 00:14:14  agm1392
 *   Further commented, completed FunctionTest Class
 *
 *   Revision 1.3  2014/10/07 20:05:11  agm1392
 *   Added commenting
 *
 *   Revision 1.2  2014/10/06 22:27:48  agm1392
 *   Completed the derivitive method of the Sum Class
 *
 *   Revision 1.1  2014/10/06 17:05:11  agm1392
 *   Part one- abstract class and initial function subclasses
 *
 *
 */

/**
 * @author Andrew
 *
 */
public class FunctionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//test 1
		System.out.println("Function 1");
		Function f1 = new Sum(Variable.X,Variable.X,new Constant(10),new Constant(1));
		System.out.println("Function " + f1);
		System.out.println("Value at 2 " + f1.evaluate(2));
		System.out.println("derivative " + f1.derivative());
		System.out.println("Integrals");
		System.out.println(f1.integral(0, 10, 8));
		System.out.println(f1.integral(5, 10, 8));
		System.out.println("");
		
		//test 2
		System.out.println("Function 2");
		Function f2 = new Product(Variable.X,Variable.X,f1,new Constant(10),new Constant(1));
		System.out.println("Function " + f2);
		System.out.println("Value at 2 " + f2.evaluate(2));
		System.out.println("derivative " + f2.derivative());
		System.out.println("Integrals");
		System.out.println(f2.integral(0, 10, 8));
		System.out.println(f2.integral(5, 10, 8));
		
		//Test 3
		System.out.println("");
		System.out.println("Function 3");
		Function f3 = new Cosine(f2);
		System.out.println("Function " + f3);
		System.out.println("Value at 2 " + f3.evaluate(2));
		System.out.println("derivative " + f3.derivative());
		System.out.println("Integrals");
		System.out.println(f3.integral(0, 10, 8));
		System.out.println(f3.integral(5, 10, 8));
		
		//Test 4
		System.out.println("");
		System.out.println("Function 4");
		Function f4 = new Sine(f3);
		System.out.println("Function " + f4);
		System.out.println("Value at 2 " + f4.evaluate(2));
		System.out.println("derivative " + f4.derivative());
		System.out.println("Integrals");
		System.out.println(f4.integral(0, 10, 8));
		System.out.println(f4.integral(5, 10, 8));
		
		//Test 5
		System.out.println("");
		System.out.println("Function 5");
		Function f5 = new Product(new Constant(0),new Constant(5));
		System.out.println("Function " + f5);
		
		//Test 6
		System.out.println("");
		System.out.println("Function 5");
		Function f6 = new Product(new Constant(1),new Constant(5));
		System.out.println("Function " + f6);
		
		
		//OUTPUT
		/*
		    Function 1
			Function ( x + x + 11.0 )
			Value at 2 15.0
			derivative 2.0
			Integrals
			210.0
			130.0
			
			Function 2
			Function ( x * x * ( x + x + 11.0 ) * 10.0 )
			Value at 2 600.0
			derivative ( ( x * ( ( x * 20.0 ) + ( ( ( x + x + 11.0 ) * 10.0 ) ) ) ) + ( ( x * ( x + x + 11.0 ) * 10.0 ) ) )
			Integrals
			87734.375
			79140.625
			
			Function 3
			Function cos( ( x * x * ( x + x + 11.0 ) * 10.0 ) )
			Value at 2 -0.9990234788329058
			derivative ( ( ( x * ( ( x * 20.0 ) + ( ( ( x + x + 11.0 ) * 10.0 ) ) ) ) + ( ( x * ( x + x + 11.0 ) * 10.0 ) ) ) * Sin( ( x * x * ( x + x + 11.0 ) * 10.0 ) ) * -1.0 )
			Integrals
			1.363807615322622
			-0.0907206928343967
			
			Function 4
			Function Sin( Cos( ( x * x * ( x + x + 11.0 ) * 10.0 ) ) )
			Value at 2 -0.8409429670428051
			derivative ( ( ( ( x * ( ( x * 20.0 ) + ( ( ( x + x + 11.0 ) * 10.0 ) ) ) ) + ( ( x * ( x + x + 11.0 ) * 10.0 ) ) ) * Sin( ( x * x * ( x + x + 11.0 ) * 10.0 ) ) * -1.0 ) * Cos( Cos( ( x * x * ( x + x + 11.0 ) * 10.0 ) ) ) )
			Integrals
			1.2005203975387593
			-0.024366336228709012
			
			Function 5
			Function 0.0
			
			Function 6
			Function 5.0
			
		 * 
		 * 
		 */
	}

}
