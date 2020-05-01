package com.balu.token19.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.balu.token19.domain.Version;

public interface VersionRepository extends JpaRepository<Version,Long>{
	
	@Query(nativeQuery = true, value = "select * from version ORDER BY version_id DESC LIMIT 1")
	Version findLatestVersion();
	
}
