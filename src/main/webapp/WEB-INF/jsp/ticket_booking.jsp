<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
  <%@ page isELIgnored="false" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <!DOCTYPE html>
        <html>

        <head>
          <meta charset="ISO-8859-1">
          <title>ticket booking</title>
          
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
           .error {
              color: red;
            }
          </style>
          <script type="text/javascript">
            function checkAvailableSeats() {
              var firstClassSeats = ${ flight.seats.firstSeat };
              var businessClassSeats = ${ flight.seats.bussinessSeat };
              var economyClassSeats = ${ flight.seats.economySeat };
              var flightClass = document.getElementById("flightClass").value;
              var seatsBooked = document.getElementById("seatsBooked").value;
              if (seatsBooked > economyClassSeats && economyClassSeats > 1 && flightClass === "Economy Class") {
                alert("Only " + economyClassSeats + " seats are available in " + flightClass);
                return false;
              }
              else if (seatsBooked > economyClassSeats && economyClassSeats == 1 && flightClass === "Economy Class") {
                alert("Only " + economyClassSeats + " seat is available in " + flightClass);
                return false;
              }
              else if (seatsBooked > businessClassSeats && businessClassSeats > 1 && flightClass === "Business Class") {
                alert("Only " + businessClassSeats + " seats are available in " + flightClass);
                return false;
              }
              else if (seatsBooked > businessClassSeats && businessClassSeats == 1 && flightClass === "Business Class") {
                alert("Only " + businessClassSeats + " seat is available in " + flightClass);
                return false;
              }
              else if (seatsBooked > firstClassSeats && firstClassSeats > 1 && flightClass === "First Class") {
                alert("Only " + firstClassSeats + " seats are available in " + flightClass);
                return false;
              }
              else if (seatsBooked > firstClassSeats && firstClassSeats == 1 && flightClass === "First Class") {
                alert("Only " + firstClassSeats + " seat is available in " + flightClass);
                return false;
              }
              else if ((seatsBooked > businessClassSeats && businessClassSeats == 0 && flightClass === "Business Class") || (seatsBooked > economyClassSeats && economyClassSeats == 0 && flightClass === "Economy Class") || (seatsBooked > firstClassSeats && firstClassSeats == 0 && flightClass === "First Class")) {
                alert("No seat available");
                return true;
              }

              return true;
            }
          </script>
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
                        <div class="btn btn-group">
                        	<a href="#" class="btn btn-primary" >${username}</a>                		
        					<button class="btn btn-primary dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></button>                        
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
          	
          	<table class="table table-striped table-hover table-bordered table-sm">
            <thead class="table table-dark">
              <tr>
                <th>Origin</th>
                <th>Destination</th>
                <th>Duration</th>
                <th>Flight Date</th>
                <th>Flight Number</th>
                <th>Flight Time</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>${flight.origin}</td>
                <td>${flight.destination}</td>
                <td>${flight.duration}</td>
                <td>${flight.flightDate}</td>
                <td>${flight.flightNumber}</td>
                <td>${flight.flightTime}</td>
              </tr>
            </tbody>
          </table>
          <div align="center"> 
            <form action="/seats/${flight.id}" class="container form-inline">
                <div class="form-group">
                  Number of seats: <input class="form-control" type="number" name="noOfSeats" value="${seats}" min=1 max=5 required>
                </div>
                <div class="form-group">
                  Flight Class:<select class="form-control" name="flightClass">
                    <option value="First Class">First Class</option>
                    <option value="Economy Class">Economy Class</option>
                    <option value="Business Class">Business Class</option>
                    <option></option>
                  </select>
                </div>
                <button type="submit" class="btn btn-success">Submit</button>
              </form>
          </div>
          <hr>
          <div class="row">
            <div class="col-md-1"></div>
              <div class="col-md-5">
                <c:if test="${seats >= 1}">
                    <form:form action="/storePassenger/${seats}/${flight.id}/${flightClass}" method="get"
                      modelAttribute="passenger" onSubmit="return checkAvailableSeats()">
                      <div class="form-group">
                        <form:label path="firstName">First Name</form:label>
                        <form:input path="firstName" class="form-control" />
                        <form:errors path="firstName" cssClass="error"></form:errors>
                      </div>
                      <div class="form-group">
                        <form:label path="lastName">Last Name</form:label>
                        <form:input path="lastName" class="form-control" />
                        <form:errors path="lastName" cssClass="error"></form:errors>
                      </div>
                      <div class="form-group">
                        <form:label path="mobileNumber">Mobile Number</form:label>
                        <form:input path="mobileNumber" class="form-control" />
                        <form:errors path="mobileNumber" cssClass="error"></form:errors>
                      </div>
                      <div class="form-group">
                        <form:label path="gender">Gender:</form:label>
                        <form:radiobutton path="gender" value="Male" />Male
                        <form:radiobutton path="gender" value="Female" />Female
                        <form:errors path="gender" cssClass="error"></form:errors>
                      </div>
                      <div class="form-group">
                        <form:label path="emailId">Email Id</form:label>
                        <form:input path="emailId" class="form-control" type="email" />
                        <form:errors path="emailId" cssClass="error"></form:errors>
                      </div>
                    </div>
                      <div class="col-md-5">
                        <div class="form-group">
                            <form:label path="flightClass">Flight Class</form:label>
                            <form:input path="flightClass" id="flightClass" value="${flightClass}" class="form-control"
                              readonly="${true}" />
                          </div>
                          <div class="form-group">
                            <form:label path="noOfSeatsBooked">Number of seats</form:label>
                            <form:input path="noOfSeatsBooked" type="number" value="${seats}" class="form-control" id="seatsBooked"
                              readonly="${true}" />
                          </div>
                          <div class="form-group">
                            <c:if test="${flightClass == 'First Class'}">
                              <form:label path="priceOfTicket">Price</form:label>
                              <form:input path="priceOfTicket" type="number" value="${flight.seats.firstCost * seats}"
                                class="form-control" readonly="${true}" />
                            </c:if>
                            <c:if test="${flightClass == 'Business Class'}">
                              <form:label path="priceOfTicket">Price</form:label>
                              <form:input path="priceOfTicket" type="number" value="${flight.seats.bussinessCost * seats}"
                                class="form-control" readonly="${true}" />
                            </c:if>
                            <c:if test="${flightClass == 'Economy Class'}">
                              <form:label path="priceOfTicket">Price</form:label>
                              <form:input path="priceOfTicket" type="number" value="${flight.seats.economyCost * seats}"
                                class="form-control" readonly="${true}" />
                            </c:if>
                      </div>
                    </div>
                      <div class="col-md-1"></div>
                        <form:hidden path="flight" value="${flight.id}" />
                        <input type="submit" value="Book your Seat" class="btn btn-success btn-block" />
                    </form:form>
                  </c:if>
          
          	
          </div>
        </body>

        </html>