import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.domain.TicketByPriceAssComparator;
import ru.netology.domain.TicketByTimeAssComparator;
import ru.netology.repository.AlreadyExistsException;
import ru.netology.repository.NotFoundException;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;

public class TicketRepositoryTest {
    Ticket ticket1 = new Ticket(1, 5_600, "CSV", "SVO", 90);
    Ticket ticket2 = new Ticket(2, 4_100, "CSV", "LED", 125);
    Ticket ticket3 = new Ticket(3, 5_200, "KZN", "SVO", 95);
    Ticket ticket4 = new Ticket(4, 6_200, "KZN", "LED", 254);
    Ticket ticket5 = new Ticket(5, 6_200, "MOW", "SVX", 168);
    Ticket ticket6 = new Ticket(6, 10_200, "SVX", "MOW", 95);
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
        repo.removeById(3);

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
        Ticket[] actual = repo.findById(2);

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
        Ticket ticket = new Ticket(1, 10_400, "SVX", "MOW", 155);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.save(ticket);
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
    public void sortingByTimeEqualValuesTest(){
        TicketByTimeAssComparator timeAssComparator = new TicketByTimeAssComparator();
        Ticket[] tickets = { ticket1, ticket2, ticket3, ticket4, ticket5, ticket6 };
        Arrays.sort(tickets, timeAssComparator);
        Ticket[] expected = { ticket1, ticket3, ticket6, ticket2, ticket5, ticket4 };

        Assertions.assertArrayEquals(expected, tickets);
    }

    @Test
    public void sortingByPriceTest(){
        TicketByPriceAssComparator priceAssComparator = new TicketByPriceAssComparator();
        Ticket[] tickets = { ticket1, ticket2, ticket3, ticket4, ticket5 };
        Arrays.sort(tickets, priceAssComparator);
        Ticket[] expected = { ticket2, ticket3, ticket1, ticket4, ticket5 };

        Assertions.assertArrayEquals(expected, tickets);
    }
}
