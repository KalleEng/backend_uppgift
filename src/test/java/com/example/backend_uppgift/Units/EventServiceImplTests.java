package com.example.backend_uppgift.Units;

import com.example.backend_uppgift.Services.EventService;
import com.example.backend_uppgift.Services.Impl.EventServiceImpl;
import com.example.backend_uppgift.models.Event;
import com.example.backend_uppgift.repositories.EventRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class EventServiceImplTests {

    @Mock
    private EventService eventService;
    @InjectMocks
    private EventServiceImpl eventServiceImpl;

    private Event event;

    @BeforeEach
    void setUp() {
        event = new Event(1, "Cleaning", LocalDateTime.now(), "John Doe");
    }

    @Test
    void testSaveEvent() {
        eventService.saveEvent(event);
        verify(eventService, times(1)).saveEvent(event);
    }

    @Test
    void testSaveAll() {
        List<Event> events = Arrays.asList(event, new Event(2, "Inspection", LocalDateTime.now(), "Jane Doe"));
        eventService.saveAll(events);
        verify(eventService, times(1)).saveAll(events);
    }

    @Test
    void testGetEventsByRoomId() {
        List<Event> events = Arrays.asList(event);
        when(eventService.getEventsByRoomId(1)).thenReturn(events);

        List<Event> result = eventService.getEventsByRoomId(1);
        assertEquals(1, result.size());
        assertEquals(event, result.get(0));

        verify(eventService, times(1)).getEventsByRoomId(1);
    }
}
