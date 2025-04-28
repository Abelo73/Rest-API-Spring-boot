package org.act.ticketingsystem.controller;

import org.act.ticketingsystem.dto.BaseResponseDTO;
import org.act.ticketingsystem.model.Priority;
import org.act.ticketingsystem.model.Status;
import org.act.ticketingsystem.model.Ticket;
import org.act.ticketingsystem.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TicketControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TicketService ticketService;

    @InjectMocks
    private TicketController ticketController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(ticketController).build();
    }

    @Test
    public void testCreateTicket() throws Exception {
        Ticket ticket = new Ticket();
        ticket.setTitle("Test Ticket");
        ticket.setDescription("Test Description");
        ticket.setPriority(Priority.HIGH);
        ticket.setStatus(Status.OPEN);

        when(ticketService.createTicket(ticket)).thenReturn(ticket);

        mockMvc.perform(post("/api/tickets/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Test Ticket\", \"description\":\"Test Description\", \"priority\":\"HIGH\", \"status\":\"OPEN\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Ticket created successfully."));
    }

    @Test
    public void testGetAllTickets() throws Exception {
        mockMvc.perform(get("/api/tickets/tickets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Tickets fetched successfully"));
    }

    @Test
    public void testGetTicketById() throws Exception {
        mockMvc.perform(get("/api/tickets/single?ticketId=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Ticket fetched successfully."));
    }

    @Test
    public void testUpdateTicket() throws Exception {
        Ticket ticket = new Ticket();
        ticket.setTitle("Updated Ticket");
        ticket.setDescription("Updated Description");
        ticket.setPriority(Priority.HIGH);
        ticket.setStatus(Status.PROGRESS);

        when(ticketService.updateTicket(1L, ticket)).thenReturn(ticket);

        mockMvc.perform(put("/api/tickets/update?ticketId=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated Ticket\", \"description\":\"Updated Description\", \"priority\":\"LOW\", \"status\":\"PROGRESS\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Ticket updated successfully."));
    }

    @Test
    public void testDeleteTicket() throws Exception {
        mockMvc.perform(delete("/api/tickets/delete?ticketId=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Ticket deleted successfully."));
    }
}
