package testsJUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ajudas.Ajuda;
import handlers.autentificacao.MockAutentificaHandler;
import handlers.procurarAjuda.ProcurarAjudaHandler;
import handlers.registarAjuda.MockRegistarAjudaHandler;
import migrantes.MigranteCabecaDeCasal;
import migrantes.MigranteDoAgregado;
import outros.Configuration;
import regioes.Regiao;
import tiposDeSort.ConjuntoSort;
import tiposDeSort.DataCrescenteSort;
import tiposDeSort.MigrantMatcherSorterStrategy;
import voluntarios.CatalogoVoluntario;

class TestesJUnit {
	private MockRegistarAjudaHandler registarAjuda;
	private ProcurarAjudaHandler procuraAjuda;
	private MockAutentificaHandler autentifica;
	private static final String[] distritos = { "aveiro", "beja", "braga", "bragança", "castelo branco", "coimbra",
			"evora", "faro", "guarda", "leiria", "lisboa", "portalegre", "porto", "santarem", "setubal",
			"viana do castelo", "vila real", "viseu" };

	@BeforeEach
	protected void setUp() throws Exception {
		this.autentifica = new MockAutentificaHandler();
		this.registarAjuda = new MockRegistarAjudaHandler(autentifica);
		this.procuraAjuda = new ProcurarAjudaHandler(registarAjuda, autentifica);

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		autentifica.identificarVoluntario(11111);
		registarAjuda.registoVoluntario();
		registarAjuda.indicarDescricao("Conjunto de carrinhos da hotwheels");
		registarAjuda.indicarData(formatter.parse("28-03-2030"));
		registarAjuda.confirmarAjuda(registarAjuda.getCodigoUnico());

		autentifica.identificarVoluntario(22222);
		registarAjuda.registoVoluntario();
		registarAjuda.indicarCapacidade(2);
		registarAjuda.indicarRegiao("lisboa");
		registarAjuda.indicarData(formatter.parse("05-03-2025"));
		registarAjuda.confirmarAjuda(registarAjuda.getCodigoUnico());

		autentifica.identificarVoluntario(33333);
		registarAjuda.registoVoluntario();
		registarAjuda.indicarCapacidade(4);
		registarAjuda.indicarRegiao("porto");
		registarAjuda.indicarData(formatter.parse("06-08-2023"));
		registarAjuda.confirmarAjuda(registarAjuda.getCodigoUnico());

		autentifica.identificarVoluntario(11111);
		registarAjuda.registoVoluntario();
		registarAjuda.indicarDescricao("Kit de primeiros socorros");
		registarAjuda.indicarData(formatter.parse("15-12-2022"));
		registarAjuda.confirmarAjuda(registarAjuda.getCodigoUnico());

		autentifica.identificarVoluntario(66666);
		registarAjuda.registoVoluntario();
		registarAjuda.indicarDescricao("Computador para trabalhar");
		registarAjuda.indicarData(formatter.parse("16-10-2028"));
		registarAjuda.confirmarAjuda(registarAjuda.getCodigoUnico());

		autentifica.identificarVoluntario(77777);
		registarAjuda.registoVoluntario();
		registarAjuda.indicarCapacidade(1);
		registarAjuda.indicarRegiao("viseu");
		registarAjuda.indicarData(formatter.parse("13-08-2029"));
		registarAjuda.confirmarAjuda(registarAjuda.getCodigoUnico());

		autentifica.identificarVoluntario(88888);
		registarAjuda.registoVoluntario();
		registarAjuda.indicarDescricao("Carro");
		registarAjuda.indicarData(formatter.parse("05-02-2025"));
		registarAjuda.confirmarAjuda(registarAjuda.getCodigoUnico());
		
		autentifica.identificarVoluntario(22222);
		registarAjuda.registoVoluntario();
		registarAjuda.indicarCapacidade(4);
		registarAjuda.indicarRegiao("lisboa");
		registarAjuda.indicarData(formatter.parse("13-08-2022"));
		registarAjuda.confirmarAjuda(registarAjuda.getCodigoUnico());
		
		autentifica.identificarVoluntario(88888);
		registarAjuda.registoVoluntario();
		registarAjuda.indicarCapacidade(5);
		registarAjuda.indicarRegiao("leiria");
		registarAjuda.indicarData(formatter.parse("11-08-2022"));
		registarAjuda.confirmarAjuda(registarAjuda.getCodigoUnico());
		
		autentifica.identificarVoluntario(99999);
		registarAjuda.registoVoluntario();
		registarAjuda.indicarCapacidade(7);
		registarAjuda.indicarRegiao("faro");
		registarAjuda.indicarData(formatter.parse("16-02-2024"));
		registarAjuda.confirmarAjuda(registarAjuda.getCodigoUnico());
		
		autentifica.identificarVoluntario(66666);
		registarAjuda.registoVoluntario();
		registarAjuda.indicarCapacidade(7);
		registarAjuda.indicarRegiao("porto");
		registarAjuda.indicarData(formatter.parse("16-02-2026"));
		registarAjuda.confirmarAjuda(registarAjuda.getCodigoUnico());
	}

	@Test
	void testeVoluntariosCriados() {
		CatalogoVoluntario cv = autentifica.getVoluntarios();
		assertEquals(7, cv.getVoluntarios().size());
	}

