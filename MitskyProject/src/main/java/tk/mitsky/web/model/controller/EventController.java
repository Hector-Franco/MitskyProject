package tk.mitsky.web.model.controller;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tk.mitsky.web.dao.EventDAO;
import tk.mitsky.web.model.Event;

@Controller
@RequestMapping(value = "/")
public class EventController {

	@Autowired
	EventDAO eventDAO;

	@ResponseBody
	@GetMapping("events")
	public List<Event> createRestGET() {

		List<Event> eventos = eventDAO.findAll();

		return eventos;
	}

	@ResponseBody
	@GetMapping("events/event")
	public Event findEventByDate(@RequestParam String date) throws Exception {

		if (!StringUtils.isEmpty(date)) {
			Event event = eventDAO.findByEventDate(Date.valueOf(date));
			if (event != null)
				return event;
			else
				throw new Exception("Evento no encontrado en la fecha: '" + date + "'");

		} else {
			throw new Exception("Ingresa una fecha de EVENTO por favor");
		}
	}

	@GetMapping("events/event/list")
	public String findEventByDateGET(@RequestParam Date date, Model model) throws Exception {

		model.addAttribute("list", Arrays.asList(eventDAO.findByEventDate(date)));
		return "events/events";

	}

	@RequestMapping("events/all")
	public String createGET(Model model) {

		model.addAttribute("list", eventDAO.findAll());

		return "events/events";
	}

	@ResponseBody
	@PostMapping("events/create")
	private Event create(@RequestParam String name, @RequestParam String date) throws Exception {

		if (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(date)) {

			Event evento = new Event();
			evento.setName(name);
			evento.setEventDate(Date.valueOf(date));
			return eventDAO.save(evento);
		} else {
			throw new Exception("Faltan parámetros obligatorios");
		}
	}

	@ResponseBody
	@DeleteMapping("events/delete-event")
	public String deleteEventByName(@RequestParam String name) throws Exception {

		if (!StringUtils.isEmpty(name)) {

			Event event = eventDAO.findByName(name);

			if (event != null) {
				eventDAO.deleteById(event.getId());
				return "Evento: '" + name + " eliminado con éxito";
			} else {
				throw new Exception("No se encuentra un evento con el nombre: " + name);
			}
		} else {
			throw new Exception("Ingresa un nombre válido");
		}

	}

}
