
package conversormonetario;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.HttpURLConnection;
import java.net.URL;
import static java.time.Clock.system;
import static java.time.InstantSource.system;
import java.util.Scanner;
import java.io.FileReader;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;


public class Conversormonetario {
 private static final String API_KEY = "a949cad3ffd5eaba437f7772"; // Reemplazar con tu clave API
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/";
    String[] opcionesDivisas = {"USD/ARS/", "ARS/USD/", "EUR/ARS/", "ARS/EUR/", "USD/COP/", "COP/USD/"};

   
        class DivisaData {
                String documentation;
                String terms_of_use;
                String time_last_update_unix;
                String time_last_update_utc;
                int time_next_update_unix;
                String time_next_update_utc;
                String base_code;               
                String target_code;
                Double conversion_rate;               
                Double conversion_result;
            }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
             int op = 0; 
         while (op!=7){
       System.out.println("**************************************************************************************"); 
       System.out.println("Menú de Conversión de Moneda");
       System.out.println("**************************************************************************************");
        System.out.println("1.- Dolar ==> Peso Argentino");
        System.out.println("2.- Peso Argentino ==> Dolar");
        System.out.println("3.- Dolar ==> Real Brasileño");
        System.out.println("4.- Real Brasileño ==> Dolar");
        System.out.println("5.- Dolar ==> Peso Colombiano");
        System.out.println("6.- Peso Colombiano  ==> Dolar");
        System.out.println("7.- Salir");

        System.out.print("Seleccione opcion de convercion (1-5): ");
        op = scanner.nextInt();
        if(op!=7){
        System.out.println("**************************************************************************************");
        System.out.print("Ingrese la cantidad a convertir: ");
        int cantidad = scanner.nextInt();
        String res = "" ;
        if (op == 1) {
           res = "USD/ARS/";                                  
        }
        if (op == 2) {
           res = "ARS/USD/";          
        }
        if (op == 3) {
           res = "EUR/ARS/";                                  
        }
        if (op == 4) {
           res = "ARS/EUR/";          
        }
        if (op == 5) {
           res = "USD/COP/";                                  
        }
        if (op == 6) {
           res = "COP/USD/";          
        }
  String url2 = API_URL + res + cantidad;
  //JsonParser parser = new JsonParser();
                    try {
                      //String pokemon="pikachu";
                      URL url=new URL(url2);
                      HttpURLConnection con=(HttpURLConnection)url.openConnection(); 
                      con.setRequestMethod("GET");
                      int responsecode=con.getResponseCode();
                                    if(responsecode!=200) {
                                        System.out.println("Error"+ responsecode);
                                    } else{

                                       // System.out.println(responseBody);
                                        StringBuilder informationstring = new StringBuilder();
                                        System.out.println("Respuesta del servidor CODE : " + responsecode);
                                        Scanner sc=new Scanner(url.openStream());
                                                while(sc.hasNext()){
                                                    informationstring.append(sc.nextLine());
                                                }
                                                sc.close();


                                                String cadenaJSON = informationstring.toString();
                                                Gson gson = new Gson();
                                        DivisaData objetoJSON = gson.fromJson(cadenaJSON, DivisaData.class);

                                          System.out.println("**************************************************************************************");
                                          System.out.println("Moneda Base " + objetoJSON.base_code );
                                          System.out.println("Moneda A Cambiar " + objetoJSON.target_code );
                                          System.out.println("Precio De Cambio " + objetoJSON.conversion_rate);
                                          System.out.println(cantidad + " al cambio son : " + objetoJSON.conversion_result );
                                          System.out.println("**************************************************************************************");

                                    }// fin del if else


                  } catch (Exception e) {
                      e.printStackTrace();
                  }//fin del try catch
                 }else{
                     System.out.println("**************************************************************************************");
                     System.out.println("Saliendo del sistema Grasias");
                     System.out.println("**************************************************************************************");
                 }// fin del if else para validar op   
            } // fin del While Bucle
    } // fin de la clase main()

    
    
  
}//fin del programa
