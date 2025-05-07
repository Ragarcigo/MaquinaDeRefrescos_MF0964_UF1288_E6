/*
Máquina expendedora de refrescos

Desarrolla en Java una aplicación de consola que simule una máquina expendedora de refrescos. Esta máquina debe permitir al usuario:

Ver un menú de 6 refrescos disponibles con su precio.

Seleccionar un refresco introduciendo un número.

Introducir el dinero para pagar.

Mostrar si el dinero es suficiente y calcular el cambio.

Al final, mostrar:

Cuántas unidades se vendieron de cada refresco.

El total de dinero recaudado.

Bebidas disponibles:
Coca-Cola
Pepsi
Fanta Naranja
Sprite
Aquarius Limón
Nestea

Requisitos mínimos
Usar al menos una Clase y la Programación orientada a Objetos
Usar una estructura ArrayList para almacenar los refrescos vendidos.
Permitir la selección del refresco y gestión del pago con Scanner o JOptionPane.
Al finalizar, recorrer el ArrayList y mostrar las estadísticas de venta.
Implementar control de errores (por ejemplo, opción no válida o dinero insuficiente).
 */

import javax.swing.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Refresco> vendidos = new ArrayList<>(); //Lista de todas las ventas
        boolean salir = false;

        while (!salir){
            String menu="Seleccione un refresco: \n";
            Bebidas[] bebidas = Bebidas.values(); //Array de todas las bebidas del enum
            for (int i = 0; i < bebidas.length; i++) {
                menu+=(i+1) + ". " + bebidas[i] + "\n";
            }
            menu+="0. Salir\n";

            //Mostrar el menú al usuario y leer la opción
            String opcionS= JOptionPane.showInputDialog(null, menu, "Máquina de Refrescos", JOptionPane.INFORMATION_MESSAGE);

            if (opcionS==null) break; //Si el usuario cierra la ventana = salir

            int opcion;
            try{
                opcion = Integer.parseInt(opcionS);
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Elección inválida. ");
                continue;
            }

            if (opcion==0){
                salir=true; //Salir del bucle
            } else if (opcion>0 && opcion<=bebidas.length){
                Bebidas seleccion = bebidas[opcion-1];

                //Solicitar dinero al usuario
                String dineroS = JOptionPane.showInputDialog(null, "Precio : €" + seleccion.getPrecio() + "\nIngrese su dinero: ");

                if (dineroS == null) continue;

                try {
                    double dinero = Double.parseDouble(dineroS);
                    if (dinero >= seleccion.getPrecio()){
                        double cambio = dinero - seleccion.getPrecio();
                        vendidos.add(new Refresco(seleccion));
                        JOptionPane.showMessageDialog(null, "Compra realizada. Cambio: " + String.format("%.2f", cambio) + "€");
                    }else{
                        JOptionPane.showMessageDialog(null, "Dinero insuficiente. Faltan: " + String.format("%.2f", seleccion.getPrecio() - dinero) + "€");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Opción inválida");
                }
                }else{
                JOptionPane.showMessageDialog(null, "Opción no existente");
                }
            }
        //RESUMEN FINAL DE VENTAS
        if (vendidos.isEmpty()){
            JOptionPane.showMessageDialog(null, "No se han vendido refrescos.");
        }else {
            //Mapa para contar cuantos refrescos se vendieron de cada tipo
        Map<Bebidas,Integer> total= new LinkedHashMap<>();
        for (Bebidas b : Bebidas.values()) {
            total.put(b, 0);
        }
        double totalVendido = 0.0; //Se calcula recorriendo el ArrayList

            for (Refresco r : vendidos) {
                Bebidas bVendida = r.getTipo();
                total.put(bVendida, total.get(bVendida) + 1);
                totalVendido += bVendida.getPrecio(); //Sumando desde el ArrayList
                }

                //Construye el resumen para mostrar
                StringBuilder resumen = new StringBuilder("RESUMEN DE VENTAS: \n");
                for (Map.Entry<Bebidas, Integer> e : total.entrySet()) {
                    if (e.getValue() > 0) {
                        resumen.append(String.format("\n %s: %d", e.getKey(), e.getValue()));
                    }
                }
                resumen.append(String.format("\n Total recaudado: $%.2f", totalVendido));

                JOptionPane.showMessageDialog(null, resumen.toString());
            }
        }
        }






