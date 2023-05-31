package handlers.autentificacao;

import migrantes.CatalogoMigrantes;
import migrantes.Migrante;
import migrantes.MigranteCabecaDeCasal;
import migrantes.MigranteSozinho;
import voluntarios.CatalogoVoluntario;
import voluntarios.Voluntario;

/**
 * Handler para lidar com o caso de uso de autentificar um utilizador
 * 
 * @author Rodrigo Cancelinha fc56371
 * @author Bruno Liu fc56297
 *
 */
public class AutentificaHandler {
	protected CatalogoVoluntario voluntarios;
	protected CatalogoMigrantes migrantes;
	private Voluntario voluntarioCorrente;
	private Migrante migranteCorrente;

	/**
	 * Contrutor da classe
	 */
	public AutentificaHandler() {
		this.voluntarios = new CatalogoVoluntario();
		this.migrantes = new CatalogoMigrantes();
		this.voluntarioCorrente = new Voluntario();
		this.migranteCorrente = null;
	}

	/**
	 * Fumcao que cria um voluntario e adiciona-o à lisda de voluntarios ou se caso
	 * o voluntario exista vai buscar o voluntario atualizando o voluntarioCorrente.
	 * 
	 * @param contacto
	 */
	public void identificarVoluntario(int contacto) {
		if (voluntarios.checkVoluntarios(contacto)) {
			voluntarioCorrente = voluntarios.getVoluntario(contacto);
		} else {
			voluntarioCorrente = new Voluntario();
			voluntarioCorrente.setContacto(contacto);
			voluntarios.addVoluntarios(voluntarioCorrente);
		}
	}

	/**
	 * Função que retorna o voluntário corrente.
	 * 
	 * @requires{@code voluntarioCorrente != null}
	 * @return O voluntario corrente.
	 */
	public Voluntario getVolunCorrente() {
		return this.voluntarioCorrente;
	}

	/**
	 * Função que cria um migrante individual, caso ja exista devolve o existente
	 * 
	 * @param nome     Nome do migrante
	 * @param contacto Contacto do migrante
	 */
	public void indicarRegistoIndividual(String nome, int contacto) {
		if (migrantes.existsMigrante(contacto)) {
			this.migranteCorrente = migrantes.getMigrante(contacto);
		} else {
			this.migranteCorrente = new MigranteSozinho(nome, contacto);
			migrantes.addMigrante(migranteCorrente);
		}
	}

	/**
	 * Função que cria um migrante que pretende registar com a familia, caso ja
	 * exista devolve o existente
	 * 
	 * @param nome            Nome do cabeca de casal
	 * @param contacto        contacto da cabeça de casal
	 * @param numeroDeMembros numero de membros que o agregado tem
	 */
	public void indicarCabecaDeCasal(String nome, int contacto, int numeroDeMembros) {
		if (migrantes.existsMigrante(contacto)) {
			this.migranteCorrente = migrantes.getMigrante(contacto);
		} else {
			this.migranteCorrente = new MigranteCabecaDeCasal(nome, contacto, numeroDeMembros);
			migrantes.addMigrante(migranteCorrente);
		}
	}

	/**
	 * Funcao que serve para adicionar outros membros ao agregado familiar
	 * 
	 * @param nome Nome do elemento
	 */
	public void indicarOutroMembro(String nome) {
		((MigranteCabecaDeCasal) migranteCorrente).adicionarMembro(nome);
	}

	/**
	 * Função que retorna o migrante corrente.
	 * 
	 * @requires{@code migranteCorrente != null}
	 * @return O migrante corrente.
	 */
	public Migrante getMigranteCorrente() {
		return this.migranteCorrente;
	}
}
