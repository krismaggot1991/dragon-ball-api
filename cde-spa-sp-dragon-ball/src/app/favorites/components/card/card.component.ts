import { Component, Input, OnInit } from '@angular/core';
import { Hero } from 'src/app/heroes/interfaces/hero.interface';

@Component({
  selector: 'heroes-hero-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardFavoriteComponent implements OnInit {

  @Input()
  public hero!: Hero;


  ngOnInit(): void {
    if (!this.hero) throw Error('Hero property is required');
  }

}
