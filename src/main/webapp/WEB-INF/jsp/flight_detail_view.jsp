<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
  <%@ page isELIgnored="false" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <!DOCTYPE html>
        <html>

        <head>
          <meta charset="ISO-8859-1">
          <title>successfully booked</title>
          <style type="text/css">
		body{
			background-image: linear-gradient(135deg, #ABDCFF 10%, #0396FF 100%);
		}
	</style>
        </head>

        <body>
          <table>
            <tr>
              <th>Flight Id</th>
              <th>Origin</th>
              <th>Destination</th>
              <th>Duration</th>
              <th>Flight Date</th>
              <th>Flight Number</th>
              <th>Flight Time</th>
              <th colspan="2">First Class</th>
              <th colspan="2">Economy Class</th>
              <th colspan="2">Business Class</th>
              <th>Opertations</th>
            </tr>
            <c:forEach items="${flightDetails}" var="flight">
              <tr>
                <td>${flight.id}</td>
                <td>${flight.origin}</td>
                <td>${flight.destination}</td>
                <td>${flight.duration}</td>
                <td>${flight.flightDate}</td>
                <td>${flight.flightNumber}</td>
                <td>${flight.flightTime}</td>
                <td>${flight.seats.firstSeat}</td>
                <td>${flight.seats.firstCost}</td>
                <td>${flight.seats.economySeat}</td>
                <td>${flight.seats.economyCost}</td>
                <td>${flight.seats.bussinessSeat}</td>
                <td>${flight.seats.bussinessCost}</td>
                <td><a href="bookdetail/${flight.id}" class="btn btn-success">Book</a></td>
              </tr>
              <tr>
              </tr>
            </c:forEach>
          </table>

        </body>

        </html>