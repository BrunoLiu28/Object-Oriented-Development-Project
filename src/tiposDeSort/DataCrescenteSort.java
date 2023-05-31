package tiposDeSort;

import java.util.ArrayList;
import java.util.List;

import ajudas.Ajuda;

/**
 * Classe DataCrescenteSort
 * 
 * @author Rodrigo Cancelinha fc56371
 * @author Bruno Liu fc56297
 *
 */
public class DataCrescenteSort implements MigrantMatcherSorterStrategy {

	/**
	 * Funcao que organiza uma lista de ajudas pelas datas de disponibilizacao
	 * 
	 * @return Retorna uma lista de ajudas organizada
	 */
	@Override
	public List<Ajuda> sort(List<Ajuda> aj) {
		List<Ajuda> aux = new ArrayList<Ajuda>();
		for (int i = 0; i < aj.size(); i++) {
			if (i == 0) {
				aux.add(aj.get(i));
			} else {
				int j = 0;
				boolean placed = false;

				while (j < aux.size() && !placed) {
					if (aj.get(i).getDate().getTime() <= aux.get(j).getDate().getTime()) {
						aux.add(j, aj.get(i));
						placed = true;
					} else if (j == aux.size() - 1) {
						aux.add(aj.get(i));
						placed = true;
					}
					j++;
				}
			}
		}
		return aux;
	}

}
