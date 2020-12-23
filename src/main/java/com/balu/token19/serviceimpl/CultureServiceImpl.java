package com.balu.token19.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balu.token19.domain.Culture;
import com.balu.token19.dto.CommonDTO;
import com.balu.token19.dto.CultureDTO;
import com.balu.token19.repo.CultureRepository;
import com.balu.token19.repo.UserRepository;
import com.balu.token19.service.CultureService;
import com.balu.token19.utils.AppConstants;

@Service
public class CultureServiceImpl implements CultureService {

	@Autowired
	private CultureRepository cultureRepository;

	@Autowired
	private UserRepository userRepository;

	/*
	 * -----------------SAVE CULTURE -------------
	 */

	@Override
	public CultureDTO saveCulture(CultureDTO cultureDTO) {
		try {
			Culture culture = new Culture();
			AppConstants.mapper.map(cultureDTO, culture);
			culture.setUser(userRepository.getOne(cultureDTO.getUserId()));
			Culture savedCulture = cultureRepository.save(culture);
			CultureDTO returnCultureDTO = new CultureDTO();
			AppConstants.mapper.map(savedCulture, returnCultureDTO);
			return returnCultureDTO;
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * -----------------GET CULTURE LIST-------------
	 */
	@Override
	public List<CultureDTO> getCultureByUserId(Long userId) {
		try {
			List<Culture> cultureDataList = cultureRepository.findByUserId(userId);
			List<CultureDTO> cultureDtoList = new ArrayList<CultureDTO>();
			if (cultureDataList.size() != 0) {
				for (int i = 0; i < cultureDataList.size(); i++) {
					Culture article = (Culture) cultureDataList.get(i);
					CultureDTO articledto = new CultureDTO(article.getCultureId(), article.getUser().getUserId(),
							article.getTitle(), article.getDescription(), "" + article.getCreatedDate(),
							"" + article.getUpdatedOn(), article.isIsactive());
					cultureDtoList.add(articledto);
				}
				return cultureDtoList;
			} else {
				return cultureDtoList;
			}
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * -----------------SAVE ALL CULTURES -------------
	 */
	@Override
	public List<CultureDTO> saveAllCultures(CommonDTO commonDTO) {
		try {
			List<CultureDTO> cultureDtoList = commonDTO.getCultures();
			List<Culture> cultureList = new ArrayList<Culture>();
			List<CultureDTO> returnCultureDtoList = new ArrayList<CultureDTO>();
			if (cultureDtoList.size() != 0) {
				for (CultureDTO culturedto : cultureDtoList) {
					Culture culture = new Culture();
					AppConstants.mapper.map(culturedto, culture);
					culture.setUser(userRepository.getOne(culturedto.getUserId()));
					cultureList.add(culture);
				}
				List<Culture> savedCulturesList = cultureRepository.saveAll(cultureList);
				if (savedCulturesList.size() != 0) {
					for (Culture culture : savedCulturesList) {
						CultureDTO returnCultureDTO = new CultureDTO();
						AppConstants.mapper.map(culture, returnCultureDTO);
						returnCultureDTO.setUserId(culture.getUser().getUserId());
						returnCultureDtoList.add(returnCultureDTO);
					}
				}
			}
			return returnCultureDtoList;
		} catch (Exception e) {
			return null;
		}
	}

}
