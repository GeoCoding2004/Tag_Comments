import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { tap } from 'rxjs/operators';

import { Observable } from 'rxjs';
import { RegisterRequest, AuthResponse } from '../models/auth.models';



@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  register(request: RegisterRequest): Observable<string> {
    return this.http.post(`${this.apiUrl}/auth/register`, request, { responseType: 'text' });
  }


  login(request: { username: string; password: string }): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/auth/login`, request).pipe(
      tap(response => {
        localStorage.setItem('authToken', response.token);
      })
    );
  }

  getToken(): string | null {
  return localStorage.getItem('authToken');
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  logout(): void {
    localStorage.removeItem('authToken');
  }

}
