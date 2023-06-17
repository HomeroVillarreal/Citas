import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Pacientes
{
    public static final String DBPacientes="db/Pacientes.txt";
    public static Scanner scanner=new Scanner(System.in);
    public static void darDeAltaPaciente()
    {
        System.out.println("----- Alta de Paciente -----");
        System.out.print("Ingresa el identificador único del paciente: ");
        String idPaciente=scanner.nextLine();
        System.out.print("Ingresa el nombre completo del paciente: ");
        String nombrePaciente=scanner.nextLine();
        try (BufferedWriter writer=new BufferedWriter(new FileWriter(DBPacientes, true)))
        {
            writer.write(idPaciente + "," + nombrePaciente);
            writer.newLine();
            System.out.println("Paciente dado de alta correctamente.");
        }
        catch (IOException any)
        {
            System.out.println("Error al dar de alta el paciente.");
        }
    }
}
