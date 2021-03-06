package org.callistasoftware.netcare.video.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="nc_video_meeting")
public class VideoMeetingEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column
	private String description;
	
	@Column(name="start_time", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDateTime;
	
	@Column(name="end_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDateTime;
	
	@Column
	private boolean started;
	
	@ManyToOne(optional=false)
	private CareUnitEntity careUnit;
	
	@ManyToOne(optional=false)
	private CareGiverEntity createdBy;
	
	@OneToMany(mappedBy="booking", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
	private List<VideoParticipantEntity> participants;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
	private List<MeetingNoteEntity> notes;

	VideoMeetingEntity() {}
	
	VideoMeetingEntity(final String name, final Date start, final Date end, final CareGiverEntity createdBy) {
		this.setName(name);
		this.setParticipants(new ArrayList<VideoParticipantEntity>());
		this.setStartDateTime(start);
		this.setStarted(false);
		
		if (end == null) {
			this.setEndDateTime(new Date(start.getTime() + (60000 * 60)));
		} else {
			this.setEndDateTime(end);
		}
		
		this.setCreatedBy(createdBy);
		this.setCareUnit(createdBy.getCareUnit());
	}
	
	public static VideoMeetingEntity newEntity(final String name, final Date start, final Date end, final CareGiverEntity createdBy) {
		return new VideoMeetingEntity(name, start, end, createdBy);
	}
	
	public Long getId() {
		return id;
	}

	void setId(Long id) {
		this.id = id;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date start) {
		this.startDateTime = start;
	}

	public Date getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Date end) {
		this.endDateTime = end;
	}

	public boolean isStarted() {
		return started;
	}

	void setStarted(boolean started) {
		this.started = started;
	}
	
	public void addParticipant(final UserEntity user, final boolean owner) {
		this.getParticipants().add(new VideoParticipantEntity(this, user, owner));
	}
	
	public List<VideoParticipantEntity> getParticipants() {
		return this.participants;
	}
	
	void setParticipants(final List<VideoParticipantEntity> participants) {
		this.participants = participants;
	}

	public CareGiverEntity getCreatedBy() {
		return createdBy;
	}

	void setCreatedBy(CareGiverEntity createdBy) {
		this.createdBy = createdBy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CareUnitEntity getCareUnit() {
		return careUnit;
	}

	void setCareUnit(CareUnitEntity careUnit) {
		this.careUnit = careUnit;
	}
	
	public void addNote(final String note, final UserEntity creator) {
		this.getNotes().add(MeetingNoteEntity.newEntity(note, creator));
	}

	public List<MeetingNoteEntity> getNotes() {
		return notes;
	}

	void setNotes(List<MeetingNoteEntity> notes) {
		this.notes = notes;
	}
	
	public boolean isOngoing() {
		final long n = System.currentTimeMillis();
		return n >= this.getStartDateTime().getTime() && n <= this.getEndDateTime().getTime();
	}
	
	public boolean isUserParticipant(final UserEntity user) {
		for (final VideoParticipantEntity ent : this.getParticipants()) {
			if (ent.getUser().getId().equals(user.getId())) {
				return true;
			}
		}
		
		return false;
	}
}
