import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { FavoritesRoutingModule } from './favorites-routing.module';
import { MaterialModule } from '../material/material.module';

import { FavoriteListComponent } from './components/favorite-list/favorite-list.component';
import { LayoutPageComponent } from './pages/layout-page/layout-page.component';
import { CardFavoriteComponent } from './components/card/card.component';
import { FavoriteImagePipe } from './pipes/favorite-image.pipe';


@NgModule({
    declarations: [
        LayoutPageComponent,
        FavoriteListComponent,
        CardFavoriteComponent,
        FavoriteImagePipe
    ],
    imports: [
        CommonModule,
        ReactiveFormsModule,
        FavoritesRoutingModule,
        MaterialModule
    ]
})
export class FavoritesModule { }
