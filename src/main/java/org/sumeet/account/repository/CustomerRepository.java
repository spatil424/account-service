package org.sumeet.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.sumeet.account.entity.Customer;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findCustomerByMobileNumber(String mobileNumber);

    Optional<Customer> findByMobileNumber(String mobileNumber);
}
