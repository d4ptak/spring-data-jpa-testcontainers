package com.d4ptak.springdatajpatestcontainers.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Integer> {

}
