package mx.edu.upn201

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN','ROLE_OPERADOR','ROLE_USUARIO'])
@Transactional(readOnly = false)
class BienvenidaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Bienvenida.list(params), model:[bienvenidaCount: Bienvenida.count()]
    }

    def show(Bienvenida bienvenida) {
        respond bienvenida
    }

    def create() {
        respond new Bienvenida(params)
    }

    @Transactional
    def save(Bienvenida bienvenida) {
        if (bienvenida == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (bienvenida.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond bienvenida.errors, view:'create'
            return
        }

        bienvenida.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'bienvenida.label', default: 'Bienvenida'), bienvenida.id])
                redirect bienvenida
            }
            '*' { respond bienvenida, [status: CREATED] }
        }
    }

    def edit(Bienvenida bienvenida) {
        respond bienvenida
    }

    @Transactional
    def update(Bienvenida bienvenida) {
        if (bienvenida == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (bienvenida.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond bienvenida.errors, view:'edit'
            return
        }

        bienvenida.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'bienvenida.label', default: 'Bienvenida'), bienvenida.id])
                redirect bienvenida
            }
            '*'{ respond bienvenida, [status: OK] }
        }
    }

    @Transactional
    def delete(Bienvenida bienvenida) {

        if (bienvenida == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        bienvenida.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'bienvenida.label', default: 'Bienvenida'), bienvenida.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'bienvenida.label', default: 'Bienvenida'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
