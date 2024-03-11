package com.pichincha.sp.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

import com.pichincha.services.server.models.CharactersDataResponse;
import com.pichincha.services.server.models.CharactersResponse;
import com.pichincha.services.server.models.SpecificCharacterResponse;
import com.pichincha.services.server.models.SpecificCharactersDataResponse;
import com.pichincha.sp.domain.enums.StatusCodeEnum;
import com.pichincha.sp.repository.ExternalDragonBallRepository;
import com.pichincha.sp.service.DragonBallService;
import com.pichincha.sp.service.dto.DragonBallExternalApiResponse;
import com.pichincha.sp.service.dto.DragonBallExternalCharacterResponse;
import com.pichincha.sp.service.mapper.DragonBallMapper;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = {DragonBallServiceImpl.class},
    initializers = ConfigDataApplicationContextInitializer.class)
class DragonBallServiceTest {

  @Autowired
  DragonBallService dragonBallService;
  @MockBean
  ExternalDragonBallRepository externalDragonBallRepository;
  @MockBean
  DragonBallMapper dragonBallMapper;

  @Test
  void shouldGetAllCharacters() {
    DragonBallExternalApiResponse dragonBallExternalApiResponse = new DragonBallExternalApiResponse();
    DragonBallExternalCharacterResponse dragonBallExternalCharacterResponse = new DragonBallExternalCharacterResponse();
    List<DragonBallExternalCharacterResponse> characters = new ArrayList<>();
    characters.add(dragonBallExternalCharacterResponse);
    dragonBallExternalApiResponse.setCharacters(characters);
    CharactersResponse charactersResponse = new CharactersResponse();
    charactersResponse.setCode(StatusCodeEnum.OK.getCode());
    List<@Valid CharactersDataResponse> data = new ArrayList<>();
    CharactersDataResponse charactersDataResponse = new CharactersDataResponse();
    charactersDataResponse.setId("1");
    data.add(charactersDataResponse);
    charactersResponse.setData(data);
    when(externalDragonBallRepository.getAllCharacters())
        .thenReturn(Mono.just(dragonBallExternalApiResponse));
    when(dragonBallMapper.toCharactersResponse(any(DragonBallExternalApiResponse.class)))
        .thenReturn(charactersResponse);
    Mono<CharactersResponse> response = dragonBallService.getAllCharacters();
    assertEquals("Response should be OK", StatusCodeEnum.OK.getCode(), response.block().getCode());
    verify(externalDragonBallRepository, times(1)).getAllCharacters();
    verify(dragonBallMapper, times(1)).toCharactersResponse(any(DragonBallExternalApiResponse.class));
  }

  @Test
  void shouldGetSpecificCharacter() {
    SpecificCharactersDataResponse specificCharactersDataResponse = new SpecificCharactersDataResponse();
    specificCharactersDataResponse.setDescription(RandomStringUtils.randomAlphabetic(20));
    when(externalDragonBallRepository.getSpecificCharacter(anyString()))
        .thenReturn(Mono.just(new DragonBallExternalCharacterResponse()));
    when(dragonBallMapper.toSpecificCharactersDataResponse(any(DragonBallExternalCharacterResponse.class)))
        .thenReturn(specificCharactersDataResponse);
    Mono<SpecificCharacterResponse> response = dragonBallService.getSpecificCharacter(RandomStringUtils.randomNumeric(1));
    assertEquals("Response should be OK", StatusCodeEnum.OK.getCode(), response.block().getCode());
    verify(externalDragonBallRepository, times(1)).getSpecificCharacter(anyString());
    verify(dragonBallMapper, times(1)).toSpecificCharactersDataResponse(any(DragonBallExternalCharacterResponse.class));
  }
}
