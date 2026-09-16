public class PocFloatDoubleCharBoolean {
    public static void main(String[] args) {
        experimento1_precisionDecimal();
        experimento2_floatVsDouble();
        experimento3_charComoNumero();
        experimento4_booleanNoAritmetico();
        experimento5_memoriaArraysDecimal();
    }

    // ============================================================
    // EXPERIMENTO 1: El problema clásico de precisión (0.1 + 0.2)
    // ============================================================
    static void experimento1_precisionDecimal() {
        System.out.println("=== EXPERIMENTO 1: Precisión decimal ===");
        double resultado = 0.1 + 0.2;
        System.out.println("0.1 + 0.2 = " + resultado);
        System.out.println("¿Es exactamente 0.3? " + (resultado == 0.3));
        System.out.println();
    }

    // ============================================================
    // EXPERIMENTO 2: float vs double - diferencia de precisión real
    // ============================================================
    static void experimento2_floatVsDouble() {
        System.out.println("=== EXPERIMENTO 2: float vs double ===");
        float numeroFloat = 1.0f / 3.0f;
        double numeroDouble = 1.0 / 3.0;
        System.out.println("1/3 como float:  " + numeroFloat);
        System.out.println("1/3 como double: " + numeroDouble);
        System.out.println();
    }

    // ============================================================
    // EXPERIMENTO 3: char es en realidad un número
    // ============================================================
    static void experimento3_charComoNumero() {
        System.out.println("=== EXPERIMENTO 3: char como número ===");
        char letra = 'A';
        int valorNumerico = letra;
        char siguienteLetra = (char) (letra + 1);
        System.out.println("Letra: " + letra);
        System.out.println("Su valor numérico: " + valorNumerico);
        System.out.println("Letra + 1: " + siguienteLetra);
        System.out.println();
    }

    // ============================================================
    // EXPERIMENTO 4: boolean no participa de aritmética
    // ============================================================
    static void experimento4_booleanNoAritmetico() {
        System.out.println("=== EXPERIMENTO 4: boolean ===");
        boolean activo = true;
        // Descomenta la siguiente línea y observa qué pasa:
        // int noCompila = activo + 1;
        System.out.println("activo = " + activo);
        System.out.println();
    }

    // ============================================================
    // EXPERIMENTO 5: memoria real de double[] vs int[]
    // ============================================================
    static void experimento5_memoriaArraysDecimal() {
        System.out.println("=== EXPERIMENTO 5: memoria double[] vs int[] ===");
        Runtime runtime = Runtime.getRuntime();
        int cantidad = 10_000_000;

        long antesInt = runtime.totalMemory() - runtime.freeMemory();
        int[] arregloInt = new int[cantidad];
        long despuesInt = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("int[]    - Diferencia: " + (despuesInt - antesInt) + " bytes");

        long antesDouble = runtime.totalMemory() - runtime.freeMemory();
        double[] arregloDouble = new double[cantidad];
        long despuesDouble = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("double[] - Diferencia: " + (despuesDouble - antesDouble) + " bytes");

        System.out.println("Último valor int (anti-optimización): " + arregloInt[0]);
        System.out.println("Último valor double (anti-optimización): " + arregloDouble[0]);
    }
}
