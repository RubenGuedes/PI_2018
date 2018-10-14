import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

class Etl 
{
    public Map<String, Integer> transform(Map<Integer, List<String> > old) 
    {
    	// Nova estrutura de dados para converter a chave em valor e vice-versa
    	Map<String, Integer> converted = new HashMap<>();
    	
    	for(Map.Entry<Integer, List<String> > entr : old.entrySet() )
    	{
    		// guarda a pontuação para coloca-la como valor
    		int valor = entr.getKey() ;
    		
    		for (String letr :entr.getValue() ) 
    		{
    			String chave = letr.toLowerCase();
    			converted.put(chave, valor);
    		}

    	}

    	return converted;
    }
}
