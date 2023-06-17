import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Doctores
{
    public static final String DBDoctores="db/Doctores.txt";
    public static Scanner scanner=new Scanner(System.in);

    public static void darDeAltaDoctor()
    {
        System.out.println("----- Alta de Doctor -----");
        System.out.print("Ingresa el identificador único del doctor: ");
        String idDoctor=scanner.nextLine();
        System.out.print("Ingresa el nombre completo del doctor: ");
        String nombreDoctor=scanner.nextLine();

        try (BufferedWriter writer=new BufferedWriter(new FileWriter(Doctores.DBDoctores, true)))
        {
            writer.write(idDoctor + "," + nombreDoctor);
            writer.newLine();
            System.out.println("Doctor dado de alta correctamente.");
        }
        catch (IOException any)
        {
            System.out.println("Error al dar de alta el doctor.");
        }
    }
}
