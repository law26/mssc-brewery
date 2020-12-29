package guru.springframework.msscbrewery.web.controller.v2;

import guru.springframework.msscbrewery.services.v2.BeerServiceV2;
import guru.springframework.msscbrewery.web.model.BeerDto;
import guru.springframework.msscbrewery.web.model.v2.BeerDtoV2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("/api/v2/beer")
@RestController
public class BeerControllerV2 {

  private final BeerServiceV2 beerServiceV2;

  public BeerControllerV2(BeerServiceV2 beerServiceV2) {
    this.beerServiceV2 = beerServiceV2;
  }

  @GetMapping({"/{beerId}"})
  public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId) {

    return new ResponseEntity<>(beerServiceV2.getBeerById(beerId), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Object> handlePost(@Valid @RequestBody BeerDtoV2 beerDtoV2) {

    BeerDto savedDto = beerServiceV2.saveBeer(beerDtoV2);

    HttpHeaders headers = new HttpHeaders();
    headers.add("Location", "/api/v1/beer" + savedDto.getId().toString());

    return new ResponseEntity<>(headers, HttpStatus.CREATED);
  }

  @PutMapping("/{beerId}")
  public ResponseEntity<Object> handleUpdate(
      @PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDtoV2 beerDtoV2) {
    beerServiceV2.updateBeer(beerId, beerDtoV2);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/{beerId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteBeer(@PathVariable("beerId") UUID beerId) {
    beerServiceV2.deleteById(beerId);
  }
}
