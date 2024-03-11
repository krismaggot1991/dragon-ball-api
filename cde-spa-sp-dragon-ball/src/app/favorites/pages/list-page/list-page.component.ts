import { Component, OnInit } from '@angular/core';
import { Hero } from 'src/app/heroes/interfaces/hero.interface';
import { FavoritesService } from '../../services/favorites.service';

@Component({
  selector: 'app-list-page',
  templateUrl: './list-page.component.html',
  styles: [
  ]
})
export class ListFavoritePageComponent implements OnInit {

  public heroes: Hero[] = [];

  constructor(private favoritesService: FavoritesService) { }

  ngOnInit(): void {

    this.favoritesService.getCharacters()
      .subscribe(heroes2 => {
        console.log('Respuesta del servicio:', heroes2);
        this.heroes = heroes2.data;
      });
  }

}
