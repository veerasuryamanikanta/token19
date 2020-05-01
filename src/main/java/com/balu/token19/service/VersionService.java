package com.balu.token19.service;

import com.balu.token19.dto.VersionDTO;

public interface VersionService {

	VersionDTO saveVersion(VersionDTO versionDTO);
	
	VersionDTO findLatestVersion();

	
}
