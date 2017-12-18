
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Miscellaneous;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MiscellaneousServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private MiscellanyService	miscellaneousService;


	// Tests
	// ====================================================

	/*
	 * To check the validity of a miscellaneous, the system must check every field of the edit form.
	 * 
	 * En este test, se comprueba si la validez de los miscellaneous, así el sistema debe validar que los campos introducidos son correctos
	 */

	/*
	 * Edit a miscellaneous or create a new one.
	 * 
	 * En este caso de uso un candidato puede crear/editar un nuevo miscellaneous siempre y cuando el curriculum le pertenezca.
	 */

	public void editMiscellaneousTest(final String username, final int miscellaneousId, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			final Miscellaneous miscellaneous = this.miscellaneousService.findOne(miscellaneousId);

			this.miscellaneousService.save(miscellaneous);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	//Drivers
	// ===================================================

	@Test
	public void driverEditMiscellaneousTest() {

		final Object testingData[][] = {
			// Alguien sin registrar/logueado -> false
			{
				null, 27, IllegalArgumentException.class
			},
			// Edición por parte del propietario del curriculum -> true
			{
				"candidate1", 27, null
			},
			// Edición por alguien que no es el propietario -> false
			{
				"candidate2", 27, IllegalArgumentException.class
			},
			// Edición por alguien que no es candidato -> false
			{
				"company1", 27, IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.editMiscellaneousTest((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

}
