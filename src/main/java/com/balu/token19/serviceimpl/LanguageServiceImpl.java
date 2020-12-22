package com.balu.token19.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balu.token19.domain.Languages;
import com.balu.token19.dto.LanguageDTO;
import com.balu.token19.repo.LanguageRepository;
import com.balu.token19.service.LanguageService;
import com.balu.token19.utils.AppConstants;

@Service
public class LanguageServiceImpl implements LanguageService {

	@Autowired
	private LanguageRepository languageRepository;

	/*
	 * -----------------SAVE Languages -------------
	 */

	@Override
	public LanguageDTO saveLanguage(LanguageDTO languageDTO) {
		try {
			Languages languages = new Languages();
			AppConstants.mapper.map(languageDTO, languages);
			Languages saved_language = languageRepository.save(languages);
			LanguageDTO returnLanguage = new LanguageDTO();
			AppConstants.mapper.map(saved_language, returnLanguage);
			return returnLanguage;
		} catch (Exception e) {
			return null;
		}
	}
	
	/*
	 * -----------------GET Languages -------------
	 */

	@Override
	public List<LanguageDTO> getLanguages() {
		try {
			List<Languages> languagesList = languageRepository.findAll();
			List<LanguageDTO> languageDTOsList = new ArrayList<LanguageDTO>();
			if (languagesList.size() != 0) {
				for (int i = 0; i < languagesList.size(); i++) {
					Languages article = (Languages) languagesList.get(i);
					LanguageDTO articledto = new LanguageDTO(article.getLanguageId(), article.getLanguageMotherName(),
							article.getLanguageEnglishName(), "" + article.getCreatedDate(),
							"" + article.getUpdatedOn(), article.getIsactive());
					languageDTOsList.add(articledto);
				}
				return languageDTOsList;
			} else {
				return languageDTOsList;
			}
		} catch (Exception e) {
			return null;
		}
	}

}
