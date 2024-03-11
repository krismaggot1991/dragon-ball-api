import { Component, Input, OnInit } from '@angular/core';
import { Hero } from 'src/app/heroes/interfaces/hero.interface';
import { FavoritesService } from '../../services/favorites.service';
import { AddFavoriteModel } from 'src/app/heroes/interfaces/add-favorite.model';
import Swal from 'sweetalert2';

@Component({
  selector: 'heroes-hero-card-favorite',
  templateUrl: './card-favorite.component.html',
  styleUrls: ['./card-favorite.component.css']
})
export class CardFavoriteAddComponent implements OnInit {

  @Input()
  public hero!: Hero;

  addFavoriteModel: AddFavoriteModel = new AddFavoriteModel();

  constructor(private favoritesService: FavoritesService) {

  }


  ngOnInit(): void {
    if (!this.hero) throw Error('Hero property is required');
  }

  addFavorite(idCharacter: string): void {

    const token = localStorage.getItem('token');

    if (token !== null) {
      console.log('idCharacter: ', idCharacter);
      console.log('usuario: ', token);
      this.addFavoriteModel.characterId = idCharacter;
      this.addFavoriteModel.username = token;
      console.log(this.addFavoriteModel);

      this.favoritesService.addFavorite(this.addFavoriteModel).subscribe(resp => {
        console.log(resp);
        if (resp.code === '200') {
          Swal.fire({
            allowOutsideClick: true,
            title: 'Exito!',
            text: "Personaje agreado a favoritos."
          });
        } else {
          Swal.fire({
            allowOutsideClick: true,
            title: 'Personaje favorito ya agregado en favoritos!',
            text: "Por favor seleccione otro personaje para agregar a favoritos."
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
