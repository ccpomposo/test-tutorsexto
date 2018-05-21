package mx.edu.upn201

import java.util.Date;
class Empleado {

    static belongsTo = [persona: Persona]
    String clave
    String puesto
    String tipoEmpleado
    Boolean isResponsableArea
    Date fechaIngreso
    Area area
    String gradoEstudios

    static constraints = {
        clave nullable: true
        puesto nullable: true
        tipoEmpleado nullable: true
        isResponsableArea nullable: true
        fechaIngreso nullable: true
        gradoEstudios nullable: true
    }

    public String toString() {
        return persona.nombre + " - " + puesto
    }
}
