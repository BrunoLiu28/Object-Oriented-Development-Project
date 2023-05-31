package voluntarios;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe CatalogoVoluntario
 * 
 * @author Rodrigo Cancelinha fc56371
 * @author Bruno Liu fc56297
 *
 */
public class CatalogoVoluntario {
	private Map<Integer, Voluntario> voluntarios;

	/**
	 * Contrutor da classe
	 */
	public CatalogoVoluntario() {
		this.voluntarios = new HashMap<>();
	}

	/**
	 * Devolve o voluntario com o correspondente contacto.
	 * 
	 * @param contacto contacto do voluntario.
	 * @return O voluntario com o correspondente contacto.
	 */
	public Voluntario getVoluntario(Integer contacto) {
		return voluntarios.get(contacto);
	}

	/**
	 * Caso nao exista ainda, adiciona um voluntario ao mapa de voluntarios
	 * 
	 * @param v Voluntario a adicionar
	 */
	public void addVoluntarios(Voluntario v) {
		if (checkVoluntarios(v.getContacto())) {
			// nao faz nada
		} else {
			this.voluntarios.put(v.getContacto(), v);
		}
	}

	/**
	 * Verifica se um voluntario existe no mapa de voluntarios
	 * 
	 * @param contacto contacto do voluntario a verificar
	 * @return true se o voluntario existir, false caso contrario
	 */
	public boolean checkVoluntarios(Integer contacto) {
		return voluntarios.containsKey(contacto);
	}

	/**
	 * Retorna o mapa de voluntarios
	 * 
	 * @return O mapa de voluntarios
	 */
	public Map<Integer, Voluntario> getVoluntarios() {
		return this.voluntarios;
	}
}
