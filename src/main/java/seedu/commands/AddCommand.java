package seedu.commands;

import seedu.data.SysLibException;
import seedu.parser.Parser;

public class AddCommand extends Command{
    @Override
    public void execute(String statement, Parser parser) throws
            IllegalStateException, NumberFormatException, SysLibException {
        String[] args = Parser.parseAddCommand(statement);
        String title = args[1];
        String tag = args[3];
        if (tag.equalsIgnoreCase("b")) {
            parser.resourceList.add(Parser.createBook(args));
            System.out.println("This book is added: " + title);
        } else {
            throw new SysLibException("Please enter a valid tag.");
        }
    }

}
