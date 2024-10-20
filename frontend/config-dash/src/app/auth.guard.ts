import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {
  const token = localStorage.getItem('token'); // Check for token in localStorage
  if (token) {
    return true; // Allow access to the route
  }

  // If no token, redirect to login
  const router = inject(Router); // Use Angular's inject function to get Router
  router.navigate(['/login']);
  return false; // Deny access to the route
};
