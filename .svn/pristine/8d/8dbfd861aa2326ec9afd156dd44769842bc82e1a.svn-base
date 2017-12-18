
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.ProfessionalRecord;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class ProfessionalRecordServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private ProfessionalRecordService	professionalRecordService;


	// Tests
	// ====================================================

	/*
	 * To check the validity of an professionalRecord, the system must check every
	 * field of the edit form.
	 * 
	 * En este test, se comprueba si la validez de los professionalRecord, así el
	 * sistema debe validar que los campos introducidos son correctos
	 */

	/*
	 * Edit a professionalRecord or create a new one.
	 * 
	 * En este caso de uso un candidato puede crear/editar un nuevo
	 * professionalRecord siempre y cuando el curriculum le pertenezca.
	 */

	public void editProfessionalRecordTest(final String username, final int professionalRecordId, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			final ProfessionalRecord professionalRecord = this.professionalRecordService.findOne(professionalRecordId);

			this.professionalRecordService.save(professionalRecord);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	// Drivers
	// ===================================================

	@Test
	public void driverEditProfessionalRecordTest() {

		final Object testingData[][] = {
			// Alguien sin registrar/logueado -> false
			{
				null, 25, IllegalArgumentException.class
			},
			// Edición por parte del propietario del curriculum -> true
			{
				"candidate1", 25, null
			},
			// Edición por alguien que no es el propietario -> false
			{
				"candidate2", 25, IllegalArgumentException.class
			},
			// Edición por alguien que no es candidato -> false
			{
				"company1", 25, IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.editProfessionalRecordTest((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

}
