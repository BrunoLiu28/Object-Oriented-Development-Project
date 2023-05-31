package voluntarios;

/**
 * Classe Voluntario
 * 
 * @author Rodrigo Cancelinha fc56371
 * @author Bruno Liu fc56297
 *
 */
public class Voluntario {
	private int contacto;

	/**
	 * Cria um voluntário com o contacto a 0
	 */
	public Voluntario() {
		contacto = 0;
	}

	/**
	 * Devolve o contacto do voluntário
	 * 
	 * @return devolve o contacto do voluntário
	 */
	public int getContacto() {
		return contacto;
	}

	/**
	 * Define o contacto do voluntário como contacto
	 * 
	 * @param contacto o contacto a definir
	 */
	public void setContacto(int contacto) {
		this.contacto = contacto;
	}

}
