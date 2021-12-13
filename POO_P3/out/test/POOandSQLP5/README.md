# POOandSQLP5
Programación orientada a objetos con acceso a base de datos, 

Descripción
A partir de los diagramas desarrollados en el producto anterior, realizaremos un
programa en Java que implemente estos diagramas almacenando la información en
estructuras dinámicas de datos y ficheros XML aplicando patrones de diseño.
Objetivo
El objetivo principal de la actividad es:
● Implementar estructuras dinámicas de datos y el modelo estático de clases en
lenguaje Java, aplicar el patrón de diseño DAO, almacenar datos en
estructuras XML y realizar pruebas unitarias utilizando JUnit, todo ello
utilizando un sistema de control de versiones.
Pasos a seguir
Los pasos a seguir para llevar a cabo el producto son:
1. Leer detenidamente estas instrucciones e identificar los requerimientos de la
actividad.
2. Revisar detenidamente la rúbrica de evaluación.
3. Consultar los recursos necesarios facilitados en el aula.
4. Registrar y configurar GitLab JE UOC.
a. En el desarrollo del proyecto deberá utilizarse el sistema de control de
versiones Git, en este caso se usará la herramienta online GitLab.

5. Implementar el modelo estático de clases en lenguaje Java.
a. En la implementación deberán utilizarse como mínimo las Clases
Genéricas o Java Generics.
b. Elegir el Tipo de Colecciones en Java óptimo para cada caso en función
de la necesidad o requisito a implementar.

c. Se debe tener en cuenta la correcta gestión de Excepciones, así como
implementar Excepciones personalizadas.

6. Realizar un programa en Java en modo de consola que almacene la
información en estructuras dinámicas de datos y la guarde en ficheros XML.
a. Hay que tener en cuenta que en esta actividad del proyecto los datos se
almacenarán en ficheros XML pero que posteriormente en este proyecto
los mismos datos se almacenarán en una base de datos relacional.
b. Para separar la persistencia de los datos del resto de funcionalidades
del sistema se deberá utilizar el patrón de diseño DAO (Data Access
Object). Uno de los beneficios de DAO es la independencia del almacén
de datos, así pues, el cambio de usar ficheros XML a una base de datos
relacional o el cambio de motor de base de datos solo afectará al DAO y
no a las clases encargadas de la lógica de negocio o de presentación. o
el cambio de motor de base de datos.
c. Con el objeto de conseguir la independencia del almacén de datos se
usará el patrón Factory para instanciar los DAOs.
d. Se debe tener en cuenta que estos ficheros XML se utilizarán
posteriormente para la carga inicial de datos en la base de datos, así
pues, su estructura debe ser adecuada para que sea posible su
importación en el modelo relacional que se implantará en la base de
datos MySql.

4. Realizar pruebas unitarias utilizando el entorno JUnit. Elegir dos métodos que
apliquen lógica de negocio y realizar las pruebas unitarias con JUnit para
evaluar su correcto funcionamiento.
Se requiere
Los requisitos indispensables para realizar el producto son:
1. Registrarse en la herramienta online GitLab JE UOC.
2. Disponer del entorno de programación en Java.
3. Disponer del entorno JUnit.
4. Investigar otras fuentes de información para ampliar los recursos facilitados en
el aula virtual.
