package org.act.ticketingsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.act.ticketingsystem.dto.BaseResponseDTO;
import org.act.ticketingsystem.model.Ticket;
import org.act.ticketingsystem.service.TicketService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Operation(summary = "Create a new ticket", description = "This endpoint allows you to create a new ticket.")

    @PostMapping("/create")
    public BaseResponseDTO<Ticket> createTicket(@RequestBody Ticket ticket) {
        try {
            Ticket createdTicket = ticketService.createTicket(ticket);
            return new BaseResponseDTO<>(true, "Ticket created successfully.", createdTicket);
        } catch (Exception e) {
            return new BaseResponseDTO<>(false, "Failed to create ticket: " + e.getMessage(), null);
        }
    }


    @Operation(summary = "Get all tickets", description = "This endpoint fetches all tickets.")
    @GetMapping("/all")
    public BaseResponseDTO<List<Ticket>> getAllTickets(){
        try {
            List<Ticket> tickets = ticketService.getAllTickets();
            return new BaseResponseDTO<>(true, "Ticket fetched successfully.",tickets );
        }catch (Exception e){
            return new BaseResponseDTO<>(false, "Something error: " + e.getMessage(), null);
        }
    }

    @GetMapping("/tickets")
    public ResponseEntity<BaseResponseDTO<Page<Ticket>>> getTickets(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Ticket> tickets = ticketService.getTickets(pageable);

        BaseResponseDTO<Page<Ticket>> response = new BaseResponseDTO<>(
                true,
                "Tickets fetched successfully",
                tickets
        );

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get a ticket by ID", description = "Fetch a ticket by its ID")
    @ApiResponse(responseCode = "200", description = "Ticket found")
    @GetMapping("/single")
    public BaseResponseDTO<Optional<Ticket>> getTicketById(@RequestParam Long ticketId) {
        try {
            Optional<Ticket> ticket = ticketService.getTicketById(ticketId);
            return new BaseResponseDTO<>(true, "Ticket fetched successfully.", ticket);
        } catch (Exception e) {
            return new BaseResponseDTO<>(false, "Failed to fetch ticket: " + e.getMessage(), null);
        }
    }

    @PutMapping("/update")
    public BaseResponseDTO<Ticket> updateTicket(@RequestParam Long ticketId, @RequestBody Ticket ticket) {
        try {
            Ticket updatedTicket = ticketService.updateTicket(ticketId, ticket);
            return new BaseResponseDTO<>(true, "Ticket updated successfully.", updatedTicket);
        } catch (Exception e) {
            return new BaseResponseDTO<>(false, "Failed to update ticket: " + e.getMessage(), null);
        }
    }

    @DeleteMapping("/delete")
    public BaseResponseDTO<String> deleteTicket(@RequestParam Long ticketId) {
        try {
            ticketService.deleteTicket(ticketId);
            return new BaseResponseDTO<>(true, "Ticket deleted successfully.", null);
        } catch (Exception e) {
            return new BaseResponseDTO<>(false, "Failed to delete ticket: " + e.getMessage(), null);
        }
    }

}
