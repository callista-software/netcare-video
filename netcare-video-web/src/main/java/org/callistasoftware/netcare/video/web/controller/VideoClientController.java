package org.callistasoftware.netcare.video.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.callistasoftware.netcare.video.core.api.VideoBooking;
import org.callistasoftware.netcare.video.core.exception.ServiceException;
import org.callistasoftware.netcare.video.core.spi.VideoBookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("")
public class VideoClientController extends ControllerSupport {
	
	private static final Logger log = LoggerFactory.getLogger(VideoClientController.class);
	
	@Autowired
	private VideoBookingService service;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String display() {
		return "redirect:/web/dashboard";
	}
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String displayHome() {
		return "redirect:/web/dashboard";
	}
	
	@RequestMapping(value="/start", method=RequestMethod.GET)
	public String displayStart() {
		return "redirect:/web/dashboard";
	}
	
	@RequestMapping(value="/dashboard", method=RequestMethod.GET)
	public String displayDashboard(final Model m) {
		getBookings(m);
		return "dashboard";
	}
	
	@RequestMapping(value="/bookings", method=RequestMethod.GET)
	public String displayBookingPage() {
		return "bookings";
	}
	
	@RequestMapping(value="/bookings/{booking}", method=RequestMethod.GET)
	public String displayCreateBooking(@PathVariable("booking") final String booking, final Model m) {
		m.addAttribute("booking", booking);
		return "bookings";
	}
	
	@RequestMapping(value="/video", method=RequestMethod.GET)
	public String displayConference(@RequestParam(value="booking") final Long bookingId, final Model m) {
		log.info("Display video conference with id {}", bookingId);
		
		log.debug("RTMP server url is: {}", this.service.getVideoServer());
		m.addAttribute("serverUrl", this.service.getVideoServer()); 
		
		final List<String> errors = new ArrayList<String>();
		try {
			final VideoBooking booking = this.service.loadVideoBooking(bookingId).getData();
			m.addAttribute("booking", booking);
			return "video";
		} catch (final ServiceException e) {
			errors.add(e.getMessage());
			
			m.addAttribute("errors", errors);
			getBookings(m);
			return "dashboard";
		}
	}
	
	@RequestMapping(value="notes", method=RequestMethod.GET)
	public String displayNotes(@RequestParam("meeting") final Long meeting) {
		return "notes";
	}
	
	private void getBookings(final Model m) {
		m.addAttribute("bookings", service.getBookingsForUser(getCurrentUser().getId(), true));
	}
}
