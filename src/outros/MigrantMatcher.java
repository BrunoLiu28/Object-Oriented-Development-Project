package outros;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import handlers.autentificacao.AutentificaHandler;
import handlers.procurarAjuda.ProcurarAjudaHandler;
import handlers.registarAjuda.RegistarAjudaHandler;
/**
 * Classe MigrantMatcher
 * 
 * @author Rodrigo Cancelinha fc56371
 * @author Bruno Liu fc56297
 *
 */
public class MigrantMatcher {

	private RegistarAjudaHandler registarAjuda;
	private ProcurarAjudaHandler procuraAjuda;
	private AutentificaHandler autentifica;
	
	/**
	 * Construtor da classe.
	 */
	public MigrantMatcher() {
		this.autentifica = new AutentificaHandler();
		this.registarAjuda = new RegistarAjudaHandler(autentifica);
		this.procuraAjuda = new ProcurarAjudaHandler(registarAjuda, autentifica);
	}

	/**
	 * Funcao para comecar o programa.
	 */
	public void startProgram() {

		System.out.println("Começando a aplicacao Migrant Matcher");
		boolean continuar = true;
		int op = -1;
		Scanner sc = new Scanner(System.in);
		while (continuar) {
			System.out.println("Insira a opção que deseja realizar:");
			System.out.println("1  -> Registar uma ajuda");
			System.out.println("2  -> Procurar uma ajuda");
			System.out.println("-1 -> Fechar o programa");

			try {
				op = sc.nextInt();
			} catch (Exception e) {
				System.out.println("Tem de inserir um número");
			}

			if (op == 1) {
				System.out.println("Começando o registo");
				System.out.println("Insira o seu contacto telefónico");
				try {
					int contacto = sc.nextInt();
					autentifica.identificarVoluntario(contacto);
				} catch (Exception e) {
					System.out.println("Necessita de inserir o seu número de telefone");
					System.out.println("Terminando o programa");
					System.exit(-1);
				}
				registarAjuda.registoVoluntario();
				System.out.println("Comecando o processo de registar uma ajuda");
				System.out.println(
						"Indique que tipo de ajuda pretende registar: \n1  -> Alojamento \n2  -> Item \n-1 -> Cancelar operação");

				int tipoDeAjuda = sc.nextInt();
				if (tipoDeAjuda == 1) {
					System.out.println("Insira o número de pessoas que o alojamento alberga");
					try {
						int capacidade = sc.nextInt();
						registarAjuda.indicarCapacidade(capacidade);
					} catch (Exception e) {
						System.out.println(
								"Necessitava de introduzir um numero para a quantidade de pessoas que o alojamento alberga");
						System.out.println("Terminando o programa");
					}
					
					registarAjuda.listaDeRegioes();
					
					System.out.println("Insira a regiao onde o alojamento se encontra");
					boolean errado = true;
					while (errado) {
						String regiao = sc.next();
						if (registarAjuda.hasRegiao(regiao)) {
							registarAjuda.indicarRegiao(regiao);
							errado = false;
						} else {
							// REGIAO NAO EXISTE
							System.out.println("A regiao que inseriu nao existe, tente novamente");
							registarAjuda.listaDeRegioes();
							System.out.println("Insira a regiao onde o alojamento se encontra");

						}
					}

					SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
					System.out.println("Insira a data de disponibilização da ajuda no seguinte formato: dd-MM-yyyy");
					String s = sc.next();
					try {
						registarAjuda.indicarData(formatter.parse(s));
					} catch (Exception e) {
						System.out.println("Data introduzida de forma incorreta");
						System.out.println("Terminando o programa");
						System.exit(-1);
					}
					
					System.out.println("Insira o código de confirmação");
					int codigo = sc.nextInt();
					registarAjuda.confirmarAjuda(codigo);

				} else if (tipoDeAjuda == 2) {
					System.out.println("Insira a descrição do item");
					String descricao = sc.next();
					registarAjuda.indicarDescricao(descricao);
					
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
					System.out.println("Insira a data de disponibilização da ajuda no seguinte formato: dd-MM-yyyy");
					String s = sc.next();
					try {
						registarAjuda.indicarData(formatter.parse(s));
					} catch (Exception e) {
						System.out.println("Data introduzida de forma incorreta");
						System.out.println("Terminando o programa");
						System.exit(-1);
					}
					
					System.out.println("Insira o código de confirmação");
					int codigo = sc.nextInt();
					registarAjuda.confirmarAjuda(codigo);
					
				} else if (tipoDeAjuda == -1) {
					// MUDAR CONDICAO DENTRO DO WHILE PARA FALSE
					System.out.println("OPERACAO CANCELADA COM SUCESSO");
				} else {
					System.out.println("Operacao inválida, tente novamente");
					System.out.println("Caso pretenda cancelar o registo insira: cancelar");
				}
				// }
			} else if (op == 2) {
				System.out.println("Comecando o processo de procura de ajudas");
				System.out.println("Caso queira registar-se individualmente insira 1");
				System.out.println("Caso queira registar-se com a família insira 2");
				int opcao = sc.nextInt();
				if (opcao == 1) {

					System.out.println("Comecando o processo de registar individualmente");
					System.out.println("Insira o seu nome");
					try {
						String nome = sc.next();
						System.out.println("Insira o seu numero de telefone");
						int contacto = sc.nextInt();

						autentifica.indicarRegistoIndividual(nome, contacto);

					} catch (Exception e) {
						System.out.println("O número de telefone deve apenas incluir números");
						System.exit(-1);
					}
					procuraAjuda.registoMigrante();
					System.out.println("Registo realizado");

					procuraAjuda.pedirListaRegioes();
					System.out.println("Selecione a regiao em que pretende procurar uma ajuda");
					String regiao = sc.next();

					boolean regiaoErrada = true;
					while (regiaoErrada) {
						if (registarAjuda.hasRegiao(regiao)) {
							System.out.println("Regiao válida");
							regiaoErrada = false;
						} else {
							System.out.println("A regiao que inseriu nao se encontra na lista de regioes que foi dada,"
									+ " insira uma regiao que pertenca à lista de regioes");
							procuraAjuda.pedirListaRegioes();
							regiao = sc.next();
						}
					}

					procuraAjuda.indicarRegiao(regiao);

					System.out.println("Lista de ajudas da regiao: " + regiao);

					if (procuraAjuda.listaDeAjudasDaRegiao()) {
						System.out.println(
								"Selecione a ajuda que pretende usufruir, esta ação pode se repetir varias vezes");
						System.out.println("Insira -1 quando quiser acabar a seleção das ajudas");
						boolean selecAjudas = true;
						while (selecAjudas) {
							int numeroDaAjuda = sc.nextInt();
							if (numeroDaAjuda == -1) {
								selecAjudas = false;
								procuraAjuda.mandarConfirmacao();
							} else {
								if (procuraAjuda.hasAjuda(numeroDaAjuda)) {
									procuraAjuda.escolherAjuda(numeroDaAjuda);
								} else {
									System.out.println(
											"A ajuda que inseriu nao se encontra na lista de ajudas que foi dada,"
													+ " insira uma ajuda que pertenca à lista de ajudas");
									procuraAjuda.listaDeAjudasDaRegiao();
									numeroDaAjuda = sc.nextInt();
								}
							}
						}
						procuraAjuda.mandarConfirmacao();
					} else {
						System.out.println("pretende ser notifiicado caso apareca uma ajuda nova na regiao escolhida?");
						procuraAjuda.requisitarNotificacao();
					}

				} else if (opcao == 2) {

					System.out.println("Comecando o processo de registar com a familia");
					System.out.println("Insira o seu nome");
					String nome = sc.next();
					System.out.println("Insira o seu numero de telefone");
					int contacto = sc.nextInt();
					System.out.println("Insira a quantidade de elementos");
					int membros = sc.nextInt();

					autentifica.indicarCabecaDeCasal(nome, contacto, membros);
					for (int i = 0; i < membros; i++) {
						System.out.println("Insira o nome do outro elemento");
						nome = sc.next();
						autentifica.indicarOutroMembro(nome);
					}

					procuraAjuda.registoMigrante();
					System.out.println("Registo familiar realizado");

					procuraAjuda.pedirListaRegioes();
					System.out.println("Selecione a regiao em que pretende procurar uma ajuda");
					String regiao = sc.next();

					boolean regiaoErrada = true;
					while (regiaoErrada) {
						if (registarAjuda.hasRegiao(regiao)) {
							System.out.println("Regiao existe");
							regiaoErrada = false;
						} else {
							System.out.println("A regiao que inseriu nao se encontra na lista de regioes que foi dada,"
									+ "insira uma regiao que pertenca à lista de regioes");
							procuraAjuda.pedirListaRegioes();
							regiao = sc.next();
						}
					}

					procuraAjuda.indicarRegiao(regiao);

					System.out.println("Lista de ajudas da regiao: " + regiao);

					if (procuraAjuda.listaDeAjudasDaRegiao()) {
						System.out.println(
								"Selecione a ajuda que pretende usufruir, esta ação pode se repetir varias vezes");
						System.out.println("Insira -1 quando quiser acabar a seleção das ajudas");
						boolean selecAjudas = true;
						while (selecAjudas) {
							int numeroDaAjuda = sc.nextInt();
							if (numeroDaAjuda == -1) {
								selecAjudas = false;
								// MANDAR CONFIRMARCAO
								procuraAjuda.mandarConfirmacao();
							} else {
								if (procuraAjuda.hasAjuda(numeroDaAjuda)) {
									procuraAjuda.escolherAjuda(numeroDaAjuda);
								} else {
									System.out.println(
											"A ajuda que inseriu nao se encontra na lista de ajudas que foi dada,"
													+ " insira uma ajuda que pertenca à lista de ajudas");
									procuraAjuda.listaDeAjudasDaRegiao();
								}
							}
						}
					} else {
						System.out.println("pretende ser notifiicado caso apareca uma ajuda nova na regiao escolhida?");
						procuraAjuda.requisitarNotificacao(); // AINDA NAO FEITO
					}

				} else {
					System.out.println("Opção indisponível");
					System.out.println("Insira uma das opcoes disponiveis");
				}
			} else if (op == -1) {
				continuar = false;
			} else {
				System.out.println("Operação inválida, tente novamente\n");
			}
		}

		sc.close();
	}

}
