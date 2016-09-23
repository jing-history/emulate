package tk.jingzing.book.chapter2;

import com.sun.istack.internal.logging.Logger;
import com.sun.jersey.test.framework.JerseyTest;
import org.junit.Test;
import tk.jingzing.book.chapter2.domain.Book;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by wangyunjing on 16/9/22.
 */
public class PutTest extends JerseyTest {
    private final static Logger LOGGER = Logger.getLogger(PutTest.class);
   private WebTarget target;
    public static AtomicLong clientBook = new AtomicLong();
    @Test
    public void testNew(){
        final Book newBook = new Book(clientBook.incrementAndGet(),"book-" + System.nanoTime());
        MediaType contentType = MediaType.APPLICATION_XML_TYPE;
        MediaType acceptType = MediaType.TEXT_PLAIN_TYPE;

        final Entity<Book> bookEntity = Entity.entity(newBook,contentType);
    //    final String lastUpdate = target("book").request(acceptType).put(bookEntity,String.class);
    //    Assert.assertNotNull(lastUpdate);
       // LOGGER.debug(lastUpdate);

    }
}
