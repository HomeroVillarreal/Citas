import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String Licencia = "db/licencia.txt";
        String Key = "Lic123";
        boolean licenciaValida = verlicencia(Licencia, Key);
        if (licenciaValida)
        {
            System.out.println("Inicia revision de archivos necesarios");
            filechecker();
            System.out.println("Carga exitosa, inicia programa");
            System.out.println("Licencia válida. ¡Programa ejecutado correctamente!");
            System.out.println("Inicio de sesión de administrador");
            System.out.print("Usuario: ");
            String usuario = scanner.nextLine();
            System.out.print("Contraseña: ");
            String PW = scanner.nextLine();
            if (checkusuario(usuario, PW)) {
                System.out.println("Inicio de sesión exitoso");
                int opcion;
                do {
                    Citas.mostrarMenu();
                    opcion = scanner.nextInt();
                    scanner.nextLine();
                    switch (opcion) {
                        case 1:
                            Doctores.darDeAltaDoctor();
                            break;
                        case 2:
                            Pacientes.darDeAltaPaciente();
                            break;
                        case 3:
                            Citas.crearCita();
                            break;
                        case 4:
                            Citas.listarCitas();
                            break;
                        case 5:
                            System.out.println("Saliendo del programa...");
                            break;
                        default:
                            System.out.println("Opción inválida. Por favor, selecciona una opción válida del menú.");
                    }
                }
                while (opcion != 5);
            } else {
                System.out.println("Inicio de sesión fallido. El programa se cerrará.");
            }
        } else {
            System.out.println("Licencia inválida. El programa no puede ejecutarse!");
            System.out.println("Contacte a ventas y adquiera una licencia valida.");
        }
    }

    public static boolean verlicencia(String DBLicencia, String claveLicencia) {
        File archivodelicencia = new File(DBLicencia);
        try {
            Scanner scanner = new Scanner(archivodelicencia);
            String clave = scanner.nextLine().trim();
            scanner.close();
            return clave.equals(claveLicencia);
        } catch (FileNotFoundException any) {
            System.out.println("Archivo de licencia no encontrado.");
            return false;
        }
    }

    private static boolean checkusuario(String Usuario, String PW) {
        String Usuariocorrecto = "admin";
        String PWCorrecto = "admin";
        return Usuario.equals(Usuariocorrecto) && PW.equals(PWCorrecto);
    }

    public static void filechecker()
    {
        String[] Archivos = {"db/Citas.txt", "db/Doctores.txt", "db/IDChecker.txt", "db/Pacientes.txt"};
        for (String Archivo : Archivos)
        {
            File file = new File(Archivo);
            if (!file.exists())
            {
                try
                {
                    file.createNewFile();
                    System.out.println("Archivo " + Archivo + " creado exitosamente.");
                }
                catch (IOException any)
                {
                    System.out.println("Error al crear el archivo " + Archivo);
                }
            }
            else
            {
                System.out.println("El archivo " + Archivo + " ya existe.");
            }
        }
    }
}