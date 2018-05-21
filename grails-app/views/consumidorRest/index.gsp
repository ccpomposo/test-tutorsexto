<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
    </head>
    <body>
        <g:if test="${miLista == null}">
            VACÍA
        </g:if>
        <table id="example">
            <g:each in="${miLista}" var="p">
                <tr>
                    <tr>
                        <td>${p.id}</td>
                        <td>${p.nombre}</td>
                        <td>${p.matricula}</td>
                        <td>${p.promedio}</td>
                        <td>
                            <g:form method="DELETE" action="eliminar">
                                <input type="hidden" name="id" value="${p.id}" />
                                <input type="submit" value="Eliminar" />
                            </g:form>
                        </td>
                        <td>
                            <g:form method="get" action="edit">
                                <input type="hidden" name="id" value="${p.id}" />
                                <input type="hidden" name="nombre" value="${p.nombre}" />
                                <input type="hidden" name="matricula" value="${p.matricula}" />
                                <input type="hidden" name="promedio" value="${p.promedio}" />
                                <input type="submit" value="Actualizar" />
                            </g:form>
                        </td>
                    </tr>
                </tr>
            </g:each>
        </table>
        <br/>
        <g:form method="POST" class="form-horizontal form-label-left" action="guardar">
            <input type="hidden" name="el_id" value="${el_id}">
            Nombre: <input type="text" name="nombre"><br/>
            Matrícula: <input type="text" name="matricula"><br/>
            Promedio: <input type="text" name="promedio"><br/>
            <fieldset class="button">
                <g:submitButton class="save" name="crear" value="Crear" >
                </g:submitButton>
            </fieldset>
        </g:form>
    </body>
</html>