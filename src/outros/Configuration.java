package outros;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * Classe Configuration.
 * 
 * @author Rodrigo Cancelinha fc56371
 * @author Bruno Liu fc56297
 *
 */
public class Configuration {

	/**
	 * Unica instancia de configuration.
	 */
	private static Configuration INSTANCE = new Configuration();

	private Properties props = new Properties();

	/**
	 * 
	 * Funcao que retorna a unica instancia existente.
	 * 
	 * @return retorna a unica instancia existente.
	 */
	public static Configuration getInstance() {
		return INSTANCE;
	}

	/**
	 * Contrutor privado para que nao seja possivel criar varios objetos.
	 */
	private Configuration() {
		try {
			props.load(new FileInputStream("default.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Funcao que devolve um valor associado a uma key.
	 * 
	 * @param chave        Chave De referencia.
	 * @param defaultValue Valor default.
	 * @return Retorna o valor associado a uma key, caso nao encontre devolve o
	 *         valor default.
	 */
	public <T> T getInstanceOfClass(String chave, T defaultValue) {
		String klassName = (String) props.get(chave);
		if (klassName == null) {
			return defaultValue;
		}

		try {
			@SuppressWarnings("unchecked")
			Class<T> klass = (Class<T>) Class.forName(klassName);
			Constructor<T> c = klass.getConstructor();
			return c.newInstance();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return defaultValue;
	}
}
