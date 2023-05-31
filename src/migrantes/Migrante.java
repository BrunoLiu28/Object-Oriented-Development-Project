package migrantes;

import java.util.ArrayList;
import java.util.List;

import outros.Requisicao;

/**
 * Classe migrante
 * 
 * @author Rodrigo Cancelinha fc56371
 * @author Bruno Liu fc56297
 *
 */
public class Migrante {

	private String nome;
	private int contacto;
	private int tipoDeMigrante; // 0 migrante sozinho; 1 migrante cabeca de casal; 2 migrante de agregado
								// familiar
	private List<Requisicao> requi;

	/**
	 * Cria um migrante com nome nome, contacto contacto e tipo de migrante
	 * tipoDeMigrante
	 * 
	 * @param nome           nomme do migrante
	 * @param contacto       contacto do migrante
	 * @param tipoDeMigrante tipo do migrante
	 */
	protected Migrante(String nome, int contacto, int tipoDeMigrante) {
		this.nome = nome;
		this.contacto = contacto;
		this.tipoDeMigrante = tipoDeMigrante;
		this.requi = new ArrayList<>();
	}

	/**
	 * adiciona uma requisição requiAtual à lista de requisições do migrante
	 * 
	 * @param requiAtual requisição a adicionar
	 */
	public void addRequi(Requisicao requiAtual) {
		this.requi.add(requiAtual);
	}

	/**
	 * Devolve o contacto do migrante
	 * 
	 * @return devolve o contacto do migrante
	 */
	public int getContacto() {
		return contacto;
	}

	/**
	 * Devolve o nome do migrante
	 * 
	 * @return Devolve o nome do migrante
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Devolve o tipo de migrante do migrante
	 * 
	 * @return devolve o tipo de migrante do migrante
	 */
	public int getTipoDeMigrante() {
		return tipoDeMigrante;
	}

}
