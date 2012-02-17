package org.callistasoftware.netcare.video.core.api;

import java.io.Serializable;
import java.util.Date;

public interface VideoBooking extends Serializable {

	/**
	 * The id of this video booking
	 * @return
	 */
	Long getId();
	
	/**
	 * The start date and time for this video booking
	 * @return
	 */
	Date getStart();
	
	/**
	 * The end date and time for this video booking
	 * @return
	 */
	Date getEnd();
	
	/**
	 * Whether the booking has been started by a user
	 * @return
	 */
	boolean isStarted();
}
