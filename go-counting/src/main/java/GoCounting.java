import java.util.HashMap;
import java.util.HashSet;
import java.awt.Point;
import java.util.Set;

/**
 * GoCounting
 */
public class GoCounting 
{
    /**
     * Variables
     */
    String board;
    /**
     * Constructor
     */
    public GoCounting(String board)
    {
        this.board = board;
    }
    /**
     * Methods
     */
    public Player getTerritoryOwner(int x, int y)
    {
        int   black   = 0;
        int   white   = 0;
        int   len_row = countRows(board);
        int   len_col = countColumns(board);
        int[] colors;
        String[][] array_string = createArrayString(board);
        onLimits(x, y, len_col, len_row);

        colors = seeVertical(  array_string, black, white, x, y, len_row, false); // see up
        black = colors[0];
        white = colors[1];
        colors = seeHorizontal(array_string, black, white, x, y, len_col , true ); // see right
        black = colors[0];
        white = colors[1];
        colors = seeVertical(  array_string, black, white, x, y, len_row, true ); // see down
        black = colors[0];
        white = colors[1];
        colors = seeHorizontal(array_string, black, white, x, y, len_col , false); // see left
        black = colors[0];
        white = colors[1];

        if (black == 0 && white > 1) 
        {
            return Player.WHITE;
        } 
        else if (black > 1 && white == 0)
        {
            return Player.BLACK;
        }
        else 
        {
            return Player.NONE;
        }
    }
    public Set<Point> getTerritory(int x, int y)
    {
        int len_col  = countColumns(board);
        int len_row = countRows(   board);
        onLimits(x, y, len_col, len_row);
        String[][] array_string = createArrayString(board);
        
        int saved_x = x;
        int saved_y = y;
        Player atual = getTerritoryOwner(x, y);
        Set<Point> territory = new HashSet<>();

        while (y>0 && array_string[x][y].equals(" ")) 
        {
            while (x< len_col && array_string[x][y].equals(" ")) 
            {
                x++;
                if (atual == getTerritoryOwner(x, y)) 
                {
                    territory.add( new Point(x, y));    
                }
            }
            x = saved_x;
            while (x>0 && array_string[x][y].equals(" ")) 
            {
                y--;
                if (atual == getTerritoryOwner(x, y)) 
                {
                    territory.add( new Point(x, y));    
                }
                x--;
            }
            y--;
            x = saved_x;   
        }
        y = saved_y;
        x =saved_x;
        while (y< len_row && array_string[x][y].equals(" ")) 
        {
            while (x < len_col && array_string[x][y].equals(" ")) 
            {
                x++;
                if (atual == getTerritoryOwner(x, y)) 
                {
                    territory.add( new Point(x, y));    
                }
            }
            x = saved_x;
            while (x > 0 && array_string[x][y].equals(" ")) 
            {
                x--;
                if (atual == getTerritoryOwner(x, y)) 
                {
                    territory.add( new Point(x, y));    
                }
            }
            y++;
            x = saved_x;
        }
        return territory;
    }
    public HashMap<Player, Set<Point>> getTerritories()
    {
        int x_max = countColumns(board);
        int y_max = countRows(board);
        String[][] array_string = createArrayString(board);
        HashMap<Player, Set<Point>> territories = new HashMap<>();
        Set<Point> blackTerritory = new HashSet<>();
        Set<Point> whiteTerritory = new HashSet<>();
        Set<Point> noneTerritory = new HashSet<>();
        for (int y = 0; y < y_max; y++) 
        {
            for (int x = 0; x < x_max; x++) 
            {
                if (array_string[y][x].equals(" ")) 
                {
                    Player p = getTerritoryOwner(x, y);
                    if (p == Player.BLACK) 
                    {
                        blackTerritory.add( new Point(x, y));
                    } 
                    else if (p == Player.WHITE)
                    {
                        whiteTerritory.add(new Point(x, y));
                    }
                    else if (p == Player.NONE)
                    {
                        noneTerritory.add(new Point(x, y));
                    }
                }
            }
            territories.put(Player.BLACK, blackTerritory);
            territories.put(Player.WHITE, whiteTerritory);
            territories.put(Player.NONE, noneTerritory);
        }
        return territories;
    }
    /**
     * Auxiliar methods
     */
    private void onLimits(int x, int y, int len_col, int len_row)
    {
        if ( !(x >= 0 && x < len_col && y >= 0 && y < len_row) ) 
        {
            throw new  IllegalArgumentException("Invalid coordinate");   
        }
    }
    private int[] seeVertical(String[][] array_string, int black, int white, int x, int y, int len_row, boolean down)
    {
        int[] colors = new int[2];
        if(down)
        {
            while ( y < len_row) 
            {
                if(array_string[x][y].equals("B"))
                {
                    black++;
                    break;
                }
                if(array_string[x][y].equals("W"))
                {
                    white++;
                    break;
                }
                y++;
            }
        }
        else
        {
            while ( y > 0 ) 
            { 
                if(array_string[x][y].equals("B"))
                {
                    black++;
                    break;
                }
                if(array_string[x][y].equals("W"))
                {
                    white++;
                    break;
                }
                y--; 
            }
        }
        colors[0] = black;
        colors[1] = white;
        return colors;
    }
    private int[] seeHorizontal(String[][] array_string, int black, int white, int x, int y, int len_col, boolean right)
    {
        int[] colors = new int[2];
        if(right)
        {
            while ( x < len_col) 
            {
                if(array_string[x][y].equals("B"))
                {
                    black++;
                    break;
                }
                if(array_string[x][y].equals("W"))
                {
                    white++;
                    break;
                }
                x++;
            }
        }
        else
        {
            while ( x > 0 ) 
            { 
                if(array_string[x][y].equals("B"))
                {
                    black++;
                    break;
                }
                if(array_string[x][y].equals("W"))
                {
                    white++;
                    break;
                }
                x--; 
            }
        }
        colors[0] = black;
        colors[1] = white;
        return colors;
    }
    private int         countColumns(String string)
    {
        int i = 0;
        while ( !string.substring(i, i+1).equals("\n") ) 
        {
            i++;
        }
        return i;
    }
    private int         countRows(String string)
    {
        int count = 0;
        for (int i = 0; i < string.length(); i++) {
            if  ( string.substring(i, i+1).equals("\n") ) 
            {
                count++;
            } 
        }
        count++;
        return count;
    }
    private String[][]    createArrayString(String string)
    {
        int x_total = countColumns(string);
        int y_total = countRows(   string);
        String[][] array_string = new String[x_total][y_total];

        int x = 0;
        int y = 0;
        int count = 0;
        int string_len = string.length();
        while (count != string_len) 
        {
            String c = string.substring(count,count+1);
            if ( c.equals("\n") ) 
            {
                y++;
            } 
            else 
            {
                array_string[x][y] = c;
                x = ( x + 1 ) % (x_total-1);    
            }    
            count++;
        }
        return array_string; 
    }
    
}