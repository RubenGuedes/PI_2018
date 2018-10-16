package teste;
import java.util.List;
//Lucas Barrigo n41793
public class BinarySearch <T extends Comparable<? super T>>{
	List<T> elementos;
	
	BinarySearch(List<T> elementos){
		this.elementos=elementos;
	}
		int indexOf(T target) {
			int menorIndice=0;
			int maiorIndice = elementos.size() -1;
			
			while(menorIndice<=maiorIndice) {
				int medioIndice = (menorIndice + maiorIndice)/2;
				int a = elementos.get(medioIndice).compareTo(target);
				if(a==0) {
					return medioIndice;
				}else if(a < 0) {
					menorIndice = medioIndice + 1;
				} else {
					maiorIndice = medioIndice - 1;
				}
			}
			
			return -1;
		}
}
