import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        Memory memory = new Memory();
        Invoker invoker = new Invoker(
                new CreateCommand(receiver),
                new CopyCommand(receiver),
                new DeleteCommand(receiver),
                new RebaseCommand(receiver)
        );

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){

            String out;
            System.out.print("Для выхода наберите exit, для продолжения нажмите Enter ");
            out = reader.readLine();
            while(!out.equals("exit")) {
                System.out.println("""
                        command e/E - создать студента
                        command c/C - скопировать студента в группу
                        command d/D - удалить студента
                        command x/X - перенести студента в другую группу
                        command u/U - отменить действие
                        """);
                out = reader.readLine();
                switch (out.toLowerCase(Locale.ROOT)){
                    case "e" -> {
                        memory.setMomento(receiver.save());
                        invoker.create();
                    }
                    case "c" -> {
                        memory.setMomento(receiver.save());
                        invoker.copy();
                    }
                    case "d" -> {
                        memory.setMomento(receiver.save());
                        invoker.delete();
                    }
                    case "x" -> {
                        memory.setMomento(receiver.save());
                        invoker.rebase();
                    }
                    case "u" -> receiver.undo(memory.getMoment());
                }

                receiver.getGroups().forEach(System.out::println);
            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }


    }
}
