package com.balu.token19.service;

import java.util.List;

import com.balu.token19.dto.LanguageDTO;

public interface LanguageService {

	LanguageDTO saveLanguage(LanguageDTO languageDTO);

	List<LanguageDTO> getLanguages();

}
