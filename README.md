# dss2021-2022-Farmacia

- Requisitos;
Java SDK 17
MySQL Server 8.0.29 (Para API)
MySQL Workbench 8.0.29 (Para API)

Se usan a la vez tanto la API como la CLI.

# Instrucciones de uso:

## Aplicación usando Memory:
Extraer el .rar usando cualquier programa que permita hacerlo, como por ejemplo winRAR.
Ejecutar la terminal dentro de la carpeta extraída. Si estamos en Windows basta con escribir ''cmd'' en la ruta del explorador de archivos y en Linux podemos hacer click derecho y "Abrir terminal".
Una vez en la terminal y en la ruta correcta, ejecutamos el siguiente comando: java -jar ./FarmaciaDSS_Memory.jar
Usar el programa.

**IMPORTANTE:** Para poder ejecutar Memory se necesitan en la misma carpeta los archivos: DataBaseClientes2.txt y DataBaseFarma.txt.

## Aplicación usando DataBase:

Los mismos pasos pero debemos ejecutar FarmaciaDSS_DataBase.jar con el comando java -jar.

**IMPORTANTE:** Para poder ejecutar correctamente la API y la CLI con base de datos necesitamos importar la que contiene el .rar. El primer paso será utilizar MySQL Workbench y crear una nueva conexión en el puerto 3306. Es importante que tanto el username como el password sean "root". Una vez creada la conexión y dentro de ella, pinchamos en Server -> Data Import y elegimos la carpeta llamada farmaciadss_database. Es importante que la base de datos tenga este nombre. Al finalizar la importación, podremos ejecutar la API como indicamos arriba y comprobar que se conecta correctamente con la base de datos.
