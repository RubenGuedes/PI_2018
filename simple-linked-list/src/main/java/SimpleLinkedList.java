import java.lang.reflect.Array;
import java.util.NoSuchElementException;

/**
 * SimpleLinkedList
 */
public class SimpleLinkedList<E> 
{
    Element<E> head;
    int size;

    public SimpleLinkedList()
    {
        head = null;
        size = 0;
    }
    public SimpleLinkedList(E[] values)
    {
        head = null;
        size = 0;
        addValues(values);
    }

    public int size()
    {
        return size;
    }

    public void addValues(E[] values)
    {
        for (E val : values) 
        {
            push(val);    
        }
    }

    public void push(E elem)
    {
        Element<E> element = new Element<>();
        element.value = elem;
        element.next  = head;

        head = element;
        size++;
    }

    public E pop() throws NoSuchElementException 
    {
        if(head == null)
            throw new NoSuchElementException();

        E value = head.value;
        head = head.next;
        size--;

        return value;
    }

    public void reverse()
    {
        Element<E> next = head.next;
        Element<E> prev = null;
        head.next = prev;

        while(next != null)
        {
            prev = head;
            head = next;

            next = next.next;
            head.next = prev;
        }
    }
    public E[] asArray(Class<E> type) 
    {
        E[] array = (E[]) Array.newInstance( type, size );

        Element<E> e = head;
        int i = 0;
        while(e != null)
        {
            array[i] = e.value;
            e = e.next;
            i++;
        }

        return array;
    }
    
}

class Element<E>
{
    E value;
    Element<E> next;
}