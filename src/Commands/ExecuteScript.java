package Commands;

import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Exceptions.WrongField;

import javax.naming.NoPermissionException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Execute script command. Takes one argument with script file path.
 * This command uses collectionManager reference to call "add" method.
 */
public class ExecuteScript implements Command{

    CommandExecutor commandExecutor;
    private static final ArrayList<Integer> recursionHistory = new ArrayList<>();

    public ExecuteScript(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    @Override
    public void execute(String[] args) throws WrongArgument, NotEnoughArgs {
        if(args.length < 2) throw new NotEnoughArgs("Command requires \"path\" argument");
        Path path;
        path = Paths.get(args[1]);
        recursionHistory.add(args[1].hashCode());

        try{
            // check file permissions
            if(!Files.exists(path)) throw new FileNotFoundException("File " + path + " not found");
            if(!Files.isReadable(path)) throw new NoPermissionException("Cannot read file.");
            if(!Files.isWritable(path)) throw new NoPermissionException("Cannot write to file.");
        }
        catch (FileNotFoundException e){
            System.out.println("File " + path + " not found."); // file does not exist
            return;
        }
        catch (NoPermissionException e){
            System.out.print("No enough permissions to " + path + " - " + e.getMessage()); // permissions deny
            return;
        }

        try(BufferedInputStream inputStream = new BufferedInputStream(Files.newInputStream(path));){
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            System.out.println("Running " + path);
            run(reader);
            recursionHistory.clear();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Read line from file, parse command and argument and run a command.
     * @param reader file reader
     * @throws IOException when reader not available
     */
    private void run(BufferedReader reader) throws IOException {

        while (true) {
            String raw = reader.readLine();
            if (raw == null) return; // if blank line

            String[] args = this.commandExecutor.parseInput(raw);  // parse command and args from file
            Command command = commandExecutor.getCommand(args[0]);
            if (command == null){
                System.out.println(args[0] + " is not a command. Try again.");
                continue;
            }
            try {
                runCommand(command, args);
            }
             catch (WrongArgument e) {
                 System.out.println("Error while running " + args[0] + " command.");
                System.out.println("Wrong argument! " + e.getMessage() + " Command skipped");
            } catch (NotEnoughArgs e) {
                System.out.println("Error while running " + args[0] + " command.");
                System.out.println("Not enough arguments. " + e.getMessage() + " Command skipped");
            }
        }
    }

    /**
     * Run command with arguments. Checks for recursion cases.
     * @param command command to execute
     * @param args args array.
     */
    private void runCommand(Command command, String[] args) throws WrongArgument, NotEnoughArgs {
        if (command.getClass() == ExecuteScript.class) {
            if (ExecuteScript.recursionHistory.contains(args[1].hashCode())) {
                System.out.println("Recursion! Command skipped!");
                System.out.println(ExecuteScript.recursionHistory);
                return;
            }
            ExecuteScript.recursionHistory.add(args[0].hashCode());
        }
        command.execute(args);
    }

    @Override
    public String getDescription() {
        return "execute script in specified file";
    }
}
