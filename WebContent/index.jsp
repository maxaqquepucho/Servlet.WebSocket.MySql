<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
/*Con esto estamos validando si realmente esta logeado el usuario, 
   Pero esta parte se implementa al final, cuando ya todo este implementado OJO!*/
String sesionIniciada =(String) session.getAttribute("sesionIniciada");
       if(sesionIniciada == "iniciada"){
    	   response.sendRedirect("ServletPersona");
    	   System.out.println("redireccion");
      }
      System.out.println(sesionIniciada);
%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link rel="stylesheet" href="css/bootstrap.min.css" >
        <title>Insert title here</title>
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
        <div class="container d-flex justify-content-center align-content-center" style="height: 600px;">
            <div class="row d-flex align-content-center">
                <div class="col-12 border ">
                    <form class="formPersona" action="ServletLogin" method="post">
                        <div class="form-group">
                            <label for="exampleInputEmail1">Usuario</label>
                            <input id="usuario" name="usuario"  type="text" class="form-control"   placeholder="User">
                            <small  class="form-text text-muted">We'll never share your email with anyone else.</small>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">Contrseña</label>
                            <input id="contrseña" name="pass"  type="password" class="form-control" placeholder="Contrseña">
                        </div>

                        <div class="d-flex justify-content-around mb-2">
                            <input id="ingresar" name="ingresar" type="submit"  class="btn btn-primary" value="ingresar">
                            <button  type="reset" name="cancelar" class="btn btn-primary">Cancelar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script src="js/jquery-3.3.1.min.js"> </script>
        <!-- <script src="js/popper.min.js" ></script> -->
        <script src="js/bootstrap.min.js" ></script>
    </body>
</html>
