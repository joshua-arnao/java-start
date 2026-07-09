# OPERADORES BÁSICOS
Son simbolos que permiten realizar operaciones en una más varibles, se asignan en diferentes categorías según su función. 

## OPERADRORES DE ASIGNACIÓN
- Sirven para asignar valores a las variables

    > `= += -= *= /= %=`

### EJEMPLOS DE ASIGNACIÓN

```java
int a = 10;
System.out.println("Asignación (=): " + a);
```

## OPERADORES ARITEMÉTICOS
- Permite realizar operaciones matemáticas básicas.

    > `+ - * / %`

### EJEMPLOS ARITEMETICOS

```java
void main(String[] args) {
  System.out.println("Suma: " + (a + b));
  System.out.println("Resta: " + (a - b));
  System.out.println("Multiplicación: " + ( a * b));
  System.out.println("División: " + ( a / b));
  System.out.println("Módulo: " + ( a % b));
}
```

## OPERADORES RELACIONALES
- Comparan dos valores y devuelven un valor booleano (true o false)

  > `< > == != <= >=`

### EJEMPLOS RELACIONALES

```java
void main(String[] args) {
  System.out.println("a == b: " + (a == b));
  System.out.println("a != b : " + (a != b));
  System.out.println("a > b " + (a > b));
  System.out.println("a < b: " + (a < b));
  System.out.println("a >= b: " + (a <= b));
  System.out.println("a <= b: " + (a <= b));
}
```

## OPERADORES LOGICOS
- Permite combinar expresiones booleanas para tomar decisiones basadas en múltiiples condiciones.

  > `&& || ! & |`

### EJEMPLOS LÓGICOS
```java
void main(String[] args) {
    boolean a = true, b = false;
    
    System.out.println("a && b: " + (a && b));
    System.out.println("a || b : " + (a || b));
    System.out.println("!a " + (!a));
}
```

## OPERADORES UNARIOS
- Se aplica a un solo operador

  > `++ -- + - ~`

### EJEMPLOS UNARIOS
```java
void main(String[] args) {
    int a = 5, b = -a;
    boolean flag = true;
    
    System.out.println("a && b: " + (a && b));
    System.out.println("a || b : " + (a || b));
    System.out.println("!a " + (!a));
}
```