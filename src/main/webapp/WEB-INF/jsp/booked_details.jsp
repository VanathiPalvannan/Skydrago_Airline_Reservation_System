<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
  <%@ page isELIgnored="false" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <!DOCTYPE html>
        <html>

        <head>
          <meta charset="ISO-8859-1">
          <title>Booked Details</title>
        	  <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <style type="text/css">
		body{
			background-image: linear-gradient(135deg, #ABDCFF 10%, #0396FF 100%);
		}
	</style>
        </head>

        <body>
        <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header" data-toggle="collapse" data-target="#myNavbar">
                <button class="navbar-toggle">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="#" class="navbar-brand">SkyDrago Airlines</a>
            </div>
            <div class="collapse navbar-collapse " id="myNavbar">
                <ul class="nav navbar-nav">
                    <li><a href="/home">Home</a></li>
                    <li class="active"><a href="">Ticket Booking</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <div  class="btn btn-group">
                          
							  <a href="#" class="btn btn-primary" >${username}</a>
					
                            <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown"><span
                                    class="caret"></span></button>
                            <ul class="dropdown-menu" role="menu">
                         	 <li><a href="/useredit"><span class="glyphicon glyphicon-user"></span> Edit profile</a></li>
                             <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                         </ul>
                        </div>
                    </li>

                </ul>
            </div>
        </div>
    </nav>
          <div style="margin-top:70px">
          <div align="center">
            <h1>Your Ticket Booked</h1>
          <table class="container table table-striped table-hover table-sm" style="width:90%">
            <tr>
              <td>Name</td>
              <td>${passenger.firstName} ${passenger.lastName}</td>
            </tr>
            <tr>
              <td>Mobile Number</td>
              <td>${passenger.mobileNumber}</td>
            </tr>
            <tr>
              <td>Gender</td>
              <td>${passenger.gender}</td>
            </tr>
            <tr>
              <td>Email Id</td>
              <td>${passenger.emailId}</td>
            </tr>
            <tr>
              <td>Number of Seats Booked</td>
              <td>${passenger.noOfSeatsBooked}</td>
            </tr>
            <tr>
              <td>Flight Class</td>
              <td>${passenger.flightClass}</td>
            </tr>
            <tr>
              <td>Paid Amount</td>
              <td>${passenger.priceOfTicket}</td>
            </tr>
            <tr>
              <td>Flight Number</td>
              <td>${passenger.flight.flightNumber}</td>
            </tr>
            <tr>
              <td>Flight Date</td>
              <td>${passenger.flight.flightDate}</td>
            </tr>
            <tr>
              <td>Flight Time</td>
              <td>${passenger.flight.flightTime}</td>
            </tr>
            <tr>
              <td>Origin</td>
              <td>${passenger.flight.origin}</td>
            </tr>
            <tr>
              <td>Destination</td>
              <td>${passenger.flight.destination}</td>
            </tr>
          </table>
          <div>
            <form action="/ticketsbooked">
              <input type="submit" value="View Ticket" class="btn btn-success">
            </form>
          </div>
          </div>
          </div>
        </body>

        </html>