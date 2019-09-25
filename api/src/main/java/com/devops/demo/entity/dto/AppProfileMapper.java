//package com.fsoft.trainingdemo.entity.dto;
//
//import AppProfile;
//import org.mapstruct.IterableMapping;
//import org.mapstruct.Mapper;
//import org.mapstruct.Named;
//
//import java.util.List;
//
///**
// * Created by DatVM2
// * Date: 29/01/2019
// * Time: 10:09
// */
//@Mapper(componentModel = "spring")
//@Named("AppProfileMapper")
//public abstract class AppProfileMapper {
//    @Named("toDTO")
//    public abstract AppProfileDTO toDTO(AppProfile appProfile);
//
//    @IterableMapping(qualifiedByName = "toDTO")
//    @Named("toAppProfileDTOs")
//    public abstract List<AppProfileDTO> ToDTOs(List<AppProfile> appProfiles);
//}
