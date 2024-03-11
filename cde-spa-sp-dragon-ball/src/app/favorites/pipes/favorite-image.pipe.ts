import { Pipe, PipeTransform } from '@angular/core';
import { Hero } from 'src/app/heroes/interfaces/hero.interface';

@Pipe({
  name: 'heroImage2'
})
export class FavoriteImagePipe implements PipeTransform {

  transform(hero: Hero): string {

    if (!hero.id && !hero.image) {
      return 'assets/no-image.png';
    }

    if (hero.image) return hero.image; // https:///google.com/flash.png

    return `assets/heroes/${hero.id}.jpg`;

  }

}
