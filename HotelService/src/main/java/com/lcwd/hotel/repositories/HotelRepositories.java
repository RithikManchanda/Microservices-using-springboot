package com.lcwd.hotel.repositories;

import com.lcwd.hotel.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepositories extends JpaRepository<Hotel,String> {


}
