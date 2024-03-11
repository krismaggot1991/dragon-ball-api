import { Component, Input, OnInit } from '@angular/core';
import { Hero } from 'src/app/heroes/interfaces/hero.interface';
import { FavoritesService } from '../../services/favorites.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-favorite-list',
  templateUrl: './favorite-list.component.html',
  styleUrls: ['./favorite-list.component.css']
})
export class FavoriteListComponent implements OnInit {
  public heroes: Hero[] = [];

  constructor(
    private favoritesService: FavoritesService,

    private router: Router,
  ) { }


  ngOnInit(): void {
    const token = localStorage.getItem('token');
    console.info(token);

    if (token !== null) {
      this.favoritesService.getFavoritesFromUser(token)
        .subscribe(heroes2 => {
          console.log('Respuesta del servicio:', heroes2);
          this.heroes = heroes2.data;
        });
    } else {
      // Manejo en caso de que el token sea nulo
      console.error('El token no está presente en el almacenamiento local.');
    }

  }

}
