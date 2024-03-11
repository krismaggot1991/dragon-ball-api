import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { FavoritesRoutingModule } from './favorites-routing.module';
import { MaterialModule } from '../material/material.module';

import { FavoriteListComponent } from './components/favorite-list/favorite-list.component';
import { LayoutPageComponent } from './pages/layout-page/layout-page.component';
import { CardFavoriteComponent } from './components/card/card.component';
import { FavoriteImagePipe } from './pipes/favorite-image.pipe';
import { ListFavoritePageComponent } from './pages/list-page/list-page.component';
import { CardFavoriteAddComponent } from './components/card-favorite/card-favorite.component';


@NgModule({
    declarations: [
        LayoutPageComponent,
        FavoriteListComponent,
        CardFavoriteComponent,
        FavoriteImagePipe,
        ListFavoritePageComponent,
        CardFavoriteAddComponent
    ],
    imports: [
        CommonModule,
        ReactiveFormsModule,
        FavoritesRoutingModule,
        MaterialModule
    ]
})
export class FavoritesModule { }
