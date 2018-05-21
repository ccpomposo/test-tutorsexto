package mx.edu.upn201

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured
import mx.edu.upn201.*

@Secured('ROLE_ADMIN')
@Transactional(readOnly = true)
class PersonaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Persona.list(params), model:[personaCount: Persona.count()]
    }

    def show(Persona persona) {
        respond persona
    }

    def create() {
        respond new Persona(params)
    }

    @Transactional
    def save(Persona persona) {
        if (persona == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (persona.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond persona.errors, view:'create'
            return
        }

        persona.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'persona.label', default: 'Persona'), persona.id])
                redirect persona
            }
            '*' { respond persona, [status: CREATED] }
        }
    }

    def edit(Persona persona) {
        respond persona
    }

    @Transactional
    def update(Persona persona) {
        if (persona == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (persona.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond persona.errors, view:'edit'
            return
        }

        persona.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'persona.label', default: 'Persona'), persona.id])
                redirect persona
            }
            '*'{ respond persona, [status: OK] }
        }
    }

    @Transactional
    def delete(Persona persona) {

        if (persona == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        persona.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'persona.label', default: 'Persona'), persona.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'persona.label', default: 'Persona'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
