package mx.edu.upn201

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class EstudianteController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Estudiante.list(params), model:[estudianteCount: Estudiante.count()]
    }

    def show(Estudiante estudiante) {
        respond estudiante
    }

    def create() {
        respond new Estudiante(params)
    }

    @Transactional
    def save(Estudiante estudiante) {
        if (estudiante == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (estudiante.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond estudiante.errors, view:'create'
            return
        }

        estudiante.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'estudiante.label', default: 'Estudiante'), estudiante.id])
                redirect estudiante
            }
            '*' { respond estudiante, [status: CREATED] }
        }
    }

    def edit(Estudiante estudiante) {
        respond estudiante
    }

    @Transactional
    def update(Estudiante estudiante) {
        if (estudiante == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (estudiante.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond estudiante.errors, view:'edit'
            return
        }

        estudiante.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'estudiante.label', default: 'Estudiante'), estudiante.id])
                redirect estudiante
            }
            '*'{ respond estudiante, [status: OK] }
        }
    }

    @Transactional
    def delete(Estudiante estudiante) {

        if (estudiante == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        estudiante.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'estudiante.label', default: 'Estudiante'), estudiante.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'estudiante.label', default: 'Estudiante'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
