
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Company;
import domain.Offer;
import domain.Salary;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class OfferServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private OfferService	offerService;

	@Autowired
	private CompanyService	companyService;


	// Tests
	// ====================================================

	/*
	 * Search for offers that contain a single key word in their title or description,
	 * provide a given salary (in a given currency), and are still open.
	 * 
	 * En este caso de uso simularemos la búsqueda de ofertas y que ésta puede ser realizada
	 * por todos los actores del sistema.
	 */

	public void searchOfferTest(final String username, final String keyword, final String keyword2, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			Collection<Offer> offers;

			offers = this.offerService.getOfferByKeyWord(keyword, keyword2);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * Browse the list of offers who have registered to the system.
	 * 
	 * En este caso de uso se comprobamos que cualquiera puede listar las ofertas que existen en nuestra aplicación.
	 */

	public void listOfOffersTest(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			this.offerService.findAll();

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * To check the validity of a offer, the system must check its creation moment is in the past, its deadline must be at least one week ahead the moment when an offer is published,
	 * and the maximum salary must be greater or equal to the minimum salary.
	 * 
	 * En este test, se comprueba si el momento de creación de la oferta está en pasado, su fecha límite está a una semana en el futuro de la fecha de creación y se comprueba que el salario máximo es mayor o igual que el salario mínimo
	 * Para ello introducimos valores correctos e incorrectos para observa el comportamiento de la aplicación.
	 */

	/*
	 * Edit an offer or create a new one.
	 * 
	 * En este caso de uso una compañía puede crear/editar una nueva oferta.
	 */

	public void editOfferTest(final String username, final Date creationMoment, final String title, final String description, final Date deadline, final Boolean draft, final Double minSalary, final Double maxSalary, final String currency,
		final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			Offer result;

			result = this.offerService.create();

			final Salary salary = new Salary();

			final Company company = this.companyService.findByPrincipal();

			result.setCreateMoment(creationMoment);
			result.setCompany(company);
			result.setDeadline(deadline);
			result.setDescription(description);
			result.setDraft(draft);
			result.setTitle(title);
			salary.setCurrency(currency);
			salary.setMaxSalary(maxSalary);
			salary.setMinSalary(minSalary);
			result.setSalary(salary);

			this.offerService.save(result);
			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}
	//Drivers
	// ===================================================

	@Test
	public void driverSearchOfferTest() {

		final Object testingData[][] = {
			// Alguien sin registrar/loguado y sin los campos del formulario rellenos -> false
			{
				null, null, null, IllegalArgumentException.class
			},
			// Un candidate -> true
			{
				"candidate1", "keyword", "keyword2", null
			},
			// Una company -> true
			{
				"company1", "keyword", "keyword2", null
			},
			// Un administrador -> true
			{
				"admin", "keyword", "keyword2", null
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.searchOfferTest((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Class<?>) testingData[i][3]);
	}

	@Test
	public void driverListOfferTest() {

		final Object testingData[][] = {
			// Alguien sin registrar/loguado -> true
			{
				null, null
			},
			// Un candidate -> true
			{
				"candidate1", null
			},
			// Una company -> true
			{
				"company1", null
			},
			// Un administrador -> true
			{
				"admin", null
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.listOfOffersTest((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@Test
	public void driverEditOfferTest() {

		//Fecha de creación
		final long l = 100;
		final Date pastDate = new Date(System.currentTimeMillis() - l);

		//Fecha actual
		final Date actual = new Date(System.currentTimeMillis() - l);
		//Fecha futura (8 dias desde la fecha actual)
		final Calendar futureDate = Calendar.getInstance();
		futureDate.setTime(actual);
		futureDate.add(Calendar.DAY_OF_YEAR, 8);
		//Fecha a 6 días de la actual
		final Calendar future6Date = Calendar.getInstance();
		future6Date.setTime(actual);
		future6Date.add(Calendar.DAY_OF_YEAR, 6);
		//Fecha a 7 días de la actual
		final Calendar future7Date = Calendar.getInstance();
		future7Date.setTime(actual);
		future7Date.add(Calendar.DAY_OF_YEAR, 7);
		future7Date.add(Calendar.MINUTE, 1);
		//Fecha en  pasada
		final Date pastDeadlineDate = new Date(System.currentTimeMillis() - 10000000);

		final Object testingData[][] = {
			// Alguien sin registrar/logueado -> false
			{
				null, pastDate, "testTitle1", "testDescription1", futureDate.getTime(), true, 7.0, 10.0, "Euros", IllegalArgumentException.class
			},
			// Alguien que no es una compañía -> false
			{
				"candidate1", pastDate, "testTitle1", "testDescription1", futureDate.getTime(), true, 7.0, 10.0, "Euros", IllegalArgumentException.class
			},
			// Todo correcto -> true
			{
				"company1", pastDate, "testTitle2", "testDescription2", futureDate.getTime(), true, 7.0, 10.0, "Euros", null
			},
			// Deadline en pasado -> false
			{
				"company1", pastDate, "testTitle3", "testDescription3", pastDeadlineDate, true, 7.0, 10.0, "Euros", IllegalArgumentException.class
			},
			// Deadline a 6 dias de la fecha actual -> false
			{
				"company1", pastDate, "testTitle3", "testDescription3", future6Date.getTime(), true, 7.0, 10.0, "Euros", IllegalArgumentException.class
			},
			// Deadline a 7 dias de la fecha actual -> true
			{
				"company1", pastDate, "testTitle3", "testDescription3", future7Date.getTime(), true, 7.0, 10.0, "Euros", null
			},
			// Momento de creación en futuro -> false
			{
				"company1", futureDate.getTime(), "testTitle3", "testDescription3", futureDate.getTime(), true, 7.0, 10.0, "Euros", IllegalArgumentException.class
			},
			// Salarios negativos -> false
			{
				"company1", pastDate, "testTitle3", "testDescription3", futureDate.getTime(), true, -7.0, -10.0, "Euros", IllegalArgumentException.class
			},
			// Salarios negativos -> false
			{
				"company1", pastDate, "test", "testDescription4", futureDate.getTime(), true, -7.0, -10.0, "Euros", IllegalArgumentException.class
			},
			// Salarios limite positivo -> true
			{
				"company1", pastDate, "test", "testDescription4", futureDate.getTime(), true, 1.0, 1.0, "Euros", null
			},
			// Salarios limite negativo -> false
			{
				"company1", pastDate, "test", "testDescription4", futureDate.getTime(), true, 0.1, 0.0, "Euros", IllegalArgumentException.class
			},
			// Salario mínimo mayor que el salario máximo -> false
			{
				"company1", pastDate, "test", "testDescription4", futureDate.getTime(), true, 100.0, 50.0, "Euros", IllegalArgumentException.class
			},
			// Titulo y descripcion vacios -> false
			{
				"company1", pastDate, "", "", futureDate.getTime(), true, 1.0, 34.0, "Euros", IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.editOfferTest((String) testingData[i][0], (Date) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (Date) testingData[i][4], (Boolean) testingData[i][5], (Double) testingData[i][6], (Double) testingData[i][7],
				(String) testingData[i][8], (Class<?>) testingData[i][9]);
	}
}
