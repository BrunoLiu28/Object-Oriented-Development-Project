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
	 * Cria um alojamento com c�digo codigo, voluntario vol que � o voluntario que
	 * disponibilizou a ajuda, capacidade igual a capacidade e regi�o igual a regiao
	 * 
	 * @param codigo     o c�digo do alojamento
	 * @param vol        o volunt�rio que disponibilizou o alojamento
	 * @param capacidade a capacidade do alojamento
	 * @param regiao     a regi�o do alojamento
	 */
	public Alojamento(int codigo, Voluntario vol, int capacidade, String regiao) {
		super(codigo, vol);
		this.capacidade = capacidade;
		this.regiao = regiao;
	}

	/**
	 * Define a regi�o do objeto como regiao
	 * 
	 * @param regiao regi�o a definir
	 */
	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}

	/**
	 * Devolve a regi�o lo alojamento
	 * 
	 * @return devolve a regi�o do alojamento
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
