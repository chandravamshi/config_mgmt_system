import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component'; // Adjust the path as necessary
import { DashboardComponent } from './dashboard/dashboard.component'; // Adjust the path as necessary
import { authGuard } from './auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate: [authGuard],
  }, // Protect this route with the guard
];
