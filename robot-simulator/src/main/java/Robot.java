public class Robot
{
	/*
		Variables
	 */
	GridPosition 	pos;
	Orientation		ori;
	/*
		Constructor
	 */
	public Robot(GridPosition pos, Orientation ori)
	{
		this.pos = pos; 
		this.ori = ori;
	}
	/*
		Commands
	 */
	public void turnLeft()
	{
		switch(ori)
		{
			case 	NORTH: ori = Orientation.WEST;
					break;

			case 	SOUTH: ori = Orientation.EAST;
					break;

			case 	EAST:  ori = Orientation.NORTH;
					break;

			case 	WEST:  ori = Orientation.SOUTH;
					break;
		}
	}

	public void turnRight()
	{
		switch(ori)
		{
			case 	NORTH: ori = Orientation.EAST;
					break;

			case 	SOUTH: ori = Orientation.WEST;
					break;

			case 	EAST:  ori = Orientation.SOUTH;
					break;

			case 	WEST:  ori = Orientation.NORTH;
					break;
		}
	}

	public void advance()
	{
		switch(ori)
		{
			case 	NORTH: pos.advanceY(1);
					break;

			case 	SOUTH: pos.advanceY(-1);
					break;

			case 	EAST:  pos.advanceX(1);
					break;

			case 	WEST:  pos.advanceX(-1);
					break;
		}
	}

	public Orientation  getOrientation()
	{

		return ori;
	}

	public GridPosition getGridPosition()
	{

		return new GridPosition(
						pos.getAdvanceX(), 
						pos.getAdvanceY() );
	}

	public void simulate(String command)
	{
		for (int i = 0; i < command.length(); i++) 
		{
			char act = command.charAt(i);
			action(act);
		}
	}
	// letter to action
	public void action(char act)
	{
		switch(act)
		{
			case 'A': 	advance();
						break;

			case 'R': 	turnRight();
						break;

			case 'L': 	turnLeft();
						break;
		}
	}
}