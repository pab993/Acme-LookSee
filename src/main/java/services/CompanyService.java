
package services;

import java.util.Collection;
import java.util.HashSet;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import repositories.CompanyRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountRepository;
import domain.Administrator;
import domain.Company;
import domain.Offer;
import forms.CompanyForm;

@Service
@Transactional
public class CompanyService {

	//Managed Repository =============================================================================

	@Autowired
	private CompanyRepository		companyRepository;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private UserAccountRepository	userAccountRepository;


	//Constructor methods ============================================================================

	//Simple CRUD methods ============================================================================

	public Collection<Company> findAll() {
		Collection<Company> result;

		result = this.companyRepository.findAll();

		return result;
	}

	public Company create() {
		Company result;
		UserAccount userAccount;
		Authority authority;
		Collection<Offer> offers;

		authority = new Authority();
		userAccount = new UserAccount();
		offers = new HashSet<Offer>();

		authority.setAuthority("COMPANY");
		userAccount.addAuthority(authority);

		result = new Company();

		result.setUserAccount(userAccount);
		result.setOffers(offers);

		return result;
	}

	public Company save(Company company) {
		Assert.notNull(company);
		Assert.notNull(company.getUserAccount());
		Company result;

		result = companyRepository.save(company);

		return result;
	}

	public Company findOne(int companyId) {
		Company result;
		result = companyRepository.findOne(companyId);
		return result;
	}

	//Other Business Methods =========================================================================

	public Company findByPrincipal() {
		Company result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Company findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);
		Company result;

		result = this.companyRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public Collection<Company> findAllByOfferId(int offerId) {
		Collection<Company> result;

		result = companyRepository.findAllByOfferId(offerId);

		return result;
	}

	public Company reconstruct(CompanyForm companyForm, BindingResult binding) {

		Company result;

		result = create();
		result.getUserAccount().setUsername(companyForm.getUsername());
		result.setName(companyForm.getName());
		result.setIsBan(false);
		result.setSurname(companyForm.getSurname());
		result.setPhone(companyForm.getPhone());
		result.setEmail(companyForm.getEmail());
		result.setPostalAddress(companyForm.getPostalAddress());
		result.setVat(companyForm.getVat());
		result.getUserAccount().setPassword(new Md5PasswordEncoder().encodePassword(companyForm.getPassword(), null));

		//		comprobarPhone(companyForm.getPhone(), binding);
		comprobarContrasena(companyForm.getPassword(), companyForm.getRepeatPassword(), binding);
		comprobarPostalAddress(companyForm.getPostalAddress(), binding);
		comprobarUsuarioUnico(companyForm.getUsername(), binding);

		return result;
	}

	private boolean comprobarUsuarioUnico(String username, BindingResult binding) {
		FieldError error;
		String[] codigos;
		boolean result = false;

		UserAccount userAccount = userAccountRepository.findByUsername(username);

		if (userAccount == null) {
			result = true;
		}

		if (!result) {
			codigos = new String[1];
			codigos[0] = "company.username.unique";
			//			error = new FieldError("cadidateForm", "password", "candidate.paswword.mismatch");
			error = new FieldError("companyForm", "username", username, false, codigos, null, "");
			binding.addError(error);
		}
		return result;
	}

	private boolean comprobarContrasena(String password, String passwordRepeat, BindingResult binding) {
		FieldError error;
		String[] codigos;
		boolean result;

		if (StringUtils.isNotBlank(password) && StringUtils.isNotBlank(passwordRepeat))
			result = password.equals(passwordRepeat);
		else
			result = false;

		if (!result) {
			codigos = new String[1];
			codigos[0] = "company.password.mismatch";
			error = new FieldError("companyForm", "password", password, false, codigos, null, "");
			binding.addError(error);
		}

		return result;
	}

	private boolean comprobarPostalAddress(String postalAddress, BindingResult binding) {
		FieldError error;
		String[] codigos;
		boolean result;

		if (postalAddress == null || postalAddress.isEmpty()) {
			result = true;
		} else {
			result = false;
		}

		if (!result) {
			if (postalAddress.matches("^[0-9]{5}$")) {
				result = true;
			} else {
				codigos = new String[1];
				codigos[0] = "company.postalAddress.error";
				error = new FieldError("companyForm", "postalAddress", postalAddress, false, codigos, null, "");
				binding.addError(error);
			}
		}

		return result;
	}

	private boolean comprobarPhone(String phone, BindingResult binding) {
		FieldError error;
		String[] codigos;
		boolean result;

		if (phone == null || phone.isEmpty()) {
			result = true;
		} else {
			result = false;
		}

		if (!result) {
			if (phone.matches("^[+][a-zA-Z]{2}([(][0-9]{1,3}[)])?[0-9]{4,25}$")) {
				result = true;
			} else {
				codigos = new String[1];
				codigos[0] = "company.phone.error";
				error = new FieldError("candidateForm", "phone", phone, false, codigos, null, "");
				binding.addError(error);
			}
		}

		return result;
	}

	public Collection<Company> findCompanyByNumberOfOffers() {

		Collection<Company> companies = companyRepository.findCompanyByNumberOfOffers();
		return companies;

	}

	public Collection<Company> findCompaniesWithMoreOffers() {

		Collection<Company> companies = companyRepository.findCompaniesWithMoreOffers();
		return companies;

	}

	public void ban(int companyId) {
		Administrator administrator;
		Company company;

		administrator = administratorService.findByPrincipal();
		company = findOne(companyId);

		Assert.isInstanceOf(Administrator.class, administrator);

		company.setIsBan(true);

		companyRepository.save(company);
	}

	public void unBan(int companyId) {
		Administrator administrator;
		Company company;

		administrator = administratorService.findByPrincipal();
		company = findOne(companyId);

		Assert.isInstanceOf(Administrator.class, administrator);

		company.setIsBan(false);

		companyRepository.save(company);
	}

}
