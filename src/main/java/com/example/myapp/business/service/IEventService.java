package com.example.myapp.business.service;



import com.example.myapp.persistence.model.Evenement;

import java.util.List;

public interface IEventService {

    List<Evenement> getEvents();

   Evenement getEventById(Long id);

    Evenement addEvent(Evenement event);

   Evenement updateEventById(Evenement event, Long id);

   public void deleteEventById(Long id);
}
