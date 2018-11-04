import java.util.LinkedList;
import java.util.Stack;

/**
 * WordProblemSolver
 */
public class WordProblemSolver
{
    /**
     * @param phrase
     * @return result
     */
    public int solve(String phrase)
    {
        // convert string to an array 
        String[] arr_phrase = phrase.split(" ");

        // linked list with only necessary information
        LinkedList<String> list = convert_string_linkedlist( arr_phrase );

        // convert the postfix string to the respective result
        int result = evalPostfix(list);  

        return result;
    }
    /**
     * evalPostfix
     * @param postfix_list
     * @return 
     */
    public int evalPostfix(LinkedList<String> postfix_list)
    {
        Stack<String> stack_operator = new Stack<>();
        Stack<Integer> stack_number   = new Stack<>();
        short quant = 0; // if quant == 2 do the operation and store on stack_number

        for (String list_value : postfix_list) 
        {
            if (isOperator(list_value)) 
            {
                stack_operator.add(  list_value  );
            }
            else
            {
                stack_number.add( Integer.valueOf(list_value) );
                quant++;
                if (quant == 2) 
                {
                    int val2 = stack_number.pop();
                    int val1 = stack_number.pop();
                    String op = stack_operator.pop();

                    quant = 1;
                    int result = operation(val1, val2, op);
                    stack_number.push(result);
                }
            }
        }
        return stack_number.pop();
    }
    /**
     * operation
     * @param val1
     * @param val2
     * @param list_value
     * @return op
     */
    public int operation(int val1, int val2, String list_value)
    {
        int op = 0;
        switch ( list_value ) 
        {
            // minus operator
            case "minus": op = val1 - val2;
                            break;
            // division operator
            case "divided": op = val1 / val2;
                            break;
            // plus operator
            case "plus": op = val1 + val2;
                            break;
            // multiplied operator                
            case  "multiplied": op = val1 * val2;
                            break;
        }
        return op;
    }
    /**
     * @param string 
     * @return list_phrase
     */
    public LinkedList<String> convert_string_linkedlist(String[] string)
    {
        LinkedList<String> list_phrase = new LinkedList<>();
        int                 string_len = string.length;

        for (int i = 2; i < string_len ; i++) 
        {
            String word = string[i];

            if ( word.compareTo("President") == 0) 
                throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
            else if (word.compareTo("cubed?") == 0)
                throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
            else if ( word.compareTo("by") == 0) 
                continue;
            else if( i == (string_len -1) )
            {
                String substring_last =  word.substring(0, (word.length()-1) );
                list_phrase.add( substring_last );
            }
            else
            {
                list_phrase.add( word );
            }
        }
        return list_phrase;
    }
    /**
     * Method isOperator
     * @param st
     * @return
     */
    public boolean isOperator( String st ) 
    {
        switch ( st ) 
        {
            // minus operator
            case "minus":
            // division operator
            case "divided":
            // plus operator
            case "plus":
            // multiplied operator                
            case  "multiplied":
                        return true;
            // is not operator
            default:
                        return false;
        }
    }
     /**
     * @param operator
     * @return priority_level
     */
    public short priority( String operator )
    {
        switch ( operator ) 
        {
            case "minus":
            case "plus": 
                            return 1;

            case "divided":
            case  "multiplied": 
                            return 2;

            default:
                            return 0;
        }
    }
}