
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

// Remote Interface
interface Calculator extends Remote {
    int addition(int a, int b) throws RemoteException;
    int subtraction(int a, int b) throws RemoteException;
    int multiplication(int a, int b) throws RemoteException;
}

// Implementation Class
class CalculatorImpl extends UnicastRemoteObject
        implements Calculator {

    CalculatorImpl() throws RemoteException {
        super();
    }

    public int addition(int a, int b) {
        return a + b;
    }

    public int subtraction(int a, int b) {
        return a - b;
    }

    public int multiplication(int a, int b) {
        return a * b;
    }
}

// Main Server and Client Class
public class RMI {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Use: java RMI server OR java RMI client");
            return;
        }

        try {
            if (args[0].equalsIgnoreCase("server")) {

                CalculatorImpl obj = new CalculatorImpl();

                Registry registry =
                    LocateRegistry.createRegistry(1099);

                registry.rebind("CalculatorService", obj);

                System.out.println("RMI Server is running...");

            } else if (args[0].equalsIgnoreCase("client")) {

                Registry registry =
                    LocateRegistry.getRegistry("localhost", 1099);

                Calculator obj = (Calculator)
                    registry.lookup("CalculatorService");

                int a = 20, b = 10;

                System.out.println("Addition: " +
                    obj.addition(a, b));

                System.out.println("Subtraction: " +
                    obj.subtraction(a, b));

                System.out.println("Multiplication: " +
                    obj.multiplication(a, b));
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
