package ru.netology.manager;

import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

public class TicketManager {
    private TicketRepository repo;
//    private String fromAirport;
//    private String toAirport;

    public TicketManager(TicketRepository repo) {
        this.repo = repo;
    }

    public TicketRepository getRepo() {
        return repo;
    }

//    public String getFromAirport() {
//        return fromAirport;
//    }
//
//    public String getToAirport() {
//        return toAirport;
//    }
    public void addTicket(Ticket newTicket) {
        repo.save(newTicket);
    }

    public Ticket[] AllTickets(String fromAirport, String toAirport) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : repo.findAll()) {
            if ((ticket.getDepartureAirport() == fromAirport)&&(ticket.getArrivalAirport() == toAirport)) {
                Ticket[] tmp = new Ticket[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
        }
        return result;
    }
}
//    public Product[] searchBy(String text) {
//        Product[] result = new Product[0];
//        for (Product product : repo.findAll()) {
//            if (product.matches(text)) {
//                Product[] tmp = new Product[result.length + 1];
//                for (int i = 0; i < result.length; i++) {
//                    tmp[i] = result[i];
//                }
//                tmp[tmp.length - 1] = product;
//                result = tmp;
//            }
//        }
//        return result;
//    }