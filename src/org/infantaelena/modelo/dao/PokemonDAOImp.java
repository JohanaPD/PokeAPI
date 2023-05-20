package org.infantaelena.modelo.dao;

import org.infantaelena.excepciones.PokemonNotFoundException;
import org.infantaelena.excepciones.PokemonRepeatedException;
import org.infantaelena.modelo.entidades.Pokemon;
import org.infantaelena.modelo.entidades.TipoPokemon;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que implementa los métodos de acceso a datos de la clase Pokemon
 * Esta puede hacerse mediante un fichero CSV separado por puntos y coma o una base de datos
 *
 * @author Johana Pardo
 * @version 1.0
 * @date 24/04/2023
 */
public class PokemonDAOImp implements PokemonDAO {
    public static final int NUMERO_PARAMETROS = 5;
    private File fichero;
    List<Object> contenido = new ArrayList<>();

    public PokemonDAOImp() {
        fichero = new File("resources/pokemones_list.csv");

    }

    public PokemonDAOImp(File fichero) {
        this.fichero = fichero;
    }


    public File getFichero() {
        return fichero;
    }

    public void setFichero(File fichero) {
        this.fichero = fichero;
    }

    public void revisarFichero(File file) {
        if (fichero.exists()) {
            System.out.println("El fichero ya existe");
        } else {
            try {
                fichero.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public boolean existePokemon(Pokemon pokemon) {
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].trim().toUpperCase().equals(pokemon.getNombre().trim().toUpperCase())) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void crear(Pokemon pokemon) throws PokemonRepeatedException {
        String tipo;
        if (!existePokemon(pokemon)) {
            try (DataOutputStream dos =
                         new DataOutputStream(new FileOutputStream(fichero, true))) {
                tipo = pokemon.getNombre() + ","
                        + String.valueOf(pokemon.getTipo()) + ","
                        + pokemon.getSalud() + ","
                        + pokemon.getDefensa() + ","
                        + pokemon.getVelocidad();
                dos.writeBytes(tipo + "\n");

            } catch (IOException e) {
                System.err.println("Error de E/S: " + e.getMessage());
            }
        }
    }


    @Override
    public Pokemon leerPorNombre(String nombre) throws PokemonNotFoundException {
        Pokemon retorno = new Pokemon();
        String[] values = new String[NUMERO_PARAMETROS];
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String line;
            while ((line = br.readLine()) != null) {
                values = line.split(",");
                if (values[0].equals(nombre)) {
                    retorno.setNombre(values[0].trim());
                    retorno.setTipo(buscarTipoPokemon(values[1].trim()));
                    retorno.setSalud(Integer.parseInt(values[2].trim()));
                    retorno.setDefensa(Integer.parseInt(values[3].trim()));
                    retorno.setVelocidad(Integer.parseInt(values[4].trim()));
                    return retorno;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
      /*  for (int i = 0; i < values.length ; i++) {
            System.out.println(values[i]);
        }*/
        return retorno;
    }

    public TipoPokemon buscarTipoPokemon(String tipo) throws PokemonNotFoundException {
        for (TipoPokemon tp : TipoPokemon.values()) {
            if (tp.name().equalsIgnoreCase(tipo)) {
                return tp;
            }
        }
        throw new PokemonNotFoundException("Tipo de Pokémon no válido: " + tipo);
    }

    @Override
    public List<Pokemon> leerTodos() {
        List<Pokemon> listaPokemon = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String line;
            while ((line = br.readLine()) != null) {
                //Esto es el pokemon que carga
                String[] values = line.split(",");
                Pokemon nuevo = new Pokemon(values[0], buscarTipoPokemon(values[1]), Integer.parseInt(values[2]), Integer.parseInt(values[3]), Integer.parseInt(values[4]));
                System.out.println("Nombre  : " + values[0] + " Tipo : " + buscarTipoPokemon(values[1]) + " Salud :  "
                        + Integer.parseInt(values[2]) + " Defensa : " + Integer.parseInt(values[3]) + " velocidad : " + Integer.parseInt(values[4]));
                listaPokemon.add(nuevo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PokemonNotFoundException e) {
            throw new RuntimeException(e);
        }
      /*  for (Pokemon poke: listaPokemon) {
            System.out.println(poke);
        }*/
        return listaPokemon;
    }

    @Override
    public void actualizar(Pokemon pokemon) throws PokemonNotFoundException {
        //Preguntamos si existe el pokemon
        boolean PokemonBuscar = existePokemon(pokemon);

        List<String> contenido = new ArrayList<>(); // Lista para guardar el contenido del archivo original
        String tipoActualizado = null; // String para guardar la versión actualizada del objeto

        String ficheroT = "resources/poke_copy.csv";
        File ficheroTemp = new File(ficheroT);

        //Si el pokemon existe
        if (PokemonBuscar) {
            try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
                String linea = null;
                //Cargamos el fichero en nuestra list
                while ((linea = br.readLine()) != null) {
                    //Pokemon nuevo=new Pokemon();
                    String[] partes = linea.split(",");
                    System.out.println(partes[0].trim().toUpperCase());
                    System.out.println(pokemon.getNombre().trim().toUpperCase());
                    if (partes[0].toUpperCase().trim().equals(pokemon.getNombre().toUpperCase().trim())) {
                        tipoActualizado = pokemon.getNombre().toUpperCase() + ","
                                + pokemon.getTipo() + ","
                                + pokemon.getSalud() + ","
                                + pokemon.getDefensa() + ","
                                + pokemon.getVelocidad();
                        contenido.add(tipoActualizado);
                    }
                    contenido.add(linea);




                }
            } catch (IOException e) {
                System.err.println("Error de E/S: " + e.getMessage());
            }
            // Si no se ha encontrado el objeto, lanzar una excepción


            // Escribir el nuevo contenido en un archivo temporal

            try (PrintWriter pw = new PrintWriter(new FileWriter(ficheroTemp))) {
                for (String linea : contenido) {
                    pw.println(linea);
                }
                pw.flush();
            } catch (IOException e) {
                System.err.println("Error de E/S: " + e.getMessage());
            }

            //File archivoTemporal = new File(ficheroTemp);
            fichero.delete();
            ficheroTemp.renameTo(fichero);
            ficheroTemp.delete();

        } else {
            throw new PokemonNotFoundException("No se encontró el Pokemon " + pokemon.getNombre());

        }


        // Renombrar el archivo temporal al nombre original para sobrescribir el archivo original


    }


    /* Revisar eliminar*/
    @Override
    public void eliminarPorNombre(String nombre) throws PokemonNotFoundException {
        Pokemon eliminar = leerPorNombre(nombre);
        List<String> contenido = new ArrayList<>(); // Lista para guardar el contenido del archivo original
        String tipo = null; // String para guardar la versión actualizada del objeto
        boolean encontrado = false;  // Flag para indicar si se ha encontrado el objeto

      /*  try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea = "";
            while ((linea = br.readLine()) != null) {
                String mantener[] = linea.split(",");
                if (!mantener[0].toUpperCase().trim().equals(nombre.toUpperCase().trim())) {
                    for (int i = 0; i < mantener.length; i++) {
                        tipo += mantener[i]+",";
                    }
                    System.out.println(tipo);
                    contenido.add(tipo);
                }
            }

        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        }

        String ficheroT = "resources/poke_copy.csv";
        File ficheroTemp = new File(ficheroT);

        try (PrintWriter pw = new PrintWriter(new FileWriter(ficheroTemp, true))) {
            for (String linea : contenido) {
                pw.println(linea);
            }
            pw.flush();
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        }
        fichero.delete();
        ficheroTemp.renameTo(fichero);
        ficheroTemp.delete();

*/
    }
}
