class GridPosition 
{
    final int x;
    final int y;
    int x_aux;
    int y_aux;

    GridPosition(final int x, final int y) 
    {
        this.x = x;
        this.y = y;
        x_aux = x;
        y_aux = y;
    }

    @Override
    public int hashCode() 
    {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + x;
        result = PRIME * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj)
     {
        if (this == obj)
         {
            return true;
        } 
        else if (obj == null || getClass() != obj.getClass()) 
        {
            return false;
        } 
        else if (x != ((GridPosition) obj).x || y != ((GridPosition)obj).y) 
        {
            return false;
        } 
        else 
        {
            return true;
        }
    }

    public void advanceX(int x_aux)
    {
        this.x_aux += x_aux;
    }

    public void advanceY(int y_aux)
    {
        this.y_aux += y_aux;
    }
    public int getAdvanceX()
    {
        return x_aux;
    }
    public int getAdvanceY()
    {
        return y_aux;
    }
}
