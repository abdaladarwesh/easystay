package com.ntgschool.easystay.Mappers;

import com.ntgschool.easystay.Dtos.Request.HotelRequest;
import com.ntgschool.easystay.Dtos.Response.HotelResponse;
import com.ntgschool.easystay.Entities.Hotel;
import com.ntgschool.easystay.Entities.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = {RoomMapper.class})
public interface HotelMapper {

    @Mapping(target = "rooms", source = "rooms", qualifiedByName = "getRooms")
    @Mapping(target = "mainImage", source = "mainImage")
    @Mapping(target = "images", source = "images")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "stars", source = "stars")
    @Mapping(target = "rating", source = "rating")
    @Mapping(target = "reviewCount", source = "reviewCount")
    @Mapping(target = "pricePerNight", source = "pricePerNight")
    @Mapping(target = "currency", source = "currency")
    @Mapping(target = "propertyType", source = "propertyType")
    @Mapping(target = "checkInFrom", source = "checkInFrom")
    @Mapping(target = "checkOutUntil", source = "checkOutUntil")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "website", source = "website")
    @Mapping(target = "distanceFromCenterKm", source = "distanceFromCenterKm")
    @Mapping(target = "distanceFromAirportKm", source = "distanceFromAirportKm")
    @Mapping(target = "amenities", source = "amenities")
    @Mapping(target = "popularFacilities", source = "popularFacilities")
    @Mapping(target = "policies", source = "policies")
    HotelResponse toHotelResponse(Hotel hotel);

    @Named("getRooms")
    default List<Room> getRooms(List<Room> rooms) {
        if (rooms == null) {
            return new ArrayList<>();
        }
        return rooms;
    }

    List<HotelResponse> toHotelResponses(List<Hotel> hotels);

    @Mapping(target = "rooms", ignore = true)
    @Mapping(target = "id", source = "id")
    Hotel toHotel(HotelRequest hotelRequest);
}
