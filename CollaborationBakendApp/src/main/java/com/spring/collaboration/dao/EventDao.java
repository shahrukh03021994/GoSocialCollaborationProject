package com.spring.collaboration.dao;

import java.util.List;

import com.spring.collaboration.domain.Event;

public interface EventDao {

public boolean addEvent(Event event);
	
	public boolean deleteEvent(int id);
	
	public Event getEvent(int id);
	
	public List<Event> listEvent();
}
