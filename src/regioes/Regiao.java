package regioes;

import java.util.ArrayList;
import java.util.List;

import ajudas.Ajuda;
import ajudas.CatalogoAjudas;
import observador.Observer;
import observador.Sujeito;
import outros.Configuration;
import tiposDeSort.DataCrescenteSort;
import tiposDeSort.MigrantMatcherSorterStrategy;

/**
 * Classe Região
 * 
 * @author Rodrigo Cancelinha fc56371
 * @author Bruno Liu fc56297
 *
 */
public class Regiao implements Sujeito {
	private String nomeDaRegiao;
	private CatalogoAjudas ajudas;
	private MigrantMatcherSorterStrategy sorter;
	private ArrayList<Observer> observers;

	/**
	 * Cria uma Região nova
	 * 
	 * @param regiao nome da região
	 */
	public Regiao(String regiao) {
		this.nomeDaRegiao = regiao;
		this.ajudas = new CatalogoAjudas();
		Configuration config = Configuration.getInstance();
		this.sorter = config.getInstanceOfClass("sort", new DataCrescenteSort());
		this.observers = new ArrayList<>();
	}

	/**
	 * Adiciona uma ajuda á lista de ajudas da região
	 * 
	 * @param ajuda a ajuda qua vai ser adicionada
	 */
	public void addAjuda(Ajuda ajuda) {
		ajudas.addAjuda(ajuda);
		alertarObservadores();
	}

	/**
	 * Devolve o nome da região.
	 * 
	 * @return devolve o nome da região.
	 */
	public String getNomeDaRegiao() {
		return nomeDaRegiao;
	}

	/**
	 * Devolve a lista de ajudas da região.
	 * 
	 * @return devolve a lista de ajudas da região.
	 */
	public List<Ajuda> getListaDeAjudas() {
		return ajudas.getAjudas();
	}

	/**
	 * Ordena dependendo do tipo de ordenação definido em properties.
	 */
	public void sortListaDeAjudas() {
		ajudas.sortLista(sorter.sort(ajudas.getAjudas()));

	}

	/**
	 * Pesquisa na lista de ajudas de uma região se existe uma determinada ajuda com
	 * um numeroDaAjuda.
	 * 
	 * @param numeroDaAjuda número da ajuda a pesquisar
	 * @return true se a ajuda existe e falso caso contrário
	 */
	public boolean hasAjudaByNumber(int numeroDaAjuda) {
		return (numeroDaAjuda < ajudas.getAjudas().size()) && (numeroDaAjuda >= 0);
	}

	/**
	 * Devolve uma ajuda com um determinado numeroDeAjuda.
	 * 
	 * @param numeroDaAjuda número da ajuda a devolver.
	 * @requires {@code hasAjudaByNumber() == true0}
	 * @return Devolve a ajuda consoante o seu indice.
	 */
	public Ajuda getAjudaByNumber(int numeroDaAjuda) {
		return ajudas.getAjudas().get(numeroDaAjuda);
	}

	/**
	 * Remove uma determinada ajuda da lista de ajudas da região.
	 * 
	 * @param aj ajuda a remover da lista de ajudas.
	 */
	public void removeAjuda(Ajuda aj) {
		int posicao = -1;
		for (int i = 0; i < ajudas.getAjudas().size(); i++) {
			if (ajudas.getAjudas().get(i).getAjudaId() == aj.getAjudaId()) {
				posicao = i;
			}
		}
		if (posicao == -1) {
			// Nao faz nada
		} else {
			ajudas.removerAjuda(posicao);
		}

	}

	/**
	 * Regista um observer à lista de observers da região.
	 */
	public void registarObservador(Observer newObserver) {
		observers.add(newObserver);
	}

	/**
	 * Remove um observer da lista de observers da região.
	 */
	public void esquecerObservador(Observer deleteObserver) {
		int observerIndex = observers.indexOf(deleteObserver);

		observers.remove(observerIndex);
	}

	/**
	 * Alerta envia uma alerta aos observadores existentes na lista de observers da
	 * região.
	 */
	public void alertarObservadores() {
		for (Observer observer : observers) {
			observer.sendSMSToMigrante();
		}
	}
}
