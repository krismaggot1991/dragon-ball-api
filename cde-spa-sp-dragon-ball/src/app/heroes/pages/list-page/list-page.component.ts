import { Component, OnInit } from '@angular/core';
import { Hero } from '../../interfaces/hero.interface';
import { HeroesService } from '../../services/heroes.service';

@Component({
  selector: 'app-list-page',
  templateUrl: './list-page.component.html',
  styles: [
  ]
})
export class ListPageComponent implements OnInit {

  public heroes: Hero[] = [];

  constructor(private heroesService: HeroesService) { }

  ngOnInit(): void {

    this.heroesService.getCharacters()
      .subscribe(heroes2 => {
        console.log('Respuesta del servicio:', heroes2);
        this.heroes = heroes2.data;
      });
  }

}
