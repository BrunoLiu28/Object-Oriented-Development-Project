package ajudas;

import java.util.Date;

import com.pidgeonsmssender.sdk.PidgeonSMSSender;

import voluntarios.Voluntario;

/**
 * Classe Ajuda
 * 
 * @author Rodrigo Cancelinha fc56371
 * @author Bruno Liu fc56297
 *
 */
public class Ajuda {
	private int ajudaId;
	private Voluntario voluntario;
	private Date data;

	/**
	 * Cria uma ajuda nova com codigo codigo e voluntário vol.
	 * 
	 * @param codigo o codigo da ajuda.
	 * @param vol    o voluntario que disponibilizou a ajuda.
	 */
	protected Ajuda(int codigo, Voluntario vol) {
		this.ajudaId = codigo;
		this.voluntario = vol;
		this.data = new Date();
	}

	/**
	 * Devolve o ID da ajuda.
	 * 
	 * @return devolve o ID da ajuda.
	 */
	public int getAjudaId() {
		return ajudaId;
	}

	/**
	 * Devolve o voluntário que disponibilizou a ajuda.
	 * 
	 * @return devolve o voluntário que disponibilizou a ajuda.
	 */
	public Voluntario getVoluntario() {
		return voluntario;
	}

	/**
	 * Envia um sms ao voluntario que disponibilizou a ajuda.
	 */
	public void sendSMS() {
		PidgeonSMSSender sender = new PidgeonSMSSender();
		sender.send(String.valueOf(voluntario.getContacto()), "Uma das suas ajudas foi requesitada");
	}

	/**
	 * Define a data d como a data da ajuda.
	 */
	public void setDate(Date d) {
		this.data = d;
	}

	/**
	 * Devolve a data da ajuda.
	 * 
	 * @return devolve a data da ajuda.
	 */
	public Date getDate() {
		return data;
	}

	/**
	 * Devolve o tipo da ajuda.
	 * 
	 * @return devolve o tipo da ajuda.
	 */
	public String getTipoDeAjuda() {
		return getClass().getName().substring(getClass().getName().indexOf(".") + 1).toLowerCase();
	}

}