	@Test
	void testeAjudasInseridasComSucesso() {
		int numeroDeAjudas = 0;
		for (int i = 0; i < distritos.length; i++) {
			if (i == 0) {
				numeroDeAjudas += registarAjuda.getRegiao(distritos[i]).getListaDeAjudas().size();
			} else {
				for (Ajuda a : registarAjuda.getRegiao(distritos[i]).getListaDeAjudas()) {
					if (a.getTipoDeAjuda().equals("alojamento")) {
						numeroDeAjudas++;
					}

				}
			}
		}
		assertEquals(11, numeroDeAjudas);
	}

	@Test
	void testeRetirarAjudaComSucesso() {
		autentifica.indicarRegistoIndividual("Joao", 123456);
		procuraAjuda.registoMigrante();

		Random rd = new Random();
		int i = rd.nextInt(distritos.length);
		procuraAjuda.indicarRegiao(distritos[i]);
		int numeroDeAjudas = registarAjuda.getRegiao(distritos[i]).getListaDeAjudas().size();
		if (numeroDeAjudas > 0) {

			int ajudaRandomARetirar = rd.nextInt(numeroDeAjudas);
			procuraAjuda.escolherAjuda(ajudaRandomARetirar);
			procuraAjuda.mandarConfirmacao();

			assertEquals(numeroDeAjudas - 1, registarAjuda.getRegiao(distritos[i]).getListaDeAjudas().size());
		}
	}

	@Test
	void testeCheckNumeroDeAjudasInvalidas() {
		Random rd = new Random();
		int i = rd.nextInt(distritos.length);
		procuraAjuda.indicarRegiao(distritos[i]);
		int numeroDeAjudas = registarAjuda.getRegiao(distritos[i]).getListaDeAjudas().size();
		if (numeroDeAjudas > 0) {
			int ajudaInvalida = rd.nextInt(numeroDeAjudas) + numeroDeAjudas;
			assertFalse(procuraAjuda.hasAjuda(ajudaInvalida));
		}
	}

	@Test
	void testeSendNotification() throws ParseException {
		autentifica.indicarRegistoIndividual("Joao", 123456);
		procuraAjuda.registoMigrante();

		Random rd = new Random();
		int i = rd.nextInt(distritos.length);
		procuraAjuda.indicarRegiao(distritos[i]);
		int numeroDeAjudas = registarAjuda.getRegiao(distritos[i]).getListaDeAjudas().size();

		for (int j = 0; j < numeroDeAjudas; j++) {
			procuraAjuda.escolherAjuda(j);
		}
		procuraAjuda.mandarConfirmacao();

		autentifica.indicarRegistoIndividual("Antonio", 654321);
		procuraAjuda.registoMigrante();
		procuraAjuda.indicarRegiao(distritos[i]);
		if (!procuraAjuda.listaDeAjudasDaRegiao()) {

		}
		procuraAjuda.requisitarNotificacao();

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		autentifica.identificarVoluntario(33333);
		registarAjuda.registoVoluntario();
		registarAjuda.indicarCapacidade(4);
		registarAjuda.indicarRegiao(distritos[i]);
		registarAjuda.indicarData(formatter.parse("06-08-2023"));
		registarAjuda.confirmarAjuda(registarAjuda.getCodigoUnico());

	}

	@Test
	void testeMigrantesAdicionadosAoAgregado() {
		autentifica.indicarCabecaDeCasal("Luis", 987654321, 3);
		autentifica.indicarOutroMembro("A");
		autentifica.indicarOutroMembro("B");
		autentifica.indicarOutroMembro("C");
		MigranteDoAgregado[] migrantes = ((MigranteCabecaDeCasal) autentifica.getMigranteCorrente()).getAgregado()
				.getMigrantes();
		assertEquals("A", migrantes[0].getNome());
		assertEquals("B", migrantes[1].getNome());
		assertEquals("C", migrantes[2].getNome());

	}

	@Test
	void testeSorterDataCrescente() {
		Configuration config = Configuration.getInstance();
		MigrantMatcherSorterStrategy sorter = config.getInstanceOfClass("", new DataCrescenteSort());
		Random rd = new Random();
		int i = rd.nextInt(distritos.length);
		Regiao r = registarAjuda.getRegiao(distritos[i]);
		r.sortListaDeAjudas();
		List<Ajuda> ajudasAux =sorter.sort(r.getListaDeAjudas());
		boolean valid = true;
		for (int j =0; j < ajudasAux.size()-1; j++) {
			if (ajudasAux.get(j).getDate().compareTo(ajudasAux.get(j+1).getDate()) > 0) {
				valid = false;
			}
		}
		
		assertTrue(valid);
	}
	
	@Test
	void testeSorterOrdemDeAjudas() {
		Configuration config = Configuration.getInstance();
		MigrantMatcherSorterStrategy sorter = config.getInstanceOfClass("", new ConjuntoSort());
		Random rd = new Random();
		int i = rd.nextInt(distritos.length);
		Regiao r = registarAjuda.getRegiao(distritos[i]);
		r.sortListaDeAjudas();
		List<Ajuda> ajudasAux =sorter.sort(r.getListaDeAjudas());
		boolean valid = true;
		for (int j =0; j < ajudasAux.size()-1; j++) {
			if (ajudasAux.get(j).getTipoDeAjuda().equals("item") && ajudasAux.get(j+1).getTipoDeAjuda().equals("alojamento")) {
				valid = false;
			}
		}
		
		assertTrue(valid);
	}

	
	//ver adicionar uma alojamneto e ver se foi adicionado bem usar lista de regioes
}
