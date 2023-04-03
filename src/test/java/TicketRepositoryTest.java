import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.repository.AlreadyExistsException;
import ru.netology.repository.NotFoundException;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;

public class TicketRepositoryTest {
    Ticket ticket1 = new Ticket(254, 5_600, "CSV", "SVO", 90);
    Ticket ticket2 = new Ticket(576, 4_100, "CSV", "LED", 125);
    Ticket ticket3 = new Ticket(475, 5_200, "KZN", "SVO", 95);
    Ticket ticket4 = new Ticket(981, 6_200, "KZN", "LED", 165);
    Ticket ticket6 = new Ticket(472, 6_200, "MOW", "SVX", 254);
    @Test
    public void allProductsTest(){
        TicketRepository repo = new TicketRepository();
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);
        repo.save(ticket4);

        Ticket[] expected = { ticket1, ticket2, ticket3, ticket4 };
        Ticket[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void removeByIdTest(){
        TicketRepository repo = new TicketRepository();
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);
        repo.save(ticket4);
        repo.removeById(475);

        Ticket[] expected = { ticket1, ticket2, ticket4 };
        Ticket[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void findByIdTest(){
        TicketRepository repo = new TicketRepository();
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);
        repo.save(ticket4);

        Ticket[] expected = { ticket2 };
        Ticket[] actual = repo.findById(576);

        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void removeNonIdTest(){
        TicketRepository repo = new TicketRepository();
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);
        repo.save(ticket4);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(123);
        });
    }
    @Test
    public void saveProductsTestIdAlreadyExist(){
        TicketRepository repo = new TicketRepository();
        repo.save(ticket1);
        Ticket ticket5 = new Ticket(254, 10_400, "SVX", "MOW", 155);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.save(ticket5);
        });
    }

    @Test
    public void getProductTest() {
        Ticket ticket = new Ticket(254, 10_400, "SVX", "MOW", 155);
        Assertions.assertEquals(254, ticket.getId());
        Assertions.assertEquals(10_400, ticket.getAmount());
        Assertions.assertEquals("SVX", ticket.getDepartureAirport());
        Assertions.assertEquals("MOW", ticket.getArrivalAirport());
        Assertions.assertEquals(155, ticket.getTravelTime());
    }
    @Test
    public void compareTest(){
        TicketRepository repo = new TicketRepository();
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);
        repo.save(ticket4);
        repo.save(ticket6);
        Ticket[] actual = repo.findAll();
        Ticket[] expected = { ticket2, ticket3, ticket1, ticket4, ticket6 };
        Arrays.sort(actual);
        Assertions.assertArrayEquals(expected, actual);
    }
}
