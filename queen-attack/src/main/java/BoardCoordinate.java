
public class BoardCoordinate {
	int fila;
	int coluna;
	BoardCoordinate(int fila, int coluna) {
		if(fila>7) {
			throw new IllegalArgumentException("A coordenada tem de ser iferior ou igual a 7.");
		}
		if(coluna>7) {
			throw new IllegalArgumentException("A coordenada tem de ser iferior ou igual a 7.");
		}
		if(fila<0) {
			throw new IllegalArgumentException("A coordenada tem de ser superior a 0.");
		}
		if(coluna<0) {
			throw new IllegalArgumentException("A coordenada tem de ser superior a 0.");
		}
		this.fila = fila;
		this.coluna=coluna;
		
	}
	@Override
	public boolean equals(Object obj) {
		BoardCoordinate other = (BoardCoordinate) obj;
		return fila == other.fila && coluna == other.coluna;
	}

}
