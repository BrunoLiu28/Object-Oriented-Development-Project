package tiposDeSort;

import java.util.List;

import ajudas.Ajuda;

/**
 * Interface para as diferentes maneira de organizar as ajudas
 * 
 * @author Rodrigo Cancelinha fc56371
 * @author Bruno Liu fc56297
 *
 */
public interface MigrantMatcherSorterStrategy {
	public List<Ajuda> sort(List<Ajuda> aj);
}
