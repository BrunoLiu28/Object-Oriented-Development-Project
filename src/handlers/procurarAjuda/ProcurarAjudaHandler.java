package handlers.procurarAjuda;

import ajudas.Ajuda;
import handlers.autentificacao.AutentificaHandler;
import handlers.registarAjuda.RegistarAjudaHandler;
import migrantes.Migrante;
import observador.RegionObserver;
import outros.Requisicao;

/**
 * Handler para lidar com o caso de uso de um migrante procurar uma ajuda
 * 
 * @author Rodrigo Cancelinha fc56371
 * @author Bruno Liu fc56297
 *
 */
public class ProcurarAjudaHandler {
	private AutentificaHandler autentificaHandler;
	private Migrante migranteAtual;
	private RegistarAjudaHandler rAHandler;
	private String regiaoAtual;
	private Requisicao requiAtual;

	/**
	 * Construtor da Classe
	 * 
	 * @param registarAjuda � passado o handler registar ajuda
	 * @param a             � passado o handler autentifica
	 */
	public ProcurarAjudaHandler(RegistarAjudaHandler registarAjuda, AutentificaHandler a) {
		this.autentificaHandler = a;
		this.rAHandler = registarAjuda;
		migranteAtual = null;
		this.regiaoAtual = "";
		this.requiAtual = new Requisicao();
	}

	/**
	 * Define o migrante atual atrav�s do migrante atual em autentificarHandler
	 */
	public void registoMigrante() {
		this.migranteAtual = autentificaHandler.getMigranteCorrente();
	}

	/**
	 * Returna imprimido a lista de regioes existentes
	 */
	public void pedirListaRegioes() {
		rAHandler.listaDeRegioes();
	}

	/**
	 * Define o regiao atual atrav�s de regiao
	 * 
	 * @param regiao Nome da regiao que o migrante escolheu
	 */
	public void indicarRegiao(String regiao) {
		this.regiaoAtual = regiao;
	}

	/**
	 * Retorna imprimido a lista de ajudas de uma certa regiao
	 * 
	 * @return true se a regiao possuir ajudas, false se a regiao nao possuir ajudas
	 */
	public boolean listaDeAjudasDaRegiao() {
		return rAHandler.listaDeAjudasRegiao(regiaoAtual);
	}

	/**
	 * Fun��o que verifica se o indice de ajuda � v�lido
	 * 
	 * @param numeroDaAjuda indice da ajuda
	 * @return true se o indice de ajuda for valido, falso caso contrario
	 */
	public boolean hasAjuda(int numeroDaAjuda) {
		return rAHandler.regiaoHasAjuda(regiaoAtual, numeroDaAjuda);

	}

	/**
	 * Funcao que adiciona a ajuda escolhida � requiAtual
	 * 
	 * @param ajuda Indice da ajuda escolhida
	 * @requires {hasAjuda(ajuda) == true}
	 */
	public void escolherAjuda(int ajuda) {
		requiAtual.addAjuda(rAHandler.getRegiao(regiaoAtual).getAjudaByNumber(ajuda));
	}

	/**
	 * Associa a requiAtual ao migrante e remove as ajudas que estao na requiAtual
	 * das regioes
	 */
	public void mandarConfirmacao() {
		this.migranteAtual.addRequi(requiAtual);
		for (Ajuda aj : requiAtual.getAjudas()) {
			rAHandler.removeAjuda(aj);
		}
	}

	/**
	 * O migrante � posto numa lista onde recebe uma mensagem caso a regiao que
	 * tenha selecionado nao possuisse nenhuma ajuda
	 */
	public void requisitarNotificacao() {
		new RegionObserver(rAHandler.getRegiao(regiaoAtual), migranteAtual);
	}

}
