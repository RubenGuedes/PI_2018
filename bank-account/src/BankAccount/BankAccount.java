package BankAccount;
//Lucas Barrigo n41793

public class BankAccount {
	int salario;
	boolean disponivel;
	
	void abrir() {
		disponivel = true;
	}
	
	int getSalario() throws BankAccountActionInvalidException{
		checkDisponivel();
		return salario;
	}
	
	 
	

	synchronized void deposit(int amount) throws BankAccountActionInvalidException{
		checkDisponivel();
		checkAmount(amount);
		
		salario += amount;
		
	}
	
	synchronized void retirar(int amount) throws BankAccountActionInvalidException{
		
		checkDisponivel();
		checkAmount(amount);
		
		if(salario == 0) {
			throw new BankAccountActionInvalidException("A conta esta vazia!");
		}
		if (amount > salario) {
			throw new BankAccountActionInvalidException("Saldo indesponivel!");
		}

		salario -= amount;
		
	}
	private void checkAmount(int amount) throws BankAccountActionInvalidException {
		if (amount < 0) {
			throw new BankAccountActionInvalidException("Nao pode depositar/levantar saldo negativo");
		}
	}
		private void checkDisponivel() throws BankAccountActionInvalidException {
			if (!disponivel) {
				throw new BankAccountActionInvalidException("Conta fechada");
			}
		}
		void close(){
			disponivel = false;
		}


	}



