# almundo -> Proyecto "consigna"

Este proyecto esta dividido en dos paquetes
 - Paquete 'consigna' -> Donde se encuentra toda la lógica de negocio.
 - Paquete 'test' -> Donde se encuentran las pruebas de diferentes escenarios.


Solución de puntos Extras:

 - Lo que pasa con una llamada cuando no hay ningun empleado libre es que esta se queda en cola. 
Tecnicamente lo que se realiza es un atributo 'llamadasEnCola' en la clase 'Dispatcher'. Este atributo 
es una lista de llamadas que luego serán tomadas una a una cada vez que un empleado vaya terminando de 
atender una llamada.

 - Lo que pasa con una llamada cuando entran más de 10 llamadas concurrentes, para el caso de este proyecto 
que solo se simula tener 10 empleados (7 operadores, 2 supervisores, 1 director) es que dicha llamada quedará 
en espera (en cola) hasta que un empleado termine de atender una llamada. En caso de que se tuviesen mas 
empleados dicha llamada seria atendida inmediatamente por el empleado que estuviese libre.

Se agregan las siguientes 3 pruebas unitarias:

 1. pruebaDeDiezLlamadas(): En esta prueba se lanzan al tiempo 10 llamadas para 10 empleados. Estas llamadas
serán atendidas en su totalidad sin que ninguna quede en cola.

 2. pruebaDeDiezLlamadasConEncolamiento(): En esta prueba se lanzan 20 llamadas para 10 empleados. Esto produce
que las 10 ultimas llamadas que llegan quedan en cola y se irán atendiendo tan pronto un empleado vaya quedando
libre.

 3. pruebaDeDiezLlamadasMasDespuesDeAlgunosSegundos(): En esta prueba se lanzan 20 llamadas para 10 empleados.
Primero se lanzan 10 llamadas y 6 segundos despues se lanzan 10 llamadas mas, esto genera que algunas llamadas
queden en cola, pero van a ser ya menos debido a que en los 6 segundos que se espera algunos empleados alcanzan
a quedar libres para atender de inmediato las primeras llamadas siguientes que entran.

Nota: En las 3 pruebas construidas se lanzan logs para ir haciendo seguimiento del comportamiento de cada cual.
Un ejemplo de la salida de la primera prueba se describe a continuación.

Iniciando pruebas en clase TestConsigna!

INICIO DE METODO TEST!

PRUEBA: pruebaDeDiezLlamadas()

Inicio llamada '0' de 7 seg: Sun Mar 17 09:56:47 COT 2019, atendida por PersonaCallCenter{nombres=Juan, apellidos=Lopez}

Inicio llamada '1' de 5 seg: Sun Mar 17 09:56:47 COT 2019, atendida por PersonaCallCenter{nombres=Carlos, apellidos=Rojas}

Inicio llamada '2' de 5 seg: Sun Mar 17 09:56:47 COT 2019, atendida por PersonaCallCenter{nombres=Andres, apellidos=Suarez}

Inicio llamada '3' de 8 seg: Sun Mar 17 09:56:47 COT 2019, atendida por PersonaCallCenter{nombres=Franco, apellidos=Morales}

Inicio llamada '4' de 8 seg: Sun Mar 17 09:56:47 COT 2019, atendida por PersonaCallCenter{nombres=Laura, apellidos=Romano}

Inicio llamada '5' de 8 seg: Sun Mar 17 09:56:47 COT 2019, atendida por PersonaCallCenter{nombres=Diana, apellidos=Robledo}

Inicio llamada '6' de 6 seg: Sun Mar 17 09:56:47 COT 2019, atendida por PersonaCallCenter{nombres=Maria, apellidos=Morales}

Inicio llamada '7' de 5 seg: Sun Mar 17 09:56:47 COT 2019, atendida por PersonaCallCenter{nombres=Francisco, apellidos=Sepulveda}

Inicio llamada '8' de 5 seg: Sun Mar 17 09:56:47 COT 2019, atendida por PersonaCallCenter{nombres=Luisa, apellidos=Mendez}

Inicio llamada '9' de 9 seg: Sun Mar 17 09:56:47 COT 2019, atendida por PersonaCallCenter{nombres=Victor, apellidos=Mercedez}

Fin llamada '2' de 5 seg: Sun Mar 17 09:56:52 COT 2019, atendida por PersonaCallCenter{nombres=Andres, apellidos=Suarez}

Fin llamada '1' de 5 seg: Sun Mar 17 09:56:52 COT 2019, atendida por PersonaCallCenter{nombres=Carlos, apellidos=Rojas}

Fin llamada '7' de 5 seg: Sun Mar 17 09:56:52 COT 2019, atendida por PersonaCallCenter{nombres=Francisco, apellidos=Sepulveda}

Fin llamada '8' de 5 seg: Sun Mar 17 09:56:52 COT 2019, atendida por PersonaCallCenter{nombres=Luisa, apellidos=Mendez}

Fin llamada '6' de 6 seg: Sun Mar 17 09:56:53 COT 2019, atendida por PersonaCallCenter{nombres=Maria, apellidos=Morales}

Fin llamada '0' de 7 seg: Sun Mar 17 09:56:54 COT 2019, atendida por PersonaCallCenter{nombres=Juan, apellidos=Lopez}

Fin llamada '3' de 8 seg: Sun Mar 17 09:56:55 COT 2019, atendida por PersonaCallCenter{nombres=Franco, apellidos=Morales}

Fin llamada '4' de 8 seg: Sun Mar 17 09:56:55 COT 2019, atendida por PersonaCallCenter{nombres=Laura, apellidos=Romano}

Fin llamada '5' de 8 seg: Sun Mar 17 09:56:55 COT 2019, atendida por PersonaCallCenter{nombres=Diana, apellidos=Robledo}

Fin llamada '9' de 9 seg: Sun Mar 17 09:56:56 COT 2019, atendida por PersonaCallCenter{nombres=Victor, apellidos=Mercedez}

FIN DE METODO TEST!

Finalizando pruebas en clase TestConsigna!









