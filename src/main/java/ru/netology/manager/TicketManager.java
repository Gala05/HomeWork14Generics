package ru.netology.manager;

import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;
import java.util.Comparator;

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

    public Ticket[] findAll(String fromAirport, String toAirport, Comparator<Ticket> comparator) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : repo.findAll()) {
            if ((ticket.getDepartureAirport().equals(fromAirport)) && (ticket.getArrivalAirport().equals(toAirport))) {
                Ticket[] tmp = new Ticket[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
        }
        Arrays.sort(result, comparator);
        return result;
    }
}