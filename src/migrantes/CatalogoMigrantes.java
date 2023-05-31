package migrantes;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe CatalogoMigrantes
 * 
 * @author Rodrigo Cancelinha fc56371
 * @author Bruno Liu fc56297
 *
 */
public class CatalogoMigrantes {
	private Map<Integer, Migrante> migrantes;

	/**
	 * Construtor da classe
	 */
	public CatalogoMigrantes() {
		migrantes = new HashMap<Integer, Migrante>();
	}

	/**
	 * Devolve true se o migrante com contacto contacto existe no catalogo
	 * 
	 * @param contacto contacto do migrante que queremos verifar se existe no
	 *                 catalogo
	 * @return Devolve true se o migrante existe no catalogo e falso caso contrário
	 */
	public boolean existsMigrante(Integer contacto) {
		return migrantes.containsKey(contacto);
	}

	/**
	 * Adiciona um migrante m ao catalogo
	 * 
	 * @param m migrante a adicionar
	 */
	public void addMigrante(Migrante m) {
		migrantes.put(m.getContacto(), m);
	}

	/**
	 * Devolve o migrante com contacto contacto
	 * 
	 * @param contacto contacto do migrante a devolver
	 * @requires existsMigrante(contacto)
	 * @return devolve o migrante com contacto contacto
	 */
	public Migrante getMigrante(Integer contacto) {
		return migrantes.get(contacto);
	}

}
