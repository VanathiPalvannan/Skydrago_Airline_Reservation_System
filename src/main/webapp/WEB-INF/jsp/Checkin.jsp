<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
	<style type="text/css">
		body{
			background-image: linear-gradient(135deg, #ABDCFF 10%, #0396FF 100%);
		}
	</style>
</head>
<body>
	<div class="table-responsive">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th scope="row">BookingId</th>
					<th scope="row">firstName</th>
					<th scope="row">lastName</th>
					<th scope="row">Gender</th>
					<th scope="row">mobileNumber</th>
					<th scope="row">Checkin</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>{{bookingRecord.bookingId}}</td>
					<td>{{passenger.firstName}}</td>
					<td>{{passenger.lastName}}</td>
					<td>{{passenger.gender}}</td>
					<td>{{passenger.mobileNumber}}</td>
					<td if="!passenger.checkIn.seatNumber"><input type="text"
						class="form-control" path="seatNumber"
						placeholder="Seat Number"> <input type="button"
						class="btn btn-success" value="Checkin"
						ng-click="checkinFlight(passenger.passengerId, seatNumber)">
					</td>
					<td ng-if="passenger.checkIn.seatNumber">Checkin already
						completed: SeatNumber:{{passenger.checkIn.seatNumber}}
						GateNumber:{{passenger.checkIn.gateNumber}}</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>