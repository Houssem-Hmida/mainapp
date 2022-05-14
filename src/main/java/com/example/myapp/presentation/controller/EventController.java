package com.example.myapp.presentation.controller;

import com.example.myapp.business.service.IEventService;
import com.example.myapp.persistence.model.Evenement;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/events")
@AllArgsConstructor
public class EventController {

    private final IEventService iEventService;



    @PreAuthorize("hasAnyRole('ROLE_RH','ROLE_USER','ROLE_MANAGER')")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Evenement> getListEvents() {
        try {
            return iEventService.getEvents();
        } catch (Exception e) {
            throw new IllegalStateException("Error EventController in method getListEvents :: " + e.toString());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_RH','ROLE_MANAGER')")
    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Evenement> getEventById(@PathVariable("id") Long id) {
        try {
           // iLogDataService.saveLogData(utilisateurService.currentUserName(),"Get Demand Num : "+id);
            Evenement event = iEventService.getEventById(id);
            return new ResponseEntity<>(event, HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error EventController in method getEventById :: " + e.toString());
        }
    }
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_RH','ROLE_MANAGER')")
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Evenement addEvent(@RequestBody Evenement event) {
        try {
         //   iLogDataService.saveLogData(utilisateurService.currentUserName(),"Add new Demand");
            return iEventService.addEvent(event);
        } catch (Exception e) {
            throw new IllegalStateException("Error EventController in method addEvent :: " + e.toString());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_RH','ROLE_MANAGER')")
    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Evenement> updateEventById(@RequestBody Evenement event, @PathVariable("id") Long id) {
        try {
         //   iLogDataService.saveLogData(utilisateurService.currentUserName(),"Update Demande Num : " +demande.getId());
            Evenement updateEvent = iEventService.updateEventById(event,id);
            return new ResponseEntity<>(updateEvent, HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error EventController in method updateEventById :: " + e.toString());
        }
    }
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_RH','ROLE_MANAGER')")
    @DeleteMapping(value = "delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteEventById(@PathVariable("id") Long id) {
        try {
          //  iLogDataService.saveLogData(utilisateurService.currentUserName(),"Delete Demande Num : "+id);
            iEventService.deleteEventById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error EventController in method deleteEventById :: " + e.toString());
        }
    }
}
