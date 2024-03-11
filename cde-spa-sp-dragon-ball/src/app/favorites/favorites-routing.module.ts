import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutPageComponent } from './pages/layout-page/layout-page.component';
import { FavoriteListComponent } from './components/favorite-list/favorite-list.component';
import { ListFavoritePageComponent } from './pages/list-page/list-page.component';

const routes: Routes = [
    {
        path: '',
        component: LayoutPageComponent,
        children: [
            { path: 'favorite-list', component: FavoriteListComponent },
            { path: 'favorite-add', component: ListFavoritePageComponent },
            { path: '**', redirectTo: 'list' },
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class FavoritesRoutingModule { }
