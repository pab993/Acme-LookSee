
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

	@Query("select c from Company c where c.userAccount.id = ?1")
	Company findByUserAccountId(int userAccountId);

	@Query("select c from Company c join c.offers o where o.id = ?1")
	Collection<Company> findAllByOfferId(int offerId);

	@Query("select c from Company c join c.offers o where o.draft = false group by c order by count(o) DESC")
	Collection<Company> findCompanyByNumberOfOffers();

	@Query("select c from Company c join c.offers o where o.draft = false group by c having count(o) >= ALL (select count(o1) from Company c1 join c1.offers o1 where o1.draft = false group by c1)")
	Collection<Company> findCompaniesWithMoreOffers();
}
