package mx.edu.upn201

import java.util.Date;
class Estudiante {

    static belongsTo = [persona: Persona]
    String matricula
    String carrera
    String semestre
    String grupo
    Boolean isActivo
    Date fechaIngreso
    Date fechaBaja
    Date fechaReingreso

    static constraints = {
        grupo nullable: true
        isActivo nullable: true
        fechaBaja nullable: true
        fechaReingreso nullable: true
    }
}
