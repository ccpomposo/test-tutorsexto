package mx.edu.upn201

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import org.grails.web.json.JSONElement
import groovy.json.JsonOutput

@Secured(['ROLE_ADMIN', 'ROLE_OPERADOR', 'ROLE_USUARIO'])
@Transactional(readOnly = false)
class ConsumidorRestController {

    static final String urlRest = "http://localhost:8080/"
    def index() { 
        def el_id = 0;
        List<Kardex> listKardex = new ArrayList<Kardex>();
        //GET - WS Rest
        RestResponse rResponse = new RestBuilder().get(urlRest+ "Kardex")
        if(rResponse.statusCode.value() == 200 && rResponse.json) {
            System.out.println rResponse.json
            def m = rResponse.json
            for(je in m) {
                Kardex k = new Kardex();
                k.id = je.id
                k.nombre = je.nombre
                k.matricula = je.matricula
                k.promedio = je.promedio
                listKardex.add(k)
                el_id = je.id
            }
            el_id = el_id +1
            System.out.println "id final = " + el_id
        } else {
            System.out.println "error en restful"
        }
        for(Kardex k : listKardex) {
            System.out.println k.id + "-" + k.nombre + "-" + k.matricula + "-" + k.promedio
        }
        [miLista : listKardex, el_id: el_id]
    }

    //POST - WS Rest
    @Transactional
    def guardar() {
        RestResponse rResponse = new RestBuilder().post(urlRest +"Kardex") {
            accept('application/json')
            contentType('application/json')
            json(JsonOutput.toJson([id: params.el_id, nombre: params.nombre, matricula: params.matricula, promedio: params.promedio]))

        }
        if( rResponse.statusCode.value() == 201 ) {
            System.out.println "Kardex creado en el servidor"
        }
        redirect(controller: "ConsumidorRest", action: "index")
    }

    @Transactional
    def eliminar() {
        String urlDelete = urlRest + "Kardex/"+ params.id
        System.out.println urlDelete
        RestResponse rResponse = new RestBuilder().delete(urlDelete)
        System.out.println rResponse.statusCode.value()
        if(rResponse.statusCode.value() == 204) {
            System.out.println "Kardex eliminado en el servidor"
        }
        redirect(controller: "ConsumidorRest", action: "index")
    }

    @Transactional
    def update() {
        System.out.println JsonOutput.toJson([id: params.id, nombre: params.nombre, matricula: params.matricula, promedio: params.promedio])
        RestResponse rResponse = new RestBuilder().put(urlRest +"Kardex/"+ params.id) {
            accept('application/json')
            contentType('application/json')
            json(JsonOutput.toJson([id: params.id.toInteger(), nombre: params.nombre, matricula: params.matricula, promedio: params.promedio.toFloat()]))
        }
        if( rResponse.statusCode.value() == 204 || rResponse.statusCode.value() == 200) {
            System.out.println "Kardex actualizado en el servidor"
        } else {
            System.out.println rResponse.statusCode.value()
        }
        redirect(controller: "ConsumidorRest", action: "index")
    }

    def edit() {
        render (view:'edit.gsp')
    }

}
