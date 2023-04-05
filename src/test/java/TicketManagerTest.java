import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.domain.TicketByPriceAssComparator;
import ru.netology.domain.TicketByTimeAssComparator;
import ru.netology.manager.TicketManager;
import ru.netology.repository.NotFoundException;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;

public class TicketManagerTest {
    Ticket ticket1 = new Ticket(1, 5_600, "CSV", "SVO", 90);
    Ticket ticket2 = new Ticket(2, 5_100, "CSV", "LED", 125);
    Ticket ticket3 = new Ticket(3, 5_200, "KZN", "SVO", 95);
    Ticket ticket4 = new Ticket(4, 6_500, "KZN", "LED", 165);
    Ticket ticket5 = new Ticket(5, 4_500, "CSV", "LED", 130);
    Ticket ticket6 = new Ticket(6, 4_700, "CSV", "LED", 120);
    Ticket ticket7 = new Ticket(7, 10_700, "CSV", "LED", 128);
    Ticket ticket8 = new Ticket(8, 3_200, "CSV", "LED", 124);
    Ticket ticket9 = new Ticket(9, 5_700, "CSV", "LED", 119);

    TicketRepository repo = new TicketRepository();
    TicketManager manager = new TicketManager(repo);

    @Test
    public void getRepositoryTest() {
        manager.addTicket(ticket1);
        manager.addTicket(ticket2);
        manager.addTicket(ticket3);
        manager.addTicket(ticket4);

        TicketRepository expected = repo;
        TicketRepository actual = manager.getRepo();

        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void addTicketTest() {
        manager.addTicket(ticket1);
        manager.addTicket(ticket2);
        manager.addTicket(ticket3);
        manager.addTicket(ticket4);

        Ticket[] expected = {ticket1, ticket2, ticket3, ticket4};
        Ticket[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void findAllTicketSortByPriceTest() {
        TicketByPriceAssComparator priceAssComparator = new TicketByPriceAssComparator();
        manager.addTicket(ticket1);
        manager.addTicket(ticket2);
        manager.addTicket(ticket3);
        manager.addTicket(ticket4);
        manager.addTicket(ticket5);
        manager.addTicket(ticket6);
        manager.addTicket(ticket7);
        manager.addTicket(ticket8);
        manager.addTicket(ticket9);

        Ticket[] expected = {ticket8, ticket5, ticket6, ticket2, ticket9, ticket7};
        Ticket[] actual = manager.findAll("CSV", "LED", priceAssComparator);
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void findAllTicketSortByTimeTest() {
        TicketByTimeAssComparator timeAssComparator = new TicketByTimeAssComparator();
        manager.addTicket(ticket1);
        manager.addTicket(ticket2);
        manager.addTicket(ticket3);
        manager.addTicket(ticket4);
        manager.addTicket(ticket5);
        manager.addTicket(ticket6);
        manager.addTicket(ticket7);
        manager.addTicket(ticket8);
        manager.addTicket(ticket9);

        Ticket[] expected = {ticket9, ticket6, ticket8, ticket2, ticket7, ticket5};
        Ticket[] actual = manager.findAll("CSV", "LED", timeAssComparator);
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void findOneTicketSortByTimeTest() {
        TicketByTimeAssComparator timeAssComparator = new TicketByTimeAssComparator();
        manager.addTicket(ticket1);
        manager.addTicket(ticket2);
        manager.addTicket(ticket3);
        manager.addTicket(ticket4);
        manager.addTicket(ticket5);
        manager.addTicket(ticket6);
        manager.addTicket(ticket7);
        manager.addTicket(ticket8);
        manager.addTicket(ticket9);

        Ticket[] expected = {ticket4};
        Ticket[] actual = manager.findAll("KZN", "LED", timeAssComparator);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void findOneTicketSortByPriceTest() {
        TicketByPriceAssComparator priceAssComparator = new TicketByPriceAssComparator();
        manager.addTicket(ticket1);
        manager.addTicket(ticket2);
        manager.addTicket(ticket3);
        manager.addTicket(ticket4);
        manager.addTicket(ticket5);
        manager.addTicket(ticket6);
        manager.addTicket(ticket7);
        manager.addTicket(ticket8);
        manager.addTicket(ticket9);

        Ticket[] expected = {ticket4};
        Ticket[] actual = manager.findAll("KZN", "LED", priceAssComparator);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void findNoTicketSortByTimeTest() {
        TicketByTimeAssComparator timeAssComparator = new TicketByTimeAssComparator();
        manager.addTicket(ticket1);
        manager.addTicket(ticket2);
        manager.addTicket(ticket3);
        manager.addTicket(ticket4);
        manager.addTicket(ticket5);
        manager.addTicket(ticket6);
        manager.addTicket(ticket7);
        manager.addTicket(ticket8);
        manager.addTicket(ticket9);

        Assertions.assertThrows(NotFoundException.class, () ->
                manager.findAll("KZN", "SMR", timeAssComparator)
                );
    }

    @Test
    public void findNoTicketSortByPriceTest() {
        TicketByPriceAssComparator priceAssComparator = new TicketByPriceAssComparator();
        manager.addTicket(ticket1);
        manager.addTicket(ticket2);
        manager.addTicket(ticket3);
        manager.addTicket(ticket4);
        manager.addTicket(ticket5);
        manager.addTicket(ticket6);
        manager.addTicket(ticket7);
        manager.addTicket(ticket8);
        manager.addTicket(ticket9);

        Assertions.assertThrows(NotFoundException.class, () ->
                manager.findAll("KZN", "SMR", priceAssComparator)
        );
    }
}