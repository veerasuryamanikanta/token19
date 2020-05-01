package com.balu.token19.serviceimpl;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balu.token19.domain.Version;
import com.balu.token19.dto.VersionDTO;
import com.balu.token19.repo.VersionRepository;
import com.balu.token19.service.VersionService;

@Service
public class VersionServiceImpl implements VersionService {

	private static final Mapper mapper = new DozerBeanMapper();

	@Autowired
	private VersionRepository versionRepository;

	
	/*
	 * -----------------SAVE VERSION -------------
	 */
	@Override
	public VersionDTO saveVersion(VersionDTO versionDTO) {
		Version version = new Version();
		mapper.map(versionDTO, version);
		Version versionData = versionRepository.save(version);
		VersionDTO versionDtoData = new VersionDTO();
		mapper.map(versionData, versionDtoData);
		return versionDtoData;
	}
	
	/*
	 * -----------------GET LATEST APP VERSION -------------
	 */
	@Override
	public VersionDTO findLatestVersion() {
		Version version = versionRepository.findLatestVersion();
		if(version!=null) {
			VersionDTO versionDtoData = new VersionDTO();
			mapper.map(version, versionDtoData);
			return versionDtoData;
		}else {
			return null;
		}
	}

}
