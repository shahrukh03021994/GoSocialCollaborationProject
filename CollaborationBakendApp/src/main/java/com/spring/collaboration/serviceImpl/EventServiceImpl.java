package com.spring.collaboration.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.collaboration.dao.EventDao;
import com.spring.collaboration.domain.Event;
import com.spring.collaboration.service.EventService;

@Service
public class EventServiceImpl implements EventService{

	@Autowired
	EventDao eventDao;
	public boolean addEvent(Event event) {
		// TODO Auto-generated method stub
		return eventDao.addEvent(event);
	}

	public boolean deleteEvent(int id) {
		// TODO Auto-generated method stub
		return eventDao.deleteEvent(id);
	}

	public Event getEvent(int id) {
		// TODO Auto-generated method stub
		return eventDao.getEvent(id);
	}

	public List<Event> listEvent() {
		// TODO Auto-generated method stub
		return eventDao.listEvent();
	}

}
