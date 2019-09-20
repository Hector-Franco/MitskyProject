package tk.mitsky.web.model.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tk.mitsky.web.model.Club;
import tk.mitsky.web.dao.ClubDAO;

@Controller
@RequestMapping(value = "/")
public class ClubController {

	@Autowired
	ClubDAO clubDAO;

	@ResponseBody
	@GetMapping("clubs")
	public List<Club> createRestGET() {

		List<Club> clubes = clubDAO.findAll();

		return clubes;
	}

	@ResponseBody
	@GetMapping("clubs/club")
	public Club findClubByName(@RequestParam String name) throws Exception {

		if (!StringUtils.isEmpty(name)) {
			Club club = clubDAO.findByName(name);
			if (club != null)
				return club;
			else
				throw new Exception("Club no encontrado: " + name);

		} else {
			throw new Exception("Por favor ingresa el nombre del club");
		}
	}

	@GetMapping("clubs/club/list")
	public String findClubByNameGET(@RequestParam String name, Model model) throws Exception {

		model.addAttribute("list", Arrays.asList(clubDAO.findByName(name)));
		return "clubs/clubs";

	}

	@RequestMapping("clubs/all")
	public String createGET(Model model) {

		model.addAttribute("list", clubDAO.findAll());

		return "clubs/clubs";
	}

	@ResponseBody
	@PostMapping("clubs/create")
	private Club create(@RequestParam String name, @RequestParam String address, @RequestParam double rate,
			@RequestParam String music) throws Exception {

		if (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(address) && !StringUtils.isEmpty(String.valueOf(rate))
				&& !StringUtils.isEmpty(music)) {

			Club club = new Club();
			club.setName(name);
			club.setAddress(address);
			club.setRate(rate);
			club.setKindOfMusic(music);

			return clubDAO.save(club);

		} else {
			throw new Exception("Faltan parámetros necesarios");
		}

	}
	
	@ResponseBody
	@DeleteMapping("clubs/delete-club")
	private String deleteClubByName(@RequestParam String name) throws Exception {

		
		if (!StringUtils.isEmpty(name)) {

			Club club = clubDAO.findByName(name);
			
			if(club != null) {
				
				clubDAO.delete(club);
				return "Club '" + name + "' eliminado con éxito";
			}
			else
				throw new Exception("Club no encontrado");

		} else {
			throw new Exception("Ingrese el nombre del Club a eliminar por favor");
		}

	}
	
	@ResponseBody
	@PutMapping("clubs/change-name")
	public Club changeClubName(@RequestParam String oldName, @RequestParam String newName) throws Exception {
		
		if(!StringUtils.isEmpty(oldName) && !StringUtils.isEmpty(newName)) {
			
			if(!oldName.equalsIgnoreCase(newName)) {
				
				Club club = clubDAO.findByName(oldName);
				
				if(clubDAO.findByName(newName) == null) {
					
					club.setName(newName);
					clubDAO.save(club);
					return club;
					
				} else {
					throw new Exception("Club ya ingresado con ese nombre");
				}
				
			} else {
				throw new Exception("Ingresa un nombre distinto");
			}
			
		} else {
			throw new Exception("Faltan parámetros necesarios");
		}
	}

}
