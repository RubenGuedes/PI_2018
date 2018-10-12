//Lucas Barrigo n41793
public class QueenAttackCalculator {
	BoardCoordinate pos1;
	BoardCoordinate pos2;
	
	QueenAttackCalculator(BoardCoordinate pos1, BoardCoordinate pos2){
		if(pos1==null || pos2==null) {
			throw new  IllegalArgumentException("Deve inserir uma posicao para para ambas as rainhas");
		}
		
		if(pos1.equals(pos2)) {
			throw new IllegalArgumentException("As rainhas nao devem estar na mesma posicao");
		}
		this.pos1 = pos1;
		this.pos2 = pos2;
		
		
	}
	boolean RainhaPodeAtacarOutra() {
		return pos1.fila == pos2.fila || pos1.coluna == pos2.coluna || pos1.fila + pos1.coluna == pos2.fila + pos2.coluna
				|| pos1.coluna - pos1.coluna == pos2.fila - pos2.coluna; //pois a rainha pode atacar em linha reta ou nas diagonais
}
}