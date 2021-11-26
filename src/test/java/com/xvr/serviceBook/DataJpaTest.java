package com.xvr.serviceBook;

import com.xvr.serviceBook.entity.Ticket;
import com.xvr.serviceBook.entity.Worker;
import com.xvr.serviceBook.repository.TicketRepository;
import com.xvr.serviceBook.repository.WorkerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;

import java.time.Instant;
import java.util.Date;

@org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
@Commit
public class DataJpaTest {

        @Autowired
        private TicketRepository ticketRepository;
        @Autowired
        private WorkerRepository workerRepository;
/*
        @BeforeEach
        public void booksShouldBeAdded() {
            Ticket ticket1 = Ticket.builder().build();
            Worker w1 = new Worker();
            Worker w2 = new Worker("b2");
            ticket1.set(b1);
            author1.addBook(b2);
            ticketRepository.save(author1);
            Author author2 = new Author("a2");
            author2.addBook(b1);
            ticketRepository.save(author2);
            Assertions.assertEquals(2, ticketRepository.count());
            Assertions.assertEquals(2, workerRepository.count());
        }
        /*@Test
        @DisplayName("отсоединение книги от автора")
        public void whenDeleteAuthorFromBook_thenOneDeleteStatement() {
            Author author = ticketRepository.findByName("a1");
            Book book = workerRepository.findByName("b1");
            author.removeBook(book);
        }*/
}
