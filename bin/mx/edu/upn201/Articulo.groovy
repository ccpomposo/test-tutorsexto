package mx.edu.upn201

class Articulo {

    Integer cantidad
    String nombre
    String marca
    String numeroSerie
    Date fechaIngreso
    Date fechaCaducidad
    Double costo
    Categoria categoria
    Status status
    String unidadMedida

    static constraints = {
    
    }

    public String toString() {
        return nombre
    }
}
