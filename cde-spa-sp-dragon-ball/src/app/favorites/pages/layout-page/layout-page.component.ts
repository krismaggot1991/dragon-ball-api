import { Component } from '@angular/core';
import { AuthService } from 'src/app/auth/services/auth.service';
import { Router } from '@angular/router';
import { User } from 'src/app/auth/interfaces/user.interface';

@Component({
  selector: 'app-layout-page',
  templateUrl: './layout-page.component.html',
  styles: [
  ]
})
export class LayoutPageComponent {
  public sidebarItems = [
    { label: 'Listado', icon: 'label', url: '../heroes/list' },
    { label: 'Ver favoritos', icon: 'favorite', url: '../favorites/favorite-list' },
    { label: 'Registrar usuario', icon: 'person_add', url: '../auth/new-account' },
    { label: 'Agregar favoritos', icon: 'star', url: '../favorites/favorite-add' },
  ];

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  get user(): User | undefined {
    return this.authService.currentUser;
  }

  onLogout() {
    this.authService.logout();
    this.router.navigate(['/auth/login'])
  }

}
