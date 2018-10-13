package passar;
//Lucas Barrigo n41793

public class CircularBuffer <T> {
	
	T[] buffer;
	int inicio;
	int fim;
	int length;
	
	
	@SuppressWarnings("unchecked")
	CircularBuffer(){
		int size=0;
		buffer =((T[]) new Object[size]);
		
		limpar();
	}
	
	T ler() throws BufferIOException{
		if(vazio()) {
			throw new BufferIOException("O buffer esta Vazio!");
			
		}
		
		T results = buffer[inicio];
		inicio=(inicio+1) % buffer.length;
		length--;
		return results;
	}
	
	void escrever(T element) throws BufferIOException{
		if(cheio()) {
			
			throw new BufferIOException("O buffer esta Cheio!");
			
		}
		buffer[fim]=element;
		fim=(fim+1) % buffer.length;
		length++;
	}
	void overwrite(T element) throws BufferIOException {
		if (cheio()) {
			ler();
		}
		escrever(element);
	}
	void limpar() {
		inicio = 0;
		fim = 0;
		length = 0;
	}

	boolean vazio() {
		return length == 0;
	}

	boolean cheio() {
		return length > 0 && inicio == fim;
	}
	
	
}
