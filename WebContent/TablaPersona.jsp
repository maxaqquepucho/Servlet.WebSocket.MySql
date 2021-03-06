<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
        <link rel="stylesheet" href="css/bootstrap.min.css" >
        <style media="screen">
            *{
                box-sizing: border-box;
                margin: 0;
                padding: 0;
            }
            body{
                background: #f5f5f5;
            }

        </style>
	</head>
	<body>
        <div class="container-fluid">
            <div class="row m-2 d-flex justify-content-center ">
                <div class="col-8">
                    <table class="table table-hover table-dark table-responsive" >
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Nombre</th>
                                <th scope="col">Apellidos</th>
                                <th scope="col">Celular</th>
                                <th scope="col">Botones</th>
                                <th scope="col">Botones 2</th>
                            </tr>
                        </thead>
                        <tbody id="personas">

                            <c:forEach var="n" items="${lista}" >
                                <%-- begin="0" end="5" --%>
                                <tr>
                                    <td scope="row"><c:out value = "${n.codigo}"/></td>
                                    <td>${n.nombre}</td>
                                    <td>${n.apellido}</td>
                                    <td>${n.dni}</td>
                                    <td>
                                        <a href="#" class="btn btn-primary  eliminar" onclick="eliminarFila(this)">Eliminar</a>
                                    </td>
                                    <td>
                                        <a href="#" class="btn btn-warning" onclick="editarFila(this)">Editar</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-4 border border p-2">
                    <form id="formPersona">
                        <div class="form-group" >
                            <div class="row">
                                <div class="col-7">
                                    <label for="">Codigo</label>
                                    <input id="codigo" name="codigo"  type="text" class="form-control"   placeholder="Codigo">
                                </div>
                                <div class="col-4">
                                    <label for="">Idice Fila</label>
                                    <input id="indiceFila" name="indice"  type="text" class="form-control"   placeholder="Indice">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Nombres</label>
                            <input id="nombre" name="nombre"  type="text" class="form-control"   placeholder="Ingrese sus Nombres">
                            <small  class="form-text text-muted">We'll never share your email with anyone else.</small>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">Apellidos</label>
                            <input id="apellido" name="apellido" value="Vargas" type="text" class="form-control" placeholder="Ingrese sus Apellidos">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">DNI</label>
                            <input id="dni" name="dni" value="164546" type="text" class="form-control" placeholder="Numero de Celular">
                        </div>

                        <div class="d-flex justify-content-around mb-2">
                            <input id="btnAgregar" name="btnAgregar" type="button"  class="btn btn-primary" value="Agregar">
                            <button  type="reset" name="cancelar" class="btn btn-primary">Cancelar</button>
                        </div>

                    </form>
                </div>
            </div>

            <div class="row d-flex justify-content-center">
                <div class="col-6">
                    <a href="ServletCerrarSesion" class="btn btn-dark">Cessar Session</a>
                </div>
                <div class="col-4">
                    <input id="nombreUsuario" type="text" name="nombreUsuario"   class="form-control" value="Max" >
                </div>
            </div>
        </div>

        <script src="js/jquery-3.3.1.min.js"> </script>
        <!-- <script src="js/popper.min.js" ></script> -->
        <script src="js/bootstrap.min.js" ></script>
         <script src="js/CrudTabla.js"></script>

	</body>
</html>
