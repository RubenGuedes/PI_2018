/**
 * Zipper
 */
public class Zipper 
{
    int value;
    Zipper up;
    Zipper left;
    Zipper right;

    public Zipper(int x)
    {
        value = x;
    }
    /**
     * Methods
     */
    public void setLeft(Zipper left)
    {
        if(left != null)
        {
            left.up = this;
        }
        this.left = left;
    }
    public Zipper getLeft()
    {
        return left;
    }
    public void setRight(Zipper right)
    {
        if ( right != null) 
        {
            right.up = this;    
        }
        this.right = right;
    }
    public Zipper getRight()
    {
        return right;
    }
    public int getValue()
    {
        return value;
    }
    public void setValue(int value)
    {
        this.value = value;
    }
    public BinaryTree toTree()
    {
        Zipper root = this;
        while(root.up != null)
        {
            root = root.up;
        }
        return new BinaryTree(root);
    }
}