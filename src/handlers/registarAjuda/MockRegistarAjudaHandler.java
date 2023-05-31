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
	 * Função que retorna o código único
	 * @return código único produzido
	 */
	public int getCodigoUnico() {
		return this.codigoUnico;
	}
}
