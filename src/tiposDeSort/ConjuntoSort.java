package tiposDeSort;

import java.util.ArrayList;
import java.util.List;

import ajudas.Ajuda;

/**
 * Classe ConjuntoSort
 * 
 * @author Rodrigo Cancelinha fc56371
 * @author Bruno Liu fc56297
 *
 */
public class ConjuntoSort implements MigrantMatcherSorterStrategy {

	/**
	 * Funcao que organiza uma lista de ajudas, vindo primeiro os alojamentos e
	 * depois os items.
	 * 
	 * @return Retorna uma lista de ajudas organizada
	 */
	@Override
	public List<Ajuda> sort(List<Ajuda> aj) {
		List<Ajuda> aux = new ArrayList<>();
		int numeroDeAlojamentos = 0;
		for (int i = 0; i < aj.size(); i++) {
			if (aj.get(i).getTipoDeAjuda().equals("alojamento")) {
				aux.add(0, aj.get(i));
				numeroDeAlojamentos++;
			} else {
				aux.add(numeroDeAlojamentos, aj.get(i));
			}

		}
		return aux;
	}

}
