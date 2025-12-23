package com.digitelts.dome.trust.registry.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitelts.dome.trust.registry.model.TrustedRegistryDetails;

public interface TrustedRegistryRepository<T extends TrustedRegistryDetails> extends JpaRepository<T,String> {}
