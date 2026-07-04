public class PocPrimitivos {
    public static void main(String[] args) {
        byte positivo = 86;
        byte negativo = -86;

        System.out.println(Integer.toBinaryString(positivo & 0xFF));
        System.out.println(Integer.toBinaryString(negativo & 0xFF));

        System.out.println(String.format("%8s", Integer.toBinaryString(5 & 0xFF)).replace(' ', '0'));

        byte numero = 86;
        byte invertido = (byte) ~numero;
        byte negativoManual = (byte) (invertido + 1);

        System.out.println("Invertido: " + invertido);
        System.out.println("Invertido + 1: " + negativoManual);

        int maximo = Integer.MAX_VALUE;
        System.out.println("Maximo: " + maximo);
        System.out.println("Maximo + 1: " + (maximo + 1));

        long maximoLong = Integer.MAX_VALUE;
        System.out.println("Como long + 1: " + (maximoLong + 1));
    }
}