package outros;

import java.util.ArrayList;

import ajudas.Ajuda;

/**
 * Classe requisi��o
 * 
 * @author Rodrigo Cancelinha fc56371
 * @author Bruno Liu fc56297
 *
 */
public class Requisicao {
	private ArrayList<Ajuda> ajudas;

	/**
	 * Contrutor da classe.
	 */
	public Requisicao() {
		ajudas = new ArrayList<>();
	}

	/**
	 * Adiciona uma ajuda � lista de ajudas da requisicao.
	 * 
	 * @param ajuda Ajuda para ser adicionada � requisicao
	 */
	public void addAjuda(Ajuda ajuda) {
		ajudas.add(ajuda);
	}

	/**
	 * Retorna lista de ajudas de uma regiao.
	 * 
	 * @return Lista de ajudas de uma regiao.
	 */
	public ArrayList<Ajuda> getAjudas() {
		return ajudas;
	}
}
