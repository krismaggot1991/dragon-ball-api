package com.pichincha.sp.service.mapper;

import static lombok.AccessLevel.PROTECTED;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import com.pichincha.services.server.models.CharactersDataResponse;
import com.pichincha.services.server.models.CharactersResponse;
import com.pichincha.sp.service.dto.DragonBallExternalApiResponse;
import com.pichincha.sp.service.dto.DragonBallExternalCharacterResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.experimental.FieldDefaults;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = SPRING, builder = @Builder(disableBuilder = true))
@FieldDefaults(level = PROTECTED)
public abstract class DragonBallMapper {

  protected CharactersDataResponse toCharactersDataResponse(DragonBallExternalCharacterResponse dragonBallExternalCharacterResponse) {
    CharactersDataResponse charactersDataResponse = new CharactersDataResponse();
    charactersDataResponse.setId(dragonBallExternalCharacterResponse.getId());
    charactersDataResponse.setGender(dragonBallExternalCharacterResponse.getGender());
    charactersDataResponse.setImage(dragonBallExternalCharacterResponse.getImage());
    charactersDataResponse.name(dragonBallExternalCharacterResponse.getName());
    charactersDataResponse.setKi(dragonBallExternalCharacterResponse.getKi());
    return charactersDataResponse;
  }

  protected List<CharactersDataResponse> toCharactersDataResponseList(DragonBallExternalApiResponse dragonBallExternalApiResponse) {
    if (dragonBallExternalApiResponse.getCharacters() == null) {
      return new ArrayList<>();
    }
    return dragonBallExternalApiResponse.getCharacters().stream().map(this::toCharactersDataResponse).collect(Collectors.toList());
  }

  @Mapping(target = "data", expression = "java(toCharactersDataResponseList(dragonBallExternalApiResponse))")
  public abstract CharactersResponse toCharactersResponse(DragonBallExternalApiResponse dragonBallExternalApiResponse);

}
