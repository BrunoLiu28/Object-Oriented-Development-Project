package migrantes;

/**
 * Classe MigranteCabecaDeCasal
 * 
 * @author Rodrigo Cancelinha fc56371
 * @author Bruno Liu fc56297
 *
 */
public class MigranteCabecaDeCasal extends Migrante {

	private int numeroDeMembros;
	private AgregadoFamiliar ag;

	/**
	 * Cria um cabe�a de casal com nome nome, contacto contacto e numero de membros
	 * numeroDeMembros
	 * 
	 * @param nome            nome do migrante cabe�a de casal
	 * @param contacto        contacto do migrante
	 * @param numeroDeMembros n�mero de migrantes no agregado
	 */
	public MigranteCabecaDeCasal(String nome, int contacto, int numeroDeMembros) {
		super(nome, contacto, 1);
		this.numeroDeMembros = numeroDeMembros;
		ag = new AgregadoFamiliar(numeroDeMembros);
	}

	/**
	 * Adiciona um membro ao agregado do cabe�a de casal
	 * 
	 * @param nome nome do MigranteDoAgregado a adicionar ao agregado
	 */
	public void adicionarMembro(String nome) {
		ag.adicionarMembro(new MigranteDoAgregado(nome));
	}

	/**
	 * Devolve o agregado familiar do cabe�a de casal
	 * 
	 * @return devolve o agregado familiar do cabe�a de casal
	 */
	public AgregadoFamiliar getAgregado() {
		return ag;
	}

	/**
	 * Devolve o numero de membros do agregado familiar do cabe�a de casal
	 * 
	 * @return devolve o numero de membros do agregado familiar do cabe�a de casal
	 */
	public int getNumeroDeMembros() {
		return numeroDeMembros;
	}

}
