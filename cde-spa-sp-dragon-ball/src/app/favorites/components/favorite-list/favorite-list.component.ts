import { Component, Input, OnInit } from '@angular/core';
import { Hero } from 'src/app/heroes/interfaces/hero.interface';

@Component({
  selector: 'app-favorite-list',
  templateUrl: './favorite-list.component.html',
  styleUrls: ['./favorite-list.component.css']
})
export class FavoriteListComponent implements OnInit {

  @Input()
  public hero!: Hero;


  ngOnInit(): void {
    const token = localStorage.getItem('token');
    console.info(token);
    if (!this.hero) throw Error('Hero property is required');
  }

}
