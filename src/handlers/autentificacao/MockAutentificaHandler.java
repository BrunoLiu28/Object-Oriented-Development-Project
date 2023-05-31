package handlers.autentificacao;

import voluntarios.CatalogoVoluntario;
/**
 * Classe que é utilizada para a realização dos testes
 * 
 * @author Rodrigo Cancelinha fc56371
 * @author Bruno Liu fc56297
 *
 */
public class MockAutentificaHandler extends AutentificaHandler{
	
	/**
	 * Devolve o catalogo de Voluntarios
	 * @return o catalogo de Voluntarios
	 */
	public CatalogoVoluntario getVoluntarios() {
		return voluntarios;
	}
}
