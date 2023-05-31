package handlers.registarAjuda;

import java.util.Date;
import java.util.Random;

import com.pidgeonsmssender.sdk.PidgeonSMSSender;

import ajudas.Ajuda;
import ajudas.Alojamento;
import ajudas.Item;
import handlers.autentificacao.AutentificaHandler;
import regioes.CatalogoRegiao;
import regioes.Regiao;
import voluntarios.Voluntario;

/**
 * Handler para lidar com o caso de uso de um voluntario registar uma ajuda
 * 
 * @author Rodrigo Cancelinha fc56371
 * @author Bruno Liu fc56297
 *
 */
public class RegistarAjudaHandler {
	private AutentificaHandler autentificaHandler;
	private CatalogoRegiao regioes;
	private Ajuda aj;
	private int ajudaID;
	private Voluntario voluntarioAtual;
	protected int codigoUnico;

	/**
	 * Construtor da Classe
	 * 
	 * @param a é passado o handler autentifica
	 */
	public RegistarAjudaHandler(AutentificaHandler a) {
		this.autentificaHandler = a;
		this.ajudaID = 0;
		this.regioes = new CatalogoRegiao();
		this.voluntarioAtual = new Voluntario();
		this.codigoUnico = 0;
	}

	/**
	 * Define o voluntario atual através do voluntario atual em autentificarHandler
	 */
	public void registoVoluntario() {
		this.voluntarioAtual = autentificaHandler.getVolunCorrente();
	}

	/**
	 * Cria uma ajuda nova, um alojamento.
	 * 
	 * @param capacidade capacidade do alojamento
	 */
	public void indicarCapacidade(int capacidade) {
		aj = new Alojamento(ajudaID, voluntarioAtual, capacidade, "");

	}

	/**
	 * Imprime a lista de regioes
	 */
	public void listaDeRegioes() {
		regioes.printListaDeRegioes();
	}

	/**
	 * Imprime a lista de ajudas de uma certa regiao.
	 * 
	 * @param regiao Nome da regiao.
	 * @requires {hasRegiao(regiao) == true}
	 * @return true se a regiao possuir ajudas, false se a regiao nao possuir
	 *         ajudas.
	 */
	public boolean listaDeAjudasRegiao(String regiao) {
		return regioes.printListaDeAjudas(regiao);
	}

	/**
	 * Função que verifica se existe a região que o utilizador inseriu
	 * 
	 * @param regiao Região do programa
	 * @return true se a região existir, false caso contrario
	 */
	public boolean hasRegiao(String regiao) {
		return regioes.existsRegiao(regiao);
	}

	/**
	 * Define a regiao de uma ajuda que é um alojamento.
	 * 
	 * @param regiao Regiao onde o alojamento se encontra.
	 */
	public void indicarRegiao(String regiao) {
		((Alojamento) aj).setRegiao(regiao);
	}

	/**
	 * Cria uma ajuda nova, um Item.
	 * 
	 * @param descricao descricao do item.
	 */
	public void indicarDescricao(String descricao) {
		aj = new Item(ajudaID, voluntarioAtual, descricao);
	}

	/**
	 * Define a data de uma ajuda
	 * 
	 * @param a Data em que a ajuda está valida
	 * @requires a depois de data atual, nao pode ser uma data passada
	 */
	public void indicarData(Date a) {
		Random rd = new Random();
		aj.setDate(a);
		int codigoUnicoGerado = rd.nextInt(90000) + 10000;
		this.codigoUnico = codigoUnicoGerado;

		PidgeonSMSSender sender = new PidgeonSMSSender();
		sender.send(String.valueOf(voluntarioAtual.getContacto()), String.valueOf(codigoUnicoGerado));
	}

	/**
	 * Função que verifica se uma região ter uma certa ajuda através do seu índice.
	 * 
	 * @param regiaoAtual   Região onde se pretende verificar a ajuda.
	 * @param numeroDaAjuda Índice da região.
	 * @return
	 */
	public boolean regiaoHasAjuda(String regiaoAtual, int numeroDaAjuda) {
		Regiao r = regioes.getRegioes(regiaoAtual);
		return r.hasAjudaByNumber(numeroDaAjuda);
	}

	/**
	 * Funcao que confirma o registo de uma ajuda atraves do codigo unico que é
	 * mandado ao voluntario
	 * 
	 * @param codigo codigoUnico mandado ao voluntario
	 * @return true se a ajudar foi registada com sucesso, false caso contrario
	 */
	public boolean confirmarAjuda(int codigo) {
		if (codigo == this.codigoUnico) {
			if (aj.getTipoDeAjuda().equals("alojamento")) {
				regioes.addAjudaUmaRegiao(aj, ((Alojamento) aj).getRegiao());
				System.out.println("Alojamento adicionado com sucesso");
				this.ajudaID++;
				return true;
			} else if (aj.getTipoDeAjuda().equals("item")) {
				regioes.addAjudaTodasRegioes(aj);
				System.out.println("Item adicionado com sucesso");
				this.ajudaID++;
				return true;
			}
		} else {
			System.out.println("Operacao nao realizada, codigo inválido");
		}
		return false;
	}

	/**
	 * Devolve a região atraves do seu nome.
	 * @param regiao Nome da região
	 * @requires {hasRegiao(região) == true}
	 * @return Regiao pretendida
	 */
	public Regiao getRegiao(String regiao) {
		return regioes.getRegioes(regiao);
	}

	/**
	 * Remove uma ajuda das regioes, e manda um SMS ao voluntario que registou a correspondente ajuda
	 * @param aj Ajuda que se predente remover
	 */
	public void removeAjuda(Ajuda aj) {
		aj.sendSMS();
		if (aj.getTipoDeAjuda().equals("item")) {
			regioes.removeAjudaTodasRegioes(aj);
		} else {
			regioes.removeAjudaUmaRegiao(aj, ((Alojamento) aj).getRegiao());
		}
	}

}
