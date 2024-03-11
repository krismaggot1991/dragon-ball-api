import { Component, Input, OnInit } from '@angular/core';
import { Hero } from 'src/app/heroes/interfaces/hero.interface';
import { FavoritesService } from '../../services/favorites.service';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'heroes-hero-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardFavoriteComponent implements OnInit {

  @Input()
  public hero!: Hero;

  constructor(private favoritesService: FavoritesService, private router: Router) {

  }


  ngOnInit(): void {
    if (!this.hero) throw Error('Hero property is required');
  }

  deleteFavorite(idCharacter: string): void {

    const token = localStorage.getItem('token');

    if (token !== null) {
      console.log('idCharacter: ', idCharacter);
      console.log('usuario: ', token);

      this.favoritesService.deletefavorite(token, idCharacter).subscribe(resp => {
        console.log(resp);
        if (resp.code === '200') {
          window.location.reload();
        } else {
          Swal.fire({
            allowOutsideClick: true,
            title: 'Error!',
            text: "No se pudo eliminar personaje de favoritos."
          });
        }
      }, (err) => {
        console.log(err.error.error.message);
        Swal.fire({
          allowOutsideClick: true,
          title: 'Error en el registro...',
          text: err.error.error.message
        });

      });

    } else {
      console.error('El token de usuario es nulo.');
      // Aquí puedes manejar la situación en la que el token sea nulo, por ejemplo, redireccionar a la página de inicio de sesión.
    }
  }

}
