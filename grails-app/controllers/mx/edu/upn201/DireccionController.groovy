package mx.edu.upn201

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DireccionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Direccion.list(params), model:[direccionCount: Direccion.count()]
    }

    def show(Direccion direccion) {
        respond direccion
    }

    def create() {
        respond new Direccion(params)
    }

    @Transactional
    def save(Direccion direccion) {
        if (direccion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (direccion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond direccion.errors, view:'create'
            return
        }

        direccion.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'direccion.label', default: 'Direccion'), direccion.id])
                redirect direccion
            }
            '*' { respond direccion, [status: CREATED] }
        }
    }

    def edit(Direccion direccion) {
        respond direccion
    }

    @Transactional
    def update(Direccion direccion) {
        if (direccion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (direccion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond direccion.errors, view:'edit'
            return
        }

        direccion.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'direccion.label', default: 'Direccion'), direccion.id])
                redirect direccion
            }
            '*'{ respond direccion, [status: OK] }
        }
    }

    @Transactional
    def delete(Direccion direccion) {

        if (direccion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        direccion.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'direccion.label', default: 'Direccion'), direccion.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'direccion.label', default: 'Direccion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
