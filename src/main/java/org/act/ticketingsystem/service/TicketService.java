package org.act.ticketingsystem.service;

import org.act.ticketingsystem.model.Ticket;
import org.act.ticketingsystem.repository.TicketRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    // Create Ticket

    public Ticket createTicket(Ticket ticket){
      return ticketRepository.save(ticket);
    }


    // All Tickets
    public List<Ticket> getAllTickets(){
        return ticketRepository.findAll();
    }

    // Ticket by id

    public Optional<Ticket> getTicketById(Long ticketId){
        Optional ticket = ticketRepository.findById(ticketId);
        if (ticket.isEmpty()){
            return null;
        }
        return ticket;
    }

    public Page<Ticket> getTickets(Pageable pageable) {
        return ticketRepository.findAll(pageable);
    }

    public Ticket updateTicket(Long ticketId, Ticket updatedTicket) {
        return ticketRepository.findById(ticketId)
                .map(existingTicket -> {
                    existingTicket.setTitle(updatedTicket.getTitle());
                    existingTicket.setDescription(updatedTicket.getDescription());
                    existingTicket.setPriority(updatedTicket.getPriority());
                    existingTicket.setStatus(updatedTicket.getStatus());
                    existingTicket.setAssignedTo(updatedTicket.getAssignedTo());
                    existingTicket.setAssignedBy(updatedTicket.getAssignedBy());
                    existingTicket.setStartDate(updatedTicket.getStartDate());
                    existingTicket.setDueDate(updatedTicket.getDueDate());
                    existingTicket.setUpdatedAt(LocalDateTime.now());
                    return ticketRepository.save(existingTicket);
                })
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + ticketId));
    }

    public void deleteTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + ticketId));
        ticketRepository.delete(ticket);
    }
}
