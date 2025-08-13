import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment'; 
import { Comment, CommentUpdateDto } from '../models/comment.model';


@Injectable({
  providedIn: 'root'
})
export class CommentService {
  
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  getCommentsByTagId(tagId: number): Observable<Comment[]> {
    return this.http.get<Comment[]>(`${this.apiUrl}/api/comments/tag/${tagId}`);
  }

  createComment(comment: Comment): Observable<Comment> {
    return this.http.post<Comment>(`${this.apiUrl}/api/comments`, comment);
  }

  updateComment(commentId: number, dto: CommentUpdateDto): Observable<Comment> {
    const token = localStorage.getItem('authToken');
    const headers = new HttpHeaders({ Authorization: `Bearer ${token || ''}` });
    return this.http.put<Comment>(`${this.apiUrl}/api/comments/${commentId}`, dto, { headers });
  }

  deleteComment(commentId: number): Observable<void> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    return this.http.delete<void>(`${this.apiUrl}/api/comments/${commentId}`, { headers });
  }


}
