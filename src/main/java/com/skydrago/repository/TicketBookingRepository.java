package com.skydrago.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skydrago.entity.Passenger;

@Repository
public interface TicketBookingRepository extends JpaRepository<Passenger, Long> {

	@Modifying
	@Query("UPDATE Seat SET economySeat=economySeat-:noOfSeats WHERE class_id=:seat_id")
	public void updateEconomySeat(@Param("noOfSeats") int noOfSeats,@Param("seat_id") int seat_id);
	
	@Modifying
	@Query("UPDATE Seat SET bussinessSeat=bussinessSeat-:noOfSeats WHERE class_id=:seat_id")
	public void updateBusinessSeat(@Param("noOfSeats") int noOfSeats,@Param("seat_id") int seat_id);
	
	@Modifying
	@Query("UPDATE Seat SET firstSeat=firstSeat-:noOfSeats WHERE class_id=:seat_id")
	public void updateFirstSeat(@Param("noOfSeats") int noOfSeats,@Param("seat_id") int seat_id);
	
	@Query(value="select * from passenger where flight_id=:flightid",nativeQuery=true)
	List<Passenger> getPassengersByFlightId(@Param("flightid")int flightid);
	
	@Query(value="select * from passenger where user_name=:username",nativeQuery=true)
	List<Passenger> getPassengersByUsername(@Param("username")String username);
	
	@Modifying
	@Query("UPDATE Passenger SET is_cancel=true WHERE id=:id")
	public void updateIsCancel(@Param("id")long id);
	
	@Modifying
	@Query("UPDATE Seat SET economySeat=economySeat+:noOfSeats WHERE class_id=:seat_id")
	public void incrementEconomySeat(@Param("noOfSeats") int noOfSeats,@Param("seat_id") int seat_id);
	
	@Modifying
	@Query("UPDATE Seat SET bussinessSeat=bussinessSeat+:noOfSeats WHERE class_id=:seat_id")
	public void incrementBusinessSeat(@Param("noOfSeats") int noOfSeats,@Param("seat_id") int seat_id);
	
	@Modifying
	@Query("UPDATE Seat SET firstSeat=firstSeat+:noOfSeats WHERE class_id=:seat_id")
	public void incrementFirstSeat(@Param("noOfSeats") int noOfSeats,@Param("seat_id") int seat_id);
}
