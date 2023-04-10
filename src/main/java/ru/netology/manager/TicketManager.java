package ru.netology.manager;

import ru.netology.domain.Ticket;
import ru.netology.repository.NotFoundException;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;

public class TicketManager {
    private TicketRepository repo;

    public TicketManager(TicketRepository repo) {
        this.repo = repo;
    }

    public TicketRepository getRepo() {
        return repo;
    }

    public void addTicket(Ticket newTicket) {
        repo.save(newTicket);
    }

    public Ticket[] аllTickets(String fromAirport, String toAirport) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : repo.findAll()) {
            if (matches(ticket, fromAirport, toAirport)) {
                Ticket[] tmp = new Ticket[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
        }
//        if (result.length == 0) {
//            throw new NotFoundException(
//                    "One of the elements " + fromAirport + " or " + toAirport + " was not found"
//            );
//        }
        Arrays.sort(result);
        return result;
    }
    public boolean matches(Ticket ticket, String fromAirport, String toAirport) {
        if (ticket.getDepartureAirport().equals(fromAirport)) {
            if (ticket.getArrivalAirport().equals(toAirport)) {
                return true;
            }
            return false;
        }
        return false;
    }

}