/**
 * Function.java
 * 
 *   $Id: Function.java,v 1.4 2014/10/08 01:48:32 agm1392 Exp $
 * 
 *   $Log: Function.java,v $
 *   Revision 1.4  2014/10/08 01:48:32  agm1392
 *   Completed commenting
 *
 *   Revision 1.3  2014/10/07 20:05:11  agm1392
 *   Added commenting
 *
 *   Revision 1.2  2014/10/06 19:14:55  agm1392
 *   Added integral function to existing classes
 *
 *   Revision 1.1  2014/10/06 17:05:13  agm1392
 *   Part one- abstract class and initial function subclasses
 *
 *
 */
/**
 * 
 * @author Andrew
 *An abstract class to represent a mathematical function
 */
public abstract class Function {
	
	/**
	 * 
	 * @param x
	 * a double value to take the place of the variable object in the function
	 * @return
	 * a double value representing the falue of the function 
	 * with respect to x value
	 */
	public abstract double evaluate(double x);
	
	/**
	 * 
	 * @return
	 * A function object that is the derivative of this function
	 */
	public abstract Function derivative();
	
	//public abstract double integral(double lower,double upper, int accuracy);

	/**
	 * Returns a boolean representing if the function is a constant or is made up of only
	 * constant values
	 * @return
	 * false
	 */
	public boolean isConstant()
	{
		return false;
	}
	
	
	/**
	 * Returns a string representing the function
	 */
	public abstract String toString();
	
	/**
	 * 
	 * @param lower
	 * double value, lower range of the integral 
	 * @param upper
	 * double value, upper range of the integral 
	 * @param accuracy
	 * Accuracy of the integral function, used in most implementations
	 * @return
	 */
	public double integral(double lower,double upper,int accuracy)
	{
		// TODO Auto-generated method stub
		double integralValue = 0;
		double interval = (upper-lower)/accuracy;
		integralValue += this.evaluate(lower);
		for(double i = lower+ interval; i < upper;i+=interval)
		{
			integralValue+= this.evaluate(i) *2;
		}
		integralValue += this.evaluate(upper);
		return (interval/2) * integralValue;
	}
}
