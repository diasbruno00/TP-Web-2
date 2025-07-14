package br.edu.ufop.web.ticket.sales.controllers;

import br.edu.ufop.web.ticket.sales.dtos.events.CreateEventsDTO;

import br.edu.ufop.web.ticket.sales.dtos.events.DeleteEventsDTO;
import br.edu.ufop.web.ticket.sales.dtos.events.UpdateEventsDTO;
import br.edu.ufop.web.ticket.sales.service.EventsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/event")
public class EventsController {

    private final EventsService eventsService;

    @PostMapping
    public ResponseEntity<CreateEventsDTO> saveEvent(@RequestBody CreateEventsDTO createEventsDTO) {

        CreateEventsDTO eventsDTO = eventsService.saveEvent(createEventsDTO);

        return ResponseEntity.ok(eventsDTO);

    }

    @GetMapping
    public ResponseEntity<List<CreateEventsDTO>> getAllEvents() {

        List<CreateEventsDTO> eventsList = eventsService.getAllEvents();

        if (eventsList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(eventsList);
    }
    @DeleteMapping
    public ResponseEntity<Object> deleteEvent(@RequestBody DeleteEventsDTO deleteEventsDTO) {

        eventsService.deteleEvents(deleteEventsDTO);

        return ResponseEntity.ok("Event deleted successfully");
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<CreateEventsDTO> getEventById(@PathVariable String eventId) {

        if (Objects.isNull(eventId) || eventId.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        CreateEventsDTO eventsDTO = eventsService.getEventById(eventId);

        if (Objects.isNull(eventsDTO)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(eventsDTO);
    }

    @PutMapping
    public ResponseEntity<UpdateEventsDTO> updateEvent(@RequestBody UpdateEventsDTO updateEventsDTO) {
        if (Objects.isNull(updateEventsDTO) || Objects.isNull(updateEventsDTO.getId())) {
            return ResponseEntity.badRequest().build();
        }

        UpdateEventsDTO updatedEvent = eventsService.updateEvent(updateEventsDTO);

        if (Objects.isNull(updatedEvent)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedEvent);
    }
}
