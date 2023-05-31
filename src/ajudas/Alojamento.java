package ajudas;

import voluntarios.Voluntario;

/**
 * Classe Alojamento
 * 
 * @author Rodrigo Cancelinha fc56371
 * @author Bruno Liu fc56297
 *
 */
public class Alojamento extends Ajuda {
	private int capacidade = 0;
	private String regiao; // FAZER ENUM PARA REGIAO????

	/**
	 * Cria um alojamento com código codigo, voluntario vol que é o voluntario que
	 * disponibilizou a ajuda, capacidade igual a capacidade e região igual a regiao
	 * 
	 * @param codigo     o código do alojamento
	 * @param vol        o voluntário que disponibilizou o alojamento
	 * @param capacidade a capacidade do alojamento
	 * @param regiao     a região do alojamento
	 */
	public Alojamento(int codigo, Voluntario vol, int capacidade, String regiao) {
		super(codigo, vol);
		this.capacidade = capacidade;
		this.regiao = regiao;
	}

	/**
	 * Define a região do objeto como regiao
	 * 
	 * @param regiao região a definir
	 */
	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}

	/**
	 * Devolve a região lo alojamento
	 * 
	 * @return devolve a região do alojamento
	 */
	public String getRegiao() {
		return regiao;
	}

	/**
	 * Devolve a capacidade do alojamento
	 * 
	 * @return devolve a capacidade do alojamento
	 */
	public int getCapacidade() {
		return capacidade;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alojamento aux = (Alojamento) obj;
		return getAjudaId() == aux.getAjudaId() && getDate() == aux.getDate() && getCapacidade() == aux.getCapacidade()
				&& getRegiao() == aux.getRegiao();

	}
}
