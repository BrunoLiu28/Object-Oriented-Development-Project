package observador;
/**
 * Interface Sujeito
 * 
 * @author Rodrigo Cancelinha fc56371
 * @author Bruno Liu fc56297
 *
 */
public interface Sujeito {
	
	public void registarObservador(Observer o);
	public void esquecerObservador(Observer o);
	public void alertarObservadores();
	
}