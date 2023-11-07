package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.data.resources.Book;
import seedu.data.Status;
import seedu.exception.SysLibException;
import seedu.parser.Parser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.ui.UI.SEPARATOR_LINEDIVIDER;

public class AddCommandTest {
    private final Parser parser = new Parser();
    private final AddCommand addCommand = new AddCommand();

    @Test
    public void addCommandValidData() throws SysLibException {
        addCommand.execute("/i TMOBM00000001 /t The Minds of Billy Milligan /a Daniel Keyes /tag B " +
                "/g Non-Fiction, Biography /s LOST", parser);

        Book newBook = (Book) parser.getResourceList().get(0);

        assertEquals(newBook.getId(), 1);
        assertEquals(newBook.getTitle(), "The Minds of Billy Milligan");
        assertEquals(newBook.getAuthor(), "Daniel Keyes");
        assertEquals(newBook.getTag(), "B");
        assertEquals(newBook.getISBN(), "TMOBM00000001");
        assertEquals(newBook.getGenreString(), "Non-Fiction, Biography");
        assertEquals(newBook.getStatus(), Status.LOST);
    }

    @Test
    public void addCommandOutput() throws SysLibException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        addCommand.execute("/i TMOBM00000001 /t The Minds of Billy Milligan /a Daniel Keyes /tag B " +
                "/g Non-Fiction, Biography", parser);

        String output = outputStream.toString();

        String expectedOutput = "This book is added:" + System.lineSeparator() +
                "[B]  ID: 1 Title: The Minds of Billy Milligan ISBN: TMOBM00000001 Author: Daniel Keyes Genre: " +
                "Non-Fiction, Biography Status: AVAILABLE Received Date: 07 Nov 2023" +
                SEPARATOR_LINEDIVIDER + System.lineSeparator();

        assertEquals(expectedOutput, output);
    }

    @Test
    public void addCommandInvalidIsbn() {
        assertThrows(SysLibException.class, ()->addCommand.execute("/i TMOBM " +
                "/t The Minds of Billy Milligan /a Daniel Keyes /tag B", parser));
    }

    @Test
    public void addCommandInsufficientData() {
        assertThrows(SysLibException.class, ()->addCommand.execute("/i ", parser));
    }
}
