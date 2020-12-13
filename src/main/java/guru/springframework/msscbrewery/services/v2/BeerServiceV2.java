package guru.springframework.msscbrewery.services.v2;

import guru.springframework.msscbrewery.web.model.BeerDto;
import guru.springframework.msscbrewery.web.model.v2.BeerDtoV2;

import java.util.UUID;

public interface BeerServiceV2 {
  BeerDto getBeerById(UUID beerId);

  BeerDto saveBeer(BeerDtoV2 beerDtoV2);

  void updateBeer(UUID beerId, BeerDtoV2 beerDtoV2);

  void deleteById(UUID beerId);
}
