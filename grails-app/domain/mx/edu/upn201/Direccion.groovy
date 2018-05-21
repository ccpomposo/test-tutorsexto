package mx.edu.upn201

class Direccion {
    String calle
    String colonia
    Integer numInt
    Integer numExt
    Integer cp

    //Hacer la relación bidireccional de Dirección a Persona en una relación uno a muchos definiendo belongsTo
    static belongsTo = [persona: Persona]

    static constraints = {
        calle nullable: true
        colonia nullable: true
        numInt nullable: true
    }

    public String toString() {
        return String.format("Dirección = Calle: %s Colonia: %s numInt: %d numExt: %dCP: %d",
        calle, colonia, numInt, numExt,cp)
    }
}
