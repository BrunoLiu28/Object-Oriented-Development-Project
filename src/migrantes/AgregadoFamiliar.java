package migrantes;

/**
 * Classe AgregadoFamiliar
 * 
 * @author Rodrigo Cancelinha fc56371
 * @author Bruno Liu fc56297
 *
 */
public class AgregadoFamiliar {
	private MigranteDoAgregado migrantes[];
	private int atual;

	/**
	 * Cria um agregado familiar com numeroDeMembros número de membros
	 * 
	 * @param numeroDeMembros número de membros do agregado familiar
	 */
	public AgregadoFamiliar(int numeroDeMembros) {
		migrantes = new MigranteDoAgregado[numeroDeMembros];
		atual = 0;
	}

	/**
	 * Adiciona um membro m ao agregado
	 * 
	 * @param m membro a dicionar ao agregado
	 */
	public void adicionarMembro(MigranteDoAgregado m) {
		migrantes[atual] = m;
		atual++;
	}

	/**
	 * Devolve uma lista com MigranteDoAgregado existentes no agregado
	 * 
	 * @return devolve a lista de MigranteDoAgregado existentes no agregado
	 */
	public MigranteDoAgregado[] getMigrantes() {
		return migrantes;
	}
}
