package ru.netology.repository;

import ru.netology.domain.Ticket;

public class TicketRepository {
    private Ticket[] tickets = new Ticket[0];

    public Ticket[] getTickets() {
        return tickets;
    }

    public Ticket[] findAll() {
        Ticket[] all = getTickets();
        return all;
    }

    public void save(Ticket newTicket) {
    if (findById(newTicket.getId()) == null) {
        Ticket[] tmp = new Ticket[tickets.length + 1];
        for (int i = 0; i < tickets.length; i++) {
            tmp[i] = tickets[i];
        }
        tmp[tmp.length - 1] = newTicket;
        tickets = tmp;
    } else {
        throw new AlreadyExistsException(
                "ID in added product ("+ newTicket.getId() + ") is already exist"
        );
    }
}

    public Ticket[] findById(int id) { // возвращает объект по идентификатору
        Ticket[] result = new Ticket[1];
        for (Ticket ticket : tickets) {
            if (ticket.getId() == id) {
                result = new Ticket[]{ticket};
                return result;
            }
        }
        return null;
    }

    public void removeById(int id) {
        if (this.findById(id) == null) {
            throw new NotFoundException(
                    "Element with id: " + id + " not found"
            );
        }
        Ticket[] tmp = new Ticket[tickets.length - 1];
        int copyToIndex = 0;
        for (Ticket ticket : tickets) {
            if (ticket.getId() != id) {
                tmp[copyToIndex] = ticket;
                copyToIndex++;
            }
        }
        tickets = tmp;
    }
}