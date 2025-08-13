import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Tag } from '../models/tag.model';


@Injectable({
  providedIn: 'root'
})
export class TagService {

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    return new HttpHeaders({
      Authorization: `Bearer ${token}`
    });
  }

  getAllTags(): Observable<Tag[]> {
    return this.http.get<Tag[]>(`${this.apiUrl}/api/tags`);
  }

  getTagById(id: number): Observable<Tag> {
    return this.http.get<Tag>(`${this.apiUrl}/api/tags/${id}`);
  }

  createTag(tag: Tag): Observable<Tag> {
    return this.http.post<Tag>(`${this.apiUrl}/api/tags`, tag, {
      headers: this.getAuthHeaders()
    });
  }

  updateTag(id: number, tag: Tag): Observable<Tag> {
    return this.http.put<Tag>(`${this.apiUrl}/api/tags/${id}`, tag, {
      headers: this.getAuthHeaders()
    });
  }

  deleteTag(id: number): Observable<string> {
    return this.http.delete<string>(`${this.apiUrl}/api/tags/${id}`, {
      headers: this.getAuthHeaders(), 
      responseType: 'text' as 'json'
    });
  }

}