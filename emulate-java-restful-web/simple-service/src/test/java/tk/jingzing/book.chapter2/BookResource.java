package tk.jingzing.book.chapter2;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.awt.print.Book;

/**
 * Created by wangyunjing on 16/9/22.
 */
@Path("book")
public interface BookResource {
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_ATOM_XML)
    public String newBook(Book book);
}
