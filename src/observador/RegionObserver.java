package observador;

import com.pidgeonsmssender.sdk.PidgeonSMSSender;

import migrantes.Migrante;
import regioes.Regiao;

/**
 * Classe RegionObserver
 * 
 * @author Rodrigo Cancelinha fc56371
 * @author Bruno Liu fc56297
 *
 */
public class RegionObserver implements Observer {

	private Regiao regiao;
	private Migrante m;

	/**
	 * Construtor da classe
	 */
	public RegionObserver(Regiao r, Migrante m) {
		this.regiao = r;
		this.m = m;
		r.registarObservador(this);
	}

	/**
	 * Funcao que envia uma notificacao ao migrante quando uma ajuda é adicionada a
	 * uma regiao que nao possuia ajudas
	 */
	public void sendSMSToMigrante() {
		PidgeonSMSSender sender = new PidgeonSMSSender();
		StringBuilder sb = new StringBuilder();
		sb.append(regiao.getNomeDaRegiao() + " possui novas ajudas registadas");
		sender.send(String.valueOf(m.getContacto()), sb.toString());
	}

}
