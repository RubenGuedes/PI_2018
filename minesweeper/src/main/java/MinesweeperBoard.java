import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class MinesweeperBoard
{
    /*
        Variables
     */
    List<String> lista;
    /*
        Constructor
    */
    public MinesweeperBoard(List<String> lista)
    {
        this.lista = lista;
    }
    /**
     * Methods
     */
    public List<String> withNumbers()
    {
        String[] arr = new String[ lista.size() ];
        int i = 0;

        while(i < lista.size() )
        {
            // se tiver uma linha
            if(lista.size() <= 1)
            {
                String st = lista.get(0);
                arr[i] = avaliate1(st);
                i++;

            }
            // Se tiver 2 linhas
            else if (lista.size() == 2) 
            {
                if( i == 0)
                {
                    String st1 = lista.get(i);
                    String st2 = lista.get(i+1);
                    arr[i] = avaliate2(st1, st2);
                    i++;
                }else{
                    String st1 = lista.get(i);
                    String st2 = lista.get(i-1);
                    arr[i] = avaliate2(st1, st2);
                    i++;
                }
            }
            // Se tiver mais que duas linhas
            else
            {
                // se for a primeira linha
                if (i == 0) 
                {
                    String st1 = lista.get(i);
                    String st2 = lista.get(i+1);
                    arr[i] = avaliate2(st1, st2);
                    i++;  
                }
                // caso intermédio
                else if (i > 0 && i < (lista.size()-1) ) 
                {
                    String cima  = lista.get(i-1);
                    String medio = lista.get(i);
                    String baixo = lista.get(i+1);
                    arr[i] = avaliate3(cima, medio, baixo);
                    i++;  
                }
                // se for a ultima linha
                else
                {
                    String st1 = lista.get(i);
                    String st2 = lista.get(i-1);
                    arr[i] = avaliate2(st1, st2);
                    i++;
                }
            }
            
        }
        List<String> lista2 = Arrays.asList(arr);
        return lista2;
    }
    /**
     * Avalia só uma linha
     * @param st1
     * @return
     */
    public String avaliate1(String st1)
    {
        String linha = "";
        for (int i = 0; i < st1.length(); i++) 
        {
            String sub_str = st1.substring(i, i+1);
            short conta = 0;

            // se não for * analisar
            if ( !(sub_str.compareTo("*")==0)  ) 
            {
                conta += contar(st1, i , false);
                
                // colocar naquela posição a quantidade de * adjacentes
                if (conta == 0) 
                {
                    linha += " ";    
                } else
                {
                    linha += conta;
                }
            }
            // Se for * adiciona na string
            else
            {   
                linha += "*";
            }
        }
        return linha;
    }
    /**
     * Avalia as duas linhas
     * @param st1
     * @param st2
     * @return
     */
    public String avaliate2(String st1, String st2)
    {   
        String linha = "";
        for (int i = 0; i < st1.length(); i++) 
        {
            String sub_str = st1.substring(i, i+1);
            short conta = 0;

            // se não for * analisar
            if ( !(sub_str.compareTo("*")==0)  ) 
            {
                // ve na linha atual
                conta += contar(st1, i , false);
                // ver o que está em baixo     
                conta += contar(st2, i, true);

                // colocar naquela posição a quantidade de * adjacentes
                if (conta == 0) 
                {
                    linha += " ";    
                } else
                {
                    linha += conta;
                }
            }
            // Se for * adiciona na string
            else
            {   
                linha += "*";
            }
        }
        return linha;
    }
    /**
     * Avalia 3 linhas
     * @param cima
     * @param medio
     * @param baixo
     * @return
     */
    public String avaliate3(String cima, String medio, String baixo)
    {
        String linha = "";
        for (int i = 0; i < medio.length(); i++) 
        {
            String sub_str = medio.substring(i, i+1);
            short conta = 0;
            // se não for * analisar
            if ( !(sub_str.compareTo("*")==0)  ) 
            {
                conta += contar( cima, i, true);
                conta += contar(medio, i, false);
                conta += contar(baixo, i, true);

                // colocar naquela posição a quantidade de * adjacentes
                if (conta == 0) 
                {
                    linha += " ";    
                } else
                {
                    linha += conta;
                }
            }
            // Se for * adiciona na string
            else
            {   
                linha += "*";
            }
        }
        return linha;
    }
    /**
     * Conta a quantidade de * adjacentes à posição que estamos a trabalhar
     * @param st
     * @param pos
     * @param contar_debaixo_cima
     * @return 
     */
    public short contar(String st, int pos, boolean contar_debaixo_cima)
    {
        short conta = 0;
        int pos_aux = pos;
        // ver o que está à esquerda 
        if ( pos_aux > 0 ) // não está na posição zero, logo ve o que está no lado esquerdo
        {
            if ( st.charAt(pos_aux-1) == '*' ) 
            {
                conta++;
            }
        }
        if ( contar_debaixo_cima ) 
        {
            if ( st.charAt(pos_aux) == '*' ) 
            {
                conta++;    
            }    
        }
        // ver o que está à direita
        if ( pos_aux < (st.length()-1) ) 
        {
            if ( st.charAt(pos_aux+1) == '*' ) 
            {
                conta++;
            }
        }
        return conta;
    }
}