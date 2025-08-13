import { HttpInterceptorFn } from '@angular/common/http';

export const AuthInterceptor: HttpInterceptorFn = (req, next) => {
  const excludedUrls = ['/auth/login', '/auth/register'];

  if (excludedUrls.some(url => req.url.includes(url))) {
    return next(req); 
  }

  const token = localStorage.getItem('authToken');

  if (token) {
    const cloned = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
    return next(cloned);
  }

  return next(req);
};
