import java.util.List;

final class RelationshipComputer<T> 
{
    public Relationship computeRelationship(List<T> list1, List<T> list2) 
    {
        if (list1.size() > list2.size())  
        {
            if (computeRelationship(list2, list1) == Relationship.SUBLIST)
                return Relationship.SUPERLIST;
        }
        else 
        {
            int limit = list2.size() - list1.size() + 1;
            for (int i = 0; i < limit; i++) 
            {
                int j;

                for (j = 0; j < list1.size() && list1.get(j).equals(list2.get(i + j)); j++);
                
                if (j == list1.size())
                    return limit == 1 ? Relationship.EQUAL : Relationship.SUBLIST;
            }
        }
        return Relationship.UNEQUAL;
    }
}