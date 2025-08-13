import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const token = localStorage.getItem('authToken');

  if (token) {
    return true; // allow access
  } else {
    router.navigate(['/login']); // redirect to login if not authenticated
    return false;
  }
};