package mx.edu.upn201

class Archivo {

    byte[] archivo
    String archivoTipo
    String archivoNombre

    static constraints = {
        archivo nullable: true, maxSize: 10485760
    }
}
