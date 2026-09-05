

## CONCATENAR UN INT A UN STRING
```java
String resultado = "";
for (int i = 0; i < 10000; i++) {
    resultado += i;  // ⚠️ PELIGRO
}
```
`String` es inmutable por eso cada `+=` dentro del loop **no modifica** el String, lo que realmente hace es crear un **String nuevo completo** en el Heap y descarta el anterior. generado presión en el GC.

**La solución**:
Usar `StringBuilder`, que si es mutable (usa un array de `char` que puede crecer)


```java
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 10000; i++) {
    sb.append(i);  // modifica el mismo objeto, no crea uno nuevo cada vez
}
String resultado = sb.toString();
```

**Autoboxing dentro de loops**:
```java
public static void main(String[] args){
    List<Integer> numeros = new ArrayList<>();
    for (int i = 0; i < 1_000_000; i++) {
        numeros.add(i);  // ⚠️ cada int se "envuelve" en un objeto Integer
    }
}
```

Un `ArrayList<Integer>` no puede guardar `int` primitivos directamente, cada `int` se convierte automáticamente(**autoboxing**) en un objeto `Integer` que vive en el Heap