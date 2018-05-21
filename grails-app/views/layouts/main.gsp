<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:stylesheet src="application.css"/>
    <asset:stylesheet src="bootstrap.css" />
    <asset:stylesheet src="miEstilo.css" />
    <asset:stylesheet src="datatables.css" />

    <asset:javascript src="jquery-1.12.4.js" />
    <asset:javascript src="popper.min.js" />
    <asset:javascript src="bootstrap.min.js" />
    <asset:javascript src="miLibreria.js" />
    <asset:javascript src="datatables.js" />
    
    <g:layoutHead/>
</head>
<body>

    <sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_OPERADOR,ROLE_USUARIO'>
    <nav class="navbar navbar-expand-md navbar bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">
                <g:img dir="images" file="upn_logo.png" />
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="${createLink(uri: '/')}">
                            Inicio <span class="sr-only">(actual)</span>
                        </a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false">
                            Recursos Humanos
                        </a>
                        <div class="dropdown-menu">
                                <a class="dropdown-item" href="${request.getContextPath()}/persona/index">
                                    Persona</a>
                                <a class="dropdown-item" href="${request.getContextPath()}/area/index">
                            Área</a>
                                <a class="dropdown-item" href="${request.getContextPath()}/empleado/index">
                            Empleado</a>
                            <sec:ifNotGranted roles="ROLE_ADMIN,ROLE_OPERADOR,ROLE_USUARIO">
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="${request.getContextPath()}/estudiante/index">
                                Archivo</a>
                            </sec:ifNotGranted>
                        </div>
                    </li>
                    <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_OPERADOR,ROLE_USUARIO">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button"
                             data-toggle="dropdown" aria-haspopup="true"
                             aria-expanded="false">Almacén</a>
                             <div class="dropdown-menu">
                                <a class="dropdown-item" href="${request.getContextPath()}/articulo/index">
                                    Artículo
                                </a>
                                <a class="dropdown-item" href="${request.getContextPath()}/categoria/index">
                                    Categoría
                                </a>
                                <a class="dropdown-item" href="${request.getContextPath()}/status/index">
                                    Estatus
                                </a>
                             </div>
                        </li>
                    </sec:ifAnyGranted>
                    <li class="nav-item">
                        <a class="nav-link" href="${request.getContextPath()}/consumidorRest/index">
                            Servicios
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${request.getContextPath()}/logoff">
                        <g:img dir="images" file="logout.png"></g:img></a>
                    </li>
                </ul>
                <sec:ifLoggedIn>
                    <div class="bg-inverse">
                        Bienvenido&nbsp;<h4><sec:username/></h4>
                    </div>
                </sec:ifLoggedIn>
            </div>
        </div>
    </nav>
    </sec:ifAnyGranted>

    <g:layoutBody/>

    <div class="footer" role="contentinfo"></div>

    <div id="spinner" class="spinner" style="display:none;">
        <g:message code="spinner.alt" default="Loading&hellip;"/>
    </div>

    <asset:javascript src="application.js"/>

</body>
</html>
