package mx.edu.upn201

class Categoria {

    String nombre
    String descripcion

    static constraints = {
    }

    public String toString() {
        return nombre + ' ' + descripcion
    }
}
