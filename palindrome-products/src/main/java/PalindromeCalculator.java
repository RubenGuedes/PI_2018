import java.util.*;

public class PalindromeCalculator 
{
    public SortedMap<Long,List<List<Integer>>> getPalindromeProductsWithFactors(int i, int i1) 
    {
        // restrictions
        if(i > i1) 
        {
            throw new IllegalArgumentException("invalid input: min is "+i+" and max is "+i1);
        }

        var palindrones = genPalindrones(i, i1);

        if(palindrones.isEmpty()) {
            throw new NoSuchElementException("no palindrome with factors in the range "+i+" to "+i1);
        }

        return palindrones;
    }

    private SortedMap<Long, List<List<Integer>>> genPalindrones(int lower, int upper) 
    {
        SortedMap<Long, List<List<Integer>>> map = new TreeMap<>();

        for(var i = lower; i <= upper; i++) 
        {

            for(var j = lower; j <= upper; j++) 
            {
                long product = i * j;
                if(isPalindrome(product)) 
                {

                    List<List<Integer>> l = new ArrayList<>();
                    if(map.containsKey(product)) 
                    {
                        l = map.get(product);
                    }

                    int ti = i, tj = j;
                    if(j < i) 
                    {
                        ti = j;
                        tj = i;
                    }

                    if(!l.contains(Arrays.asList(ti, tj))) 
                    {
                        l.add(Arrays.asList(ti, tj));
                    }

                    map.put(product, l);
                }
            }
        }

        return map;
    }
    
    // see if a palindrome
    private static boolean isPalindrome(long numb) 
    {
        String intStr = String.valueOf(numb);
        return intStr.equals(new StringBuilder(intStr).reverse().toString());
    }
}