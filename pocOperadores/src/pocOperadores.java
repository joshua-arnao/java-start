public class pocOperadores {
    public static void main(String[] args) {

        int c = 10;
        System.out.println("Valor orginal: " + c); // Se imprime - 10
        System.out.println("Post-incremento: " + (c++)); // Se imprime - 10 internamente se suma 1 entonces c es 11
        System.out.println("Pre-incremento: " + (++c)); // Se suma 1 y luego se imprime - 12

        int d = 5;
        int result = d++ + ++d;

        System.out.println("Rsultado d++ + ++d:" + result);

        System.out.println("---Divisiones---");
       // System.out.println(5/0); // Excepción  ArithmeticException
        System.out.println(5.0/0); // Sin excepción resultado infinity

        System.out.println("----Operadores Compuestos----");
        byte b = 10;

        System.out.println(b+=5); // Compila y resultado 5
        // System.out.println(b = (b+5)); // Error se necesita un cast que java esconde en la primera suma (byte) (b+5)
        System.out.println(b = (byte)(b+5)); // Cast

    }
}
