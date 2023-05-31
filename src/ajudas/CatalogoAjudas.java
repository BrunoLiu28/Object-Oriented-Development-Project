package ajudas;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe CatalogoAjudas
 * 
 * @author Rodrigo Cancelinha fc56371
 * @author Bruno Liu fc56297
 *
 */
public class CatalogoAjudas {
	private List<Ajuda> ajudas;

	/**
	 * Cria um catágolo de ajudas
	 */
	public CatalogoAjudas() {
		this.ajudas = new ArrayList<Ajuda>();
	}

	/**
	 * Adiciona uma ajuda ao catalogo de ajudas
	 * 
	 * @param aj ajuda a adicionar a ao catalogo
	 */
	public void addAjuda(Ajuda aj) {
		ajudas.add(aj);
	}

	/**
	 * Devolve a lista das ajudas
	 * 
	 * @return devolve a lista das ajudas
	 */
	public List<Ajuda> getAjudas() {
		return this.ajudas;
	}

	/**
	 * Ordena dependendo do tipo de ordenação definido em properties
	 * 
	 * @param aj lista a ordenar
	 */
	public void sortLista(List<Ajuda> aj) {
		this.ajudas = aj;
	}

	/**
	 * Remove uma ajuda da lista tendo em conta a sua posição na lista
	 * 
	 * @param posicao posição da ajuda a remover
	 */
	public void removerAjuda(int posicao) {
		ajudas.remove(posicao);

	}
}
