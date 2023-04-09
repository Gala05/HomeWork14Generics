import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.manager.TicketManager;
import ru.netology.repository.NotFoundException;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;

public class TicketManagerTest {
    Ticket ticket1 = new Ticket(254, 5_600, "CSV", "SVO", 90);
    Ticket ticket2 = new Ticket(576, 5_100, "CSV", "LED", 125);
    Ticket ticket3 = new Ticket(475, 5_200, "KZN", "SVO", 95);
    Ticket ticket4 = new Ticket(981, 6_500, "KZN", "LED", 165);
    Ticket ticket5 = new Ticket(748, 4_500, "CSV", "LED", 125);
    Ticket ticket6 = new Ticket(971, 4_700, "CSV", "LED", 125);
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
    public void AllTicketWhenExistTest() {

        manager.addTicket(ticket1);
        manager.addTicket(ticket2);
        manager.addTicket(ticket3);
        manager.addTicket(ticket4);
        manager.addTicket(ticket5);
        manager.addTicket(ticket6);

        Ticket[] expected = {ticket5, ticket6, ticket2};
        Ticket[] actual = manager.аllTickets("CSV", "LED");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void AllTicketFindOneTicketTest() {

        manager.addTicket(ticket1);
        manager.addTicket(ticket2);
        manager.addTicket(ticket3);
        manager.addTicket(ticket4);
        manager.addTicket(ticket5);
        manager.addTicket(ticket6);

        Ticket[] expected = {ticket1};
        Ticket[] actual = manager.аllTickets("CSV", "SVO");
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void AllTicketFindNoTicketTest() {

        manager.addTicket(ticket1);
        manager.addTicket(ticket2);
        manager.addTicket(ticket3);
        manager.addTicket(ticket4);
        manager.addTicket(ticket5);
        manager.addTicket(ticket6);

        Assertions.assertThrows(NotFoundException.class,
                () -> manager.аllTickets("CSV", "SMR")
                );
    }
}



