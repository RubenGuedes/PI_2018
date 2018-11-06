import java.util.Stack;
import java.lang.IllegalArgumentException;
/**
 * BaseConverter
 */
public class BaseConverter 
{
    /**
     * Global Variables
     */
    private int     base;
    private int[]   number;
    /**
     * Constructor
     */
    public BaseConverter(int base, int[] number)
    {
        this.base   = base;
        this.number = number;
        exceptions();
    }
    /**
     * Method "convertToBase"
     * @param val
     */
    public int[] convertToBase(int val)
    {
        if (val >= 2) 
        {
            
            if (number.length == 0 ) 
                return new int[]{0};
            else 
            {
                int[] value = toDecimal( base, number);
                return toOtherBase(val, value);
            }
        }
        else
            throw new IllegalArgumentException("Bases must be at least 2.");
    }
        
    public void exceptions()
    {
        if (base<2)
            throw new IllegalArgumentException("Bases must be at least 2.");
        if(!inCorrectBase(number, base))
            throw new IllegalArgumentException("All digits must be strictly less than the base.");
    }
    /**
     * Method toDecimal
     * @param base_to_convert
     * @param number
     * @return
     */
    public int[] toDecimal(int base_to_convert, int[] number)
    {
        // a representation of the result in a string
        int value = 0;
        // position on array
        int position = 0;
        // length of the number
        int number_length = number.length-1; 
        // Convert to decimal
        for (int i = number_length; i >= 0; i--) 
        {
            position = number_length - i;
            value   += number[i] * pow(base_to_convert , position);
        }

        // int to String
        String  val_string  = String.valueOf(value); 
        // len of the String
        int     string_len  = val_string.length();   
        // create new array
        int[]   array_val   = new int[string_len];
        // convert int to int[]
        for (int i = 0; i < string_len; i++) 
        {
            array_val[i] = Integer.valueOf( val_string.substring(i, i+1) );
        }
        return array_val;
    }
    /**
     * 
     * @param base_goal
     * @param number
     * @return
     */
    public int[] toOtherBase(int base_goal, int[] number)
    {
        // Auxiliar stack
        Stack<Integer> binary_stack = new Stack<>();
        // remaning variable & many_adds count how many adds to the stack it does
        int remaining = 0;
        int many_adds = 0;
        // number to work
        int int_number  = arrayToInteger(number);
        int copy_int_number = int_number;
        // Process to insert the remaining values in the stack
        while (int_number != 0 ) 
        {
            remaining  = int_number % base_goal;
            int_number = int_number / base_goal;
            binary_stack.add(remaining);
            many_adds++;
        }
        // if int_number == 0 return [0]
        if (copy_int_number == 0)
            return new int[]{0};
        else
        {
            int[] binary_array = new int[many_adds];
            for (int i = 0; i < many_adds; i++)
            {
                binary_array[i] = binary_stack.pop();
            }
            return binary_array;
        }
    }
    /**
     * 
     * @param number
     * @return
     */
    public int arrayToInteger(int[] number)
    {
        String value = "";
        for (int i = 0; i < number.length; i++) 
        {
            value += number[i];    
        }
        return Integer.valueOf(value);
    }
    /**
     * 
     * @param number
     * @param correspondence_base
     * @return
     */
    public boolean inCorrectBase(int[] number, int correspondence_base)
    {
        int contador = 0;
        int number_len = number.length;
        for (int i = 0; i < number_len; i++) 
        {
            if (correspondence_base > number[i])
            {
                if(number[i] < 0 && correspondence_base != 10 ) 
                    throw new IllegalArgumentException("Digits may not be negative.");
                contador++;
            }
        }
        return (contador == number_len) ? true : false ;
    }
    /**
     * 
     * @param val1
     * @param val2
     * @return total
     */
    public int pow(int val1, int val2)
    {
        int total = 1;
        for (int i = 0; i < val2; i++) 
        {
            total *= val1;
        }
        return total;
    }
}