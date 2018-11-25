/**
 * BinaryTree
 */
public class BinaryTree 
{
    Zipper root;

    public BinaryTree(Zipper root)
    {
        this.root = root;
    }
    public BinaryTree(int value)
    {
        root = new Zipper(value);
    }
    public String printTree()
    {
        return print(root);
    }
    public String print(Zipper zipper)
    {
        if (zipper == null) 
        {
            return "null";    
        }
        return String.format("value: %d, left: %s, right: %s", zipper.value, keys(print(zipper.left)),keys(print(zipper.right)));
    }
    public String keys(String val)
    {
        return val == "null" ? val : ("{ " + val + " }");  
    }
    public Zipper getRoot()
    {
        return root;
    }
    @Override
    public boolean equals(Object object) 
    {
		BinaryTree b = (BinaryTree) object;
		return root.equals(b.root);
	}
}