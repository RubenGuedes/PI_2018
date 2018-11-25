import java.util.Random;

/**
 * Cipher
 */
public class Cipher 
{
    /*
        Variables
     */
    private final int KEY_LENGTH = 100;
    private final String Letters = "abcdefghijklmnopqrstuvwxyz";
    private final String key;
    private Random random;
    /*
        Constructors
     */
    public Cipher()
    {
        random = new Random();
        key = generateKey();
    }
    public Cipher(String key)
    {
        if (!key.matches("^[a-z]+$")) throw new IllegalArgumentException();
        
        random = new Random();
        this.key = key;
    }
    /*
            METHODS
     */
    public String getKey()
    {
        return key;
    }
    public String decode(String text)
    {
        String encoded = "";
        for (int i = 0; i < text.length(); i++) 
        {
            char text_char          = text.charAt(i);
            int  text_char_position = findPosition(text_char);
            int  cipher             = getOffset(i);
            int  index              = text_char_position - cipher;
            if( index < 0 )
            {
                index += Letters.length();
            }
            encoded += String.valueOf( Letters.charAt(index) );    
        }
        return encoded;
    }
    public String encode(String text_coded)
    {
        String encoded = "";
        for (int i = 0; i < text_coded.length(); i++) 
        {
            char text_char          = text_coded.charAt(i);
            int  text_char_position = findPosition(text_char);
            int  cipher             = getOffset(i);
            int  index              = text_char_position + cipher;
            if( index >= Letters.length() )
            {
                index -= Letters.length();
            }
            encoded += String.valueOf( Letters.charAt(index) );    
        }
        return encoded;
    }
    public int findPosition(char x)
    {
        int letters_l = Letters.length();
        for (int i = 0; i <letters_l ; i++) 
        {
            if (Letters.charAt(i) == x) 
            {
                return i;
            }
        }
        return -1;
    }
    protected int getOffset(int index) 
    {
		char c = key.charAt(index);
		return findPosition( c );
	}
    public String generateKey()
    {
        String k = "";
        for (int i = 0; i < KEY_LENGTH; i++) 
        {
            int index = random.nextInt(Letters.length());
            String char_string = String.valueOf( Letters.charAt(index) );
            k += char_string;
        }
        return k;
    }
}