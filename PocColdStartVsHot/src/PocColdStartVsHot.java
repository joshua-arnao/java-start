public class PocColdStartVsHot {

    public static void main(String[] args) {
        int repeticiones = 2_000_000;
        long[] tiempos = new long[10];

        // Ejecutamos el MISMO método 10 veces seguidas, y medimos
        // el tiempo de CADA tanda por separado, para ver si va
        // cambiando a medida que el JIT "calienta" el método.
        for (int tanda = 0; tanda < 10; tanda++) {
            long inicio = System.nanoTime();

            long resultado = calcularAlgo(repeticiones);

            long fin = System.nanoTime();
            tiempos[tanda] = (fin - inicio) / 1_000_000; // a milisegundos

            System.out.println("Tanda " + tanda + ": " + tiempos[tanda]
                    + " ms  (resultado=" + resultado + ")");
        }
    }

    // Método con trabajo real (no trivial) para que el JIT tenga
    // algo que valga la pena optimizar
    static long calcularAlgo(int n) {
        long suma = 0;
        for (int i = 0; i < n; i++) {
            suma += (i * 37) % 1009;
        }
        return suma;
    }
}