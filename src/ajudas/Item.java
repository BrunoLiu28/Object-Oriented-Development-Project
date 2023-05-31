package ajudas;

import voluntarios.Voluntario;

/**
 * Classe Item
 * 
 * @author Rodrigo Cancelinha fc56371
 * @author Bruno Liu fc56297
 *
 */
public class Item extends Ajuda {

	private String descricao;

	/**
	 * Cria um item com código codigo, voluntario vol que é o voluntario que
	 * disponibilizou a ajuda, descrição igual a descricao
	 * 
	 * @param codigo    o código do item
	 * @param vol       o voluntário que disponibilizou o item
	 * @param descricao a descrição do item
	 */
	public Item(int codigo, Voluntario vol, String descricao) {
		super(codigo, vol);
		this.descricao = descricao;
	}

	/***
	 * Devolve a descrição do item
	 * 
	 * @return devolve a descrição do item
	 */
	public String getDescricao() {
		return descricao;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item aux = (Item) obj;
		return getAjudaId() == aux.getAjudaId() && getDate() == aux.getDate() && getDescricao() == aux.getDescricao();

	}

}
