package com.autodidacta;

import java.util.ArrayList;
import java.util.List;

public class PocAutoboxing {
    public static void main(String[] args) {
        int cantidad = 1_000_000;
        Runtime runtime = Runtime.getRuntime();

        System.out.println("==== int[] (primitivo) ====");
        long antesMemArray = runtime.totalMemory() - runtime.freeMemory();
        long antesTiempoArray = System.nanoTime();

        int[] arrayPrimitivo = new int[cantidad];
        for (int i = 0; i < cantidad; i++) {
            arrayPrimitivo[i] = i;
        }

        long despuesTiempoArray = System.nanoTime();
        long despuesMemArray = runtime.totalMemory() - runtime.freeMemory();

        long tiempoArray = (despuesTiempoArray - antesTiempoArray) / 1_000_000;
        long memoriaArray = despuesMemArray - antesMemArray;

        System.out.println("Tiempo:  " + tiempoArray + " ms");
        System.out.println("Memoria: " + memoriaArray + " bytes");

        System.out.println();
        System.out.println("==== ArrayList<Integer> (Integer.valueOf manual) ====");
        long antesMemLista = runtime.totalMemory() - runtime.freeMemory();
        long antesTiempoLista = System.nanoTime();

        List<Integer> listaWrapper = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            listaWrapper.add(i);
        }

        long despuesTiempoLista = System.nanoTime();
        long despuesMemLista = runtime.totalMemory() - runtime.freeMemory();

        long tiempoLista = (despuesTiempoLista - antesTiempoLista) / 1_000_000;
        long memoriaLista = despuesMemLista - antesMemLista;

        System.out.println("Tiempo:  " + tiempoLista + " ms");
        System.out.println("Memoria: " + memoriaLista + " bytes");

        System.out.println();
        System.out.println("==== ArrayList<Integer> (autoboxing) ====");
        long antesMemManual = runtime.totalMemory() - runtime.freeMemory();
        long antesTiempoManual = System.nanoTime();

        List<Integer> listaManual = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Integer valorEnvuelto = Integer.valueOf(i);
            listaManual.add(valorEnvuelto);
        }

        long despuesTiempoManual = System.nanoTime();
        long despuesMemManual = runtime.totalMemory() - runtime.freeMemory();

        long tiempoManual = (despuesTiempoManual - antesTiempoManual) / 1_000_000;
        long memoriaManual = despuesMemManual - antesMemManual;

        System.out.println("Tiempo:  " + tiempoManual + " ms");
        System.out.println("Memoria: " + memoriaManual + " bytes");
    }
}