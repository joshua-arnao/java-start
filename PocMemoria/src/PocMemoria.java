public class PocMemoria {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
//
//        long antes = runtime.totalMemory() - runtime.freeMemory();
//        System.out.println("Memoria usada ANTES: " + antes + " bytes");
//
//        int[] arreglo = new int[10_000_000];
//
//        long despues = runtime.totalMemory() - runtime.freeMemory();
//        System.out.println("Memoria usada DESPUES: " + despues + " bytes");
//
//        System.out.println("Diferencia: " + (despues - antes) + " bytes");

        long antesInt = runtime.totalMemory() - runtime.freeMemory();
        int[] arregloInt = new int[10_000_000];
        long despuesInt = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("int[] - Diferencia: " + (despuesInt - antesInt) + " bytes");

        long antesByte = runtime.totalMemory() - runtime.freeMemory();
        byte[] arregloByte = new byte[10_000_000];
        long despuesByte = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("byte[] - Diferencia: " + (despuesByte - antesByte) + " bytes");
    }
}