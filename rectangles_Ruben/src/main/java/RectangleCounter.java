import java.util.LinkedList;

class RectangleCounter
{
	/*
	 *	Variable
	 * */
	private int	 count;
	/*
	 *	Constructor
	 * */
	public RectangleCounter()
	{
		count = 0;
	}	
	/*
	 *	Methods
	 * */
	public int countRectangles(String[]  input)
	{
		int len_array = input.length;
		if( len_array != 0 )
		{
			// Iterate the array
			// Indicate the actual line/sud y position
			for (int y_pos = 0; y_pos < len_array; y_pos++)
			{
				// length of the String
				String st = input[y_pos];
				int len_string = st.length();

				for(int x_pos = 0; x_pos < len_string; x_pos++)
				{
					char value = st.charAt( x_pos );
					if ( value == '+' )
					{
						// see if is rectangle
						hasRectangles(x_pos, y_pos, input);
					}				
				}
			}
		}
		return getCount();
	}
	/*
	 *	hasRectangles method
	 * */
	public void hasRectangles( int pos_x_caracter, int pos_y_caracter, String[] inputGrid )
	{
		int x_position = pos_x_caracter;
		int y_position = pos_y_caracter;

		// List of the positions where we can find + symbol HORIZONTALLY
		LinkedList<Integer> pos = countPlusSymbolHorizontal( inputGrid[y_position] );
				
		while ( !pos.isEmpty() ) 
		{
			// count corners
			int	corner	= 0;

			//##############################################################
			// FIRST STEPS

			// Position where we can found + symbol
			int x_position_goal	= pos.remove();

			// Quant of moves that are needed to go from the first + symbol to the + symbol in the list  
			int totalmoves_horizontal = x_position_goal - x_position;

 			if(totalmoves_horizontal > 0) 
			{
				//##############################################################
				// MOVE TO RIGHT
				int move_to_right = 0;
				for (int x =x_position+1; x <= x_position_goal; x++) 
				{
					if( 	 inputGrid[pos_y_caracter].charAt(x) == '-' || inputGrid[pos_y_caracter].charAt(x) == '+') 
					{
						move_to_right++;
					}
				}
				if( totalmoves_horizontal == move_to_right  )
				{
					corner++;
				}
				//##############################################################
				// List of the positions where we can find + symbol VERTICALLY
				LinkedList<Integer> list_vert = countPlusSymbolVertical( x_position_goal, inputGrid );

				while( !list_vert.isEmpty() )
				{
					// Position where we can found + symbol
					int y_position_goal	= list_vert.remove();

					// Quantity of moves that are needed to go from the first + symbol to the + symbol in the list  
					int totalmoves_vertical = y_position_goal - y_position;

					// To prevent bad code
					if(corner > 1)
						corner = 1;

					//##############################################################
					// GO DOWN
					if (totalmoves_vertical > 0) 
					{
						if (corner == 1) 
						{
							int moves_v = 0;
							for (int y = y_position+1; y <= y_position_goal; y++) 
							{
								if( inputGrid[y].charAt(x_position_goal) == '|' || inputGrid[y].charAt(x_position_goal) == '+' )
								{
									moves_v++;
								}
							}
							if( totalmoves_vertical == moves_v && inputGrid[y_position_goal].charAt(x_position_goal) == '+' )
							{
								corner++;
							}
						}
						//##############################################################
						// GO LEFT
						if (corner == 2) 
						{
							int moves_left = 0;
							for (int x = x_position_goal-1; x >= x_position; x--) 
							{
								if( inputGrid[y_position_goal].charAt(x) == '-' || inputGrid[y_position_goal].charAt(x) == '+')
								{
									moves_left++;
								}
							}
							if( totalmoves_horizontal == moves_left  && inputGrid[y_position_goal].charAt(x_position) == '+')
							{
								corner++;
							}
						}
						//##############################################################
						// GO UP
						if(corner == 3)
						{
							int moves_up = 0;
							for (int y = y_position_goal-1; y >= y_position; y--) 
							{
								if( inputGrid[ y ].charAt(x_position) == '|' || inputGrid[ y ].charAt(x_position) == '+' ) 
								{
									moves_up++;
								}
							}
							if( totalmoves_vertical == moves_up  && inputGrid[ y_position ].charAt(x_position) == '+')
							{
								corner++;
							}
						}
						//##############################################################
						// if found 4 corners
						if ( corner == 4 ) 
						{				
							count++;	
						}
					}
				}
			}	
		}
	}
	/*
	 *		countPlusSymbolVertical method 
	 * */
	public LinkedList<Integer> countPlusSymbolVertical(int x_pos, String[] inputGrid)
	{
		// This LinkedList will store the positions where we see '+' 
		LinkedList<Integer> positions = new LinkedList<>();

		for (int i = 0; i < inputGrid.length; i++ ) 
		{
			char val = inputGrid[i].charAt(x_pos);
			if( val == '+' )
			{
				positions.add( i );
			}	
		}
		return positions;
	}
	/*
	 *		countPlusSymbolHorizontal method 
	 * */
	public LinkedList<Integer> countPlusSymbolHorizontal(String st)
	{
		// This LinkedList will store the positions where we see '+' 
		LinkedList<Integer> positions = new LinkedList<>();

		for (int i = 0; i < st.length(); i++) 
		{
			char val = st.charAt(i);
			if( val == '+' )
			{
				positions.add( i );
			} 	
		}
		return positions;
	}
	/*
	 *	getCount method
	 * */
	public int getCount()
	{
		return count;
	}
}