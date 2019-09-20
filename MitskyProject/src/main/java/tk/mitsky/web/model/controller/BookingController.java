package tk.mitsky.web.model.controller;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tk.mitsky.web.dao.BookingDAO;
import tk.mitsky.web.model.Booking;
import tk.mitsky.web.model.Club;


@Controller
@RequestMapping(value = "/")
public class BookingController {
	
	@Autowired
	BookingDAO bookingDAO;


	@ResponseBody
	@RequestMapping("bookings")
	public List<Booking> createRestGET() {

		List<Booking> bookings = bookingDAO.findAll();

		return bookings;
	}

	@RequestMapping("bookings/all")
	public String createGET(Model model) {

		model.addAttribute("list", bookingDAO.findAll());

		return "bookings/bookings";
	}
	
	@ResponseBody
	@GetMapping("bookings/booking")
	public Booking findBookingById(@RequestParam Long id) throws Exception {

		if (!StringUtils.isEmpty(id.toString())) {
			
			Booking book = bookingDAO.findByid(id);
			
			if (book != null)
				return book;
			else
				throw new Exception("Reserva no encontrada: " + id);

		} else {
			throw new Exception("Por favor ingresa el ID de la RESERVA");
		}
	}

	@GetMapping("bookings/booking/list")
	public String findClubByNameGET(@RequestParam Long id, Model model) throws Exception {

		model.addAttribute("list", Arrays.asList(bookingDAO.findByid(id)));
		return "bookings/bookings";

	}
	
	@ResponseBody
	@PostMapping("bookings/create")
	public Booking createNewBookingPost(
			@RequestParam Timestamp date,
			@RequestParam String club) throws Exception {

		if (!StringUtils.isEmpty(date.toString()) && !StringUtils.isEmpty(club.toString())) {


			if (bookingDAO.findByDate(date) != null) {
				throw new Exception("Reserva a esa fecha y hora ya realizada");
			}
			
			Club newClub = new Club();
			newClub.setName(club);
			

			// CREATE A NEW USER OBJECT
			Booking booking = new Booking();
			booking.setDate(date);
			booking.setClub(newClub);

			return bookingDAO.save(booking);

		} else {
			throw new Exception("Debe llenar todos los campos");
		}
	}

}
