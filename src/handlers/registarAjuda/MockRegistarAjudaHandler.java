package handlers.registarAjuda;

import handlers.autentificacao.AutentificaHandler;

/**
 * Handler para lidar com o caso de uso de um voluntario registar uma ajuda
 * 
 * @author Rodrigo Cancelinha fc56371
 * @author Bruno Liu fc56297
 *
 */
public class MockRegistarAjudaHandler extends RegistarAjudaHandler{
	
	/**
	 * Funcao que chama o contrutor da classe pai
	 * @param a Autentifica handler
	 */
	public MockRegistarAjudaHandler(AutentificaHandler a) {
		super(a);
	}

	/**
	 * Fun��o que retorna o c�digo �nico
	 * @return c�digo �nico produzido
	 */
	public int getCodigoUnico() {
		return this.codigoUnico;
	}
}
