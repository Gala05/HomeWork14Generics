package ru.netology.domain;

import java.util.Comparator;

public class TicketByPriceAssComparator implements Comparator<Ticket> {
    @Override
    public int compare(Ticket o1, Ticket o2) {
        if (o1.getAmount() < o2.getAmount()) {
            return -1;
        } else if (o1.getAmount() > o2.getAmount()) {
            return 1;
        } else {
            return 0;
        }
    }
}