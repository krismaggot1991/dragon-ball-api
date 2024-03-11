import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutPageComponent } from './pages/layout-page/layout-page.component';
import { FavoriteListComponent } from './components/favorite-list/favorite-list.component';

const routes: Routes = [
    {
        path: '',
        component: LayoutPageComponent,
        children: [
            { path: 'favorite-list', component: FavoriteListComponent },
            { path: '**', redirectTo: 'list' },
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class FavoritesRoutingModule { }
