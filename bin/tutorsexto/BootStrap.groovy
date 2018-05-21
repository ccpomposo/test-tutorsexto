package tutorsexto

import mx.edu.upn201.*;
import java.util.Date;

class BootStrap {

    def init = { servletContext ->
    	def rolAdmin = new Rol(authority: 'ROLE_ADMIN').save()
    	def rolOpera = new Rol(authority: 'ROLE_OPERADOR').save()
    	def rolUsuario = new Rol(authority: 'ROLE_USUARIO').save()

    	def usuAdmin = new Usuario(username: 'admin', password: 'admin').save()
    	def usuOper = new Usuario(username: 'operador', password: 'operador').save()
    	def usuSis = new Usuario(username: 'usuario', password: 'usuario').save()

    	UsuarioRol.create usuAdmin, rolAdmin
    	UsuarioRol.create usuOper, rolOpera
    	UsuarioRol.create usuSis, rolUsuario

    	UsuarioRol.withSession {
    		it.flush()
    		it.clear()
    	}
    }
    def destroy = {
    }
}
