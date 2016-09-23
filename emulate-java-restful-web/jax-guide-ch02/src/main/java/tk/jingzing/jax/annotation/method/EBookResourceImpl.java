package tk.jingzing.jax.annotation.method;


import org.apache.log4j.Logger;
import tk.jingzing.jax.domain.Book;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class EBookResourceImpl implements BookResource {
    private final static Logger LOGGER = Logger.getLogger(EBookResourceImpl.class);
    public static AtomicLong serverBookSequence = new AtomicLong();

    @Override
    public String getWeight() {
        return "150M";
    }

    @Override
    public String newBook(Book book) {
        SimpleDateFormat f = new SimpleDateFormat("d MMM yyyy HH:mm:ss");
        Date lastUpdate = Calendar.getInstance().getTime();
        //...
        LOGGER.debug(book.getBookId());
        return f.format(lastUpdate);
    }

    @Override
    public Book createBook(Book book) {
        book.setBookId(serverBookSequence.incrementAndGet());
        return book;
    }

    @Override
    public void delete(long bookId) {
        LOGGER.debug(bookId);
    }


    /*@Override
    public boolean moveBooks(Books books) {
    	LOGGER.debug("MOVE method");
        return true;
    }*/
}
