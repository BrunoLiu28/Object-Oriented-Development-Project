package regioes;

import java.util.HashMap;
import java.util.Map;

import ajudas.Ajuda;
import ajudas.Item;

/**
 * Catalogo de Regioes
 * 
 * @author Rodrigo Cancelinha fc56371
 * @author Bruno Liu fc56297
 *
 */
public class CatalogoRegiao {
	private Map<String, Regiao> regioes;
	private static final String[] distritos = { "aveiro", "beja", "braga", "bragan�a", "castelo branco", "coimbra",
			"evora", "faro", "guarda", "leiria", "lisboa", "portalegre", "porto", "santarem", "setubal",
			"viana do castelo", "vila real", "viseu" };

	/**
	 * Cria um catalogo de com regi�es correspondentes as presentes na string
	 * registos
	 */
	public CatalogoRegiao() {
		regioes = new HashMap<String, Regiao>();
		for (int i = 0; i < distritos.length; i++) {
			regioes.put(distritos[i].toLowerCase().trim(), (new Regiao(distritos[i])));
		}
	}

	/**
	 * Devolve uma regi�o presente no catalogo caso esta exista
	 * 
	 * @param key chave que esta ligada � regi�o
	 * @return Devolve uma regi�o presente no catalogo caso esta exista caso
	 *         contr�rio devolve null
	 */
	public Regiao getRegioes(String key) {
		if (existsRegiao(key)) {
			return regioes.get(key);
		}
		return null;
	}

	/**
	 * Verifica se uma regi�o existe no catalogo
	 * 
	 * @param nomeDaRegiao nome da regi�o a verificar
	 * @return devolve true caso a regi�o exista e falso caso contr�rio
	 */
	public boolean existsRegiao(String nomeDaRegiao) {
		return regioes.containsKey(nomeDaRegiao.toLowerCase().trim());
	}

	/**
	 * Imprime a lista de regi�es presentes no catalogo
	 */
	public void printListaDeRegioes() {
		System.out.println("Imprimindo lista de regioes");
		for (String s : distritos) {
			System.out.println(s.substring(0, 1).toUpperCase() + s.substring(1));
		}
	}

	/**
	 * Imprime a lista de ajudas existentes numa regi�o
	 * 
	 * @param regiao regi�o sobre a qual se v�o imprimir as ajudas
	 * @return true caso existam ajudas na regi�o e false caso contr�rio
	 */
	public boolean printListaDeAjudas(String regiao) {
		Regiao aux = regioes.get(regiao);
		if (aux.getListaDeAjudas().size() == 0) {
			System.out.println("A regiao escolhida nao tem nenhuma ajuda registada");
			return false;
		}

		int i = 0;
		for (Ajuda a : aux.getListaDeAjudas()) {
			if (a.getTipoDeAjuda().equals("Item")) {
				System.out.println(
						i + "- Tipo de ajuda:" + a.getTipoDeAjuda() + " Descricao: " + ((Item) a).getDescricao());
			} else {
				System.out.println(i + "- Tipo de ajuda:" + a.getTipoDeAjuda() + " Capacidade do alojamento: "
						+ ((Item) a).getDescricao());
			}
			System.out.println(i + "- Tipo de ajuda:" + a.getTipoDeAjuda());
			i++;
		}
		return true;
	}

	/**
	 * Adiciona uma determinada aj ajuda a todas as regi�es
	 * 
	 * @param aj Ajuda a adicionar
	 */
	public void addAjudaTodasRegioes(Ajuda aj) {
		for (int i = 0; i < distritos.length; i++) {
			regioes.get(distritos[i].toLowerCase().trim()).addAjuda(aj);
			sortRegiao(distritos[i].toLowerCase().trim());
		}

	}

	/**
	 * Adiciona uma determinada aj ajuda a uma regi�o regiao
	 * 
	 * @param aj     Ajuda a adicionar
	 * @param regiao Regi�o � qual se vai adicionar
	 */
	public void addAjudaUmaRegiao(Ajuda aj, String regiao) {
		regioes.get(regiao.toLowerCase().trim()).addAjuda(aj);
		sortRegiao(regiao.toLowerCase().trim());
	}

	/**
	 * Remove uma ajuda aj de todas as regi�es
	 * 
	 * @param aj Ajuda a remover
	 */
	public void removeAjudaTodasRegioes(Ajuda aj) {
		for (int i = 0; i < distritos.length; i++) {
			regioes.get(distritos[i].toLowerCase().trim()).removeAjuda(aj);
		}
	}

	/**
	 * Remove uma ajuda aj de uma regi�o regiao
	 * 
	 * @param aj     Ajuda a remover
	 * @param regiao Regi�o � qual se vai remover a ajuda
	 */
	public void removeAjudaUmaRegiao(Ajuda aj, String regiao) {
		regioes.get(regiao.toLowerCase().trim()).removeAjuda(aj);
	}

	/**
	 * Ordena dependendo do tipo de ordena��o definido em properties
	 * 
	 * @param regiao Regiao para ordenar a lista de ajudas
	 */
	public void sortRegiao(String regiao) {
		regioes.get(regiao.toLowerCase().trim()).sortListaDeAjudas();
	}
}
