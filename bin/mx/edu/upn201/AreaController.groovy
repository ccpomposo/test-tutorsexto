package mx.edu.upn201

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AreaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Area.list(params), model:[areaCount: Area.count()]
    }

    def show(Area area) {
        respond area
    }

    def create() {
        respond new Area(params)
    }

    @Transactional
    def save(Area area) {
        if (area == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (area.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond area.errors, view:'create'
            return
        }

        area.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'area.label', default: 'Area'), area.id])
                redirect area
            }
            '*' { respond area, [status: CREATED] }
        }
    }

    def edit(Area area) {
        respond area
    }

    @Transactional
    def update(Area area) {
        if (area == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (area.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond area.errors, view:'edit'
            return
        }

        area.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'area.label', default: 'Area'), area.id])
                redirect area
            }
            '*'{ respond area, [status: OK] }
        }
    }

    @Transactional
    def delete(Area area) {

        if (area == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        area.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'area.label', default: 'Area'), area.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'area.label', default: 'Area'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
