import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('authToken');
    return new HttpHeaders({
      Authorization: `Bearer ${token || ''}`
    });
  }

  getCurrentUser(): Observable<{ username: string }> {
    return this.http.get<{ username: string }>(`${this.apiUrl}/api/users/me`, {
      headers: this.getAuthHeaders()
    });
  }
}
