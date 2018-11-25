import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;
import java.util.List;

class BinarySearchTree<T extends Comparable<T>> 
{
    Node<T> root;

    public BinarySearchTree()
    {
        root = null;
    }
    void insert(T value) 
    {
        root = insert(value, root);
    }
    private Node<T> insert(T value, Node<T> node)
    {
        if (node == null) 
        {
            return new Node<T>(value);    
        }
        else if (value.compareTo(node.getData()) <= 0) 
        {
            node.left_node = insert( value, node.getLeft());
        }
        else if (value.compareTo(node.getData()) > 0)
        {
            node.right_node = insert(value, node.getRight());
        }
        return node;
    }

    List<T> getAsSortedList() 
    {
        List<T> result = new ArrayList<T>();
        order(result, root);
        return result;
    }
    void order(List<T> result, Node<T> node)
    {
        if (node == null)
        { 
            return;
        }
        else
        {
            order(result, node.getLeft() );
            result.add(   node.getData() );
            order(result, node.getRight());
        }
    }

    List<T> getAsLevelOrderList() 
    {
        List<T> result = new ArrayList<>();
        Queue< Node<T> > queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty() ) 
        {
            Node<T> begin = queue.poll();
            if (begin == null)  
                continue;
            result.add(begin.getData());
            queue.offer(begin.getLeft());
            queue.offer(begin.getRight());
        }
        return result;
    }

    Node<T> getRoot() 
    {
        return root;
    }

    static class Node<T> 
    {
        T element;
        Node<T> left_node;
        Node<T> right_node;

        public Node()
        {
            this(null, null, null);
        }
        public Node(T element)
        {
            this(element, null, null);
        }
        public Node(T element, Node<T> left_node, Node<T> right_node)
        {
            this.element     = element;
            this.left_node   = left_node;
            this.right_node  = right_node;

        }

        Node<T> getLeft() 
        {
            return left_node;
        }

        Node<T> getRight() 
        {
            return right_node;
        }

        T getData() 
        {
            return element;
        }
    }
}
