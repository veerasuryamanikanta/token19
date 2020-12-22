package com.balu.token19.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balu.token19.domain.Technician;
import com.balu.token19.dto.TechnicianDTO;
import com.balu.token19.repo.TechnicianRepository;
import com.balu.token19.repo.UserRepository;
import com.balu.token19.service.TechnicianService;
import com.balu.token19.utils.AppConstants;

@Service
public class TechnicianServiceImpl implements TechnicianService {

	@Autowired
	private TechnicianRepository technicianRepository;

	@Autowired
	private UserRepository userRepository;

	/*
	 * -----------------SAVE TECHNICIAN -------------
	 */

	@Override
	public TechnicianDTO saveTechnician(TechnicianDTO technicianDTO) {
		try {
			Technician technician = new Technician();
			AppConstants.mapper.map(technicianDTO, technician);
			technician.setUser(userRepository.getOne(technicianDTO.getUserId()));
			Technician savedTechnician = technicianRepository.save(technician);
			TechnicianDTO returnTechnicianDTO = new TechnicianDTO();
			AppConstants.mapper.map(savedTechnician, returnTechnicianDTO);
			returnTechnicianDTO.setUserId(technician.getUser().getUserId());
			return returnTechnicianDTO;
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * -----------------GET TECHNICIAN BY USER ID-------------
	 */
	@Override
	public TechnicianDTO getTechnicianByUserId(Long userId) {
		try {
			Technician technician = technicianRepository.findByUserId(userId);
			TechnicianDTO technicianDto = new TechnicianDTO();
			AppConstants.mapper.map(technician, technicianDto);
			technicianDto.setUserId(technician.getUser().getUserId());
			return technicianDto;
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * -----------------GET ALL TECHNICIANS -------------
	 */
	@Override
	public List<TechnicianDTO> getTechnicians() {
		try {
			List<Technician> techniciansList = technicianRepository.findAll();
			System.out.println(techniciansList.size());
			List<TechnicianDTO> techniciansDtoList = new ArrayList<TechnicianDTO>();
			if (techniciansList.size() != 0) {
				for (int i = 0; i < techniciansList.size(); i++) {
					Technician technician = (Technician) techniciansList.get(i);
					TechnicianDTO techniciandto = new TechnicianDTO(technician.getTechnicianId(), technician.getUser().getUserId(),
							technician.getTitle(),technician.getDescription(),technician.getBlogurl(),technician.getExperience(),
							"" + technician.getCreatedDate(),"" + technician.getUpdatedOn(), technician.getIsactive());
					techniciansDtoList.add(techniciandto);
				}
				return techniciansDtoList;
			} else {
				return techniciansDtoList;
			}
		} catch (Exception e) {
			return null;
		}
	}

}
