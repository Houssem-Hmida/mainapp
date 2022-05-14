package com.example.myapp.business.service.impl;

import com.example.myapp.business.service.IEventService;
import com.example.myapp.persistence.model.Evenement;
import com.example.myapp.persistence.repository.EventRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

@Transactional
@AllArgsConstructor
@Slf4j
@Service
public class EventService implements IEventService {

    @Autowired
    EventRepository eventRepository;

    @Override
    public List<Evenement> getEvents(){
        try{
            log.info("Fetching all events ");
            return eventRepository.findAll();
        } catch (Exception e){
            throw new IllegalStateException("Error EventService in method getEvents " + e.toString());
        }
    }

    @Override
    public Evenement getEventById(Long id){
        try {
            if (id == null)
                return new Evenement();
            Evenement e = eventRepository.findEventById(id);
            if (e == null)
                return new Evenement();
            log.info("Fetching event with id :{} ",id);
            return e;
        } catch (Exception e){
            throw new IllegalStateException("Error EventService in method getEventById :: " + e.toString());
        }

    }

    @Override
    public Evenement addEvent(Evenement event) {
        try {
            Evenement objNomUnique = eventRepository.findEventByTitle(event.getTitle());

            if ( objNomUnique != null)
                throw new IllegalStateException("Evenement title token");

            log.info("Saving new event {} to the databse ",event.getTitle());

            return eventRepository.save(event);
        } catch (Exception e) {
            throw new IllegalStateException("Error EventService in method addEvent :: " + e.toString());
        }
    }

    @Override
    public Evenement updateEventById(Evenement event,Long id) {
        try {
            Evenement upevent = eventRepository.findEventById(event.getId());
            upevent.setTitle(event.getTitle());
            upevent.setStart(event.getStart());
            upevent.setEnd(event.getEnd());
            upevent.setId(id);

            log.info("updating  event {} to the databse ",event.getTitle());

            return eventRepository.save(upevent);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error EventService in method updateEventById :: " + e.toString());
        }
    }

    @Override
    public void deleteEventById(Long id) {
        try {
            log.info("Deleting Evenement with id {}  ",id);
            eventRepository.deleteById(id);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error EventService in method deleteEventById :: " + e.toString());
        }
    }

}
