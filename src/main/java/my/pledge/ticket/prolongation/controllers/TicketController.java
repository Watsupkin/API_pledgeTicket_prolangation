package my.pledge.ticket.prolongation.controllers;

import my.pledge.ticket.prolongation.models.Ticket;
import my.pledge.ticket.prolongation.models.User;
import my.pledge.ticket.prolongation.repo.TicketsRepo;
import my.pledge.ticket.prolongation.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class TicketController {
    @Autowired
    private TicketsRepo ticketsRepo;
    @Autowired
    private UsersRepo usersRepo;


    @GetMapping("/add_ticket/{id}")
    public String getTicketForm(@PathVariable(value = "id") long userId,
                                Model model){
        model.addAttribute("userId", userId);
        return "add_ticket";
    }

    @PostMapping("/add_ticket")
    public String addTicket(@RequestParam long userId,
                            @RequestParam String goodName,
                            @RequestParam double goodPrice,
                            @RequestParam int pledgeDays,
                            Model model){
        Ticket ticket = new Ticket(goodName,goodPrice,pledgeDays,userId);
        ticketsRepo.save(ticket);
        User user = usersRepo.findById(userId).get();
        model.addAttribute("user", user);
        return "lk.html";
    }

    @GetMapping("/tickets/{id}")
    public String getUsersTickets(@PathVariable(name = "id") long userId,
                                  Model model){
        Iterable<Ticket> tickets = ticketsRepo.findAll();
        List<Ticket> usersTickets = new ArrayList<>();
        for (Ticket ticket : tickets) {
            if (ticket.getClientID() == userId){
                usersTickets.add(ticket);
            }
        }
        model.addAttribute("tickets", usersTickets);
        return "tickets";
    }
    @GetMapping("/update/{id}")
    public String getUpdate(@PathVariable(name = "id") long ticketId,
                                  Model model){
        Ticket ticket = ticketsRepo.findById(ticketId).get();
        model.addAttribute("ticketId", ticket.getId());
        return "update";
    }
    @PostMapping("/update")
    public String updateTicket(@RequestParam long ticketId,
                               @RequestParam int days,
                               Model model){
        Ticket ticket = ticketsRepo.findById(ticketId).get();
        ticket.setPledgeDays(ticket.getPledgeDays() + days);
        ticketsRepo.save(ticket);
        User user;
        Iterable<User> users = usersRepo.findAll();
        for (User u : users) {
            if (u.getId() == ticket.getClientID()) {
                user = u;
                model.addAttribute("user", user);
                return "lk";
            }
        }
        return "redirect:/";
    }
}
