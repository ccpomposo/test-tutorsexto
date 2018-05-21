package mx.edu.upn201

import java.util.Date;

class Persona {
    String nombre
    String apPaterno
    String apMaterno
    String telefono
    Integer edad
    Date fechaNacimiento
    String genero
    Direccion direccion
    Usuario usuario

    static constraints = {
        apPaterno nullable: true
        apMaterno nullable: true
        fechaNacimiento nullable: true
        edad nullable: true
        genero nullable: true
        direccion nullable: true
    }

    public String toString() {
        return nombre + ' ' + apPaterno + ' ' + apMaterno 
    }
}
