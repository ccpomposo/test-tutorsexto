<!-- Tarea 2
Diseñar un web service rest servidor de los estados de la república para la app  -->
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
    </head>
    <body>
        <g:form method="PUT" class="form-horizontal form-label-left" action="update">
            <input type="hidden" name="id" value="${params.id}">
            Nombre: <input type="text" name="nombre" value="${params.nombre}"><br/>
            Matrícula: <input type="text" name="matricula" value="${params.matricula}"><br/>
            Promedio: <input type="text" name="promedio" value="${params.promedio}"><br/>
            <fieldset class="button">
                <g:submitButton class="save" name="crear" value="Actualizar" >
                </g:submitButton>
            </fieldset>
        </g:form>
    </body>
</html>