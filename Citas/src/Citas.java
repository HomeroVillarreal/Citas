import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Citas
{
    private static final String IDChecker="db/IDChecker.txt";
    private static final String DBCitas="db/Citas.txt";
    public static Scanner scanner=new Scanner(System.in);

    public static void mostrarMenu()
    {
        System.out.println("----- Sistema de Citas Médicas -----");
        System.out.println("1. Dar de alta doctor");
        System.out.println("2. Dar de alta paciente");
        System.out.println("3. Crear cita");
        System.out.println("4. Listar citas");
        System.out.println("5. Salir");
        System.out.print("Selecciona una opción: ");
    }

    public static void crearCita()
    {
        System.out.println("----- Creación de Cita -----");
        String idCita = obteneridCita();
        System.out.println("Identificador de cita: " + idCita);
        System.out.print("Ingresa la fecha y hora de la cita (Ejemplo: 2023-06-11 10:00): ");
        String fechaHora = scanner.nextLine();
        System.out.print("Ingresa el motivo de la cita: ");
        String motivo = scanner.nextLine();
        System.out.print("Ingresa el identificador único del doctor: ");
        String idDoctor = scanner.nextLine();
        System.out.print("Ingresa el identificador único del paciente: ");
        String idPaciente = scanner.nextLine();
        try (BufferedWriter writer=new BufferedWriter(new FileWriter(DBCitas, true))) {
            writer.write(idCita + "," + fechaHora + "," + motivo + "," + idDoctor + "," + idPaciente);
            writer.newLine();
            System.out.println("Cita creada correctamente.");
        } catch (IOException any)
        {
            System.out.println("Error al crear la cita: ");
        }
    }

    public static void listarCitas()
    {
        System.out.println("----- Listado de Citas -----");
        try (BufferedReader reader=new BufferedReader(new FileReader(DBCitas)))
        {
            String linea;
            while ((linea = reader.readLine()) != null)
            {
                String[] datosCita=linea.split(",");
                if (datosCita.length==5)
                {
                    String idCita=datosCita[0];
                    String fechaHora=datosCita[1];
                    String motivo=datosCita[2];
                    String idDoctor=datosCita[3];
                    String idPaciente=datosCita[4];
                    String nombreDoctor=obtenerNombreDoctor(idDoctor);
                    String nombrePaciente=obtenerNombrePaciente(idPaciente);
                    System.out.println("ID Cita: "+idCita);
                    System.out.println("Fecha y Hora: "+fechaHora);
                    System.out.println("Motivo: "+motivo);
                    System.out.println("Doctor: "+nombreDoctor);
                    System.out.println("Paciente: "+nombrePaciente);
                    System.out.println();
                }
            }
        }
        catch (IOException any)
        {
            System.out.println("Error al listar las citas.");
        }
    }

    public static String obteneridCita()
    {
        int ultimoId=obtenerUltimoIdCita();
        int nuevoId=ultimoId + 1;
        guardarUltimoIdCita(nuevoId);
        return String.valueOf(nuevoId);
    }

    private static int obtenerUltimoIdCita()
    {
        try (BufferedReader reader=new BufferedReader(new FileReader(IDChecker)))
        {
            String linea=reader.readLine();
            if (linea!=null)
            {
                return Integer.parseInt(linea);
            }
        }
        catch (IOException any)
        {
            System.out.println("Error al obtener el último ID de cita");
        }
        return 0;
    }

    private static void guardarUltimoIdCita(int ultimoId)
    {
        try (BufferedWriter writer=new BufferedWriter(new FileWriter(IDChecker)))
        {
            writer.write(String.valueOf(ultimoId));
        } catch (IOException any)
        {
            System.out.println("Error al guardar el último ID de cita");
        }
    }

    public static String obtenerNombreDoctor(String idDoctor) throws IOException
    {
        try (BufferedReader reader=new BufferedReader(new FileReader(Doctores.DBDoctores)))
        {
            String linea;
            while ((linea=reader.readLine())!=null)
            {
                String[] datosDoctor=linea.split(",");
                if (datosDoctor.length==2&&datosDoctor[0].equals(idDoctor))
                {
                    return datosDoctor[1];
                }
            }
        }
        return "Desconocido";
    }

    private static String obtenerNombrePaciente(String idPaciente) throws IOException
    {
        try (BufferedReader reader=new BufferedReader(new FileReader(Pacientes.DBPacientes)))
        {
            String linea;
            while ((linea=reader.readLine())!=null)
            {
                String[] datosPaciente=linea.split(",");
                if (datosPaciente.length==2&&datosPaciente[0].equals(idPaciente))
                {
                    return datosPaciente[1];
                }
            }
        }
        return "Desconocido";
    }
}