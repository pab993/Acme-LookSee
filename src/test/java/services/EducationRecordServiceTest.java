
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.EducationRecord;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class EducationRecordServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private EducationRecordService	educationRecordService;


	// Tests
	// ====================================================

	/*
	 * To check the validity of an educationRecord, the system must check every field of the edit form.
	 * 
	 * En este test, se comprueba si la validez de los educationRecord, así el sistema debe validar que los campos introducidos son correctos
	 */

	/*
	 * Edit a educationRecord or create a new one.
	 * 
	 * En este caso de uso un candidato puede crear/editar un nuevo educationRecord siempre y cuando el curriculum le pertenezca.
	 */

	public void editEducationRecordTest(final String username, final int educationRecordId, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			final EducationRecord educationRecord = this.educationRecordService.findOne(educationRecordId);

			this.educationRecordService.save(educationRecord);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	//Drivers
	// ===================================================

	@Test
	public void driverEditEducationRecordTest() {

		final Object testingData[][] = {
			// Alguien sin registrar/logueado -> false
			{
				null, 29, IllegalArgumentException.class
			},
			// Edición por parte del propietario del curriculum -> true
			{
				"candidate1", 29, null
			},
			// Edición por alguien que no es el propietario -> false
			{
				"candidate2", 29, IllegalArgumentException.class
			},
			// Edición por alguien que no es candidato -> false
			{
				"company1", 29, IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.editEducationRecordTest((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

}
