import { Component, NgModule, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { CommentService } from '../../services/comment.service';
import { Comment } from '../../models/comment.model';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { TagService } from '../../services/tag.service';
import { Tag } from '../../models/tag.model';
import { Router } from '@angular/router';



@Component({
  selector: 'app-manage-comments',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule, FormsModule],
  templateUrl: './manage-comments.html',
  styleUrls: ['./manage-comments.css']
})
export class ManageComments implements OnInit {
  comments: Comment[] = [];
  tagId!: number;
  loading = true;
  error = '';
  tags: Tag[]=[]

  constructor(private commentService: CommentService, private route: ActivatedRoute, private tagService: TagService, private router: Router) {}

  ngOnInit(): void {
    this.loadTags();
  }

  loadTags() {
    this.tagService.getAllTags().subscribe({
      next: (data) => {
        this.tags = data;
      },
      error: (err) => {
        console.error('Error fetching tags:', err);
      }
    });
  }

  fetchComments(): void {
    this.loading = true;
    this.error = '';
    this.comments = [];

    this.commentService.getCommentsByTagId(this.tagId).subscribe({
      next: (data) => {
        this.comments = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to load comments';
        this.loading = false;
        console.error(err);
      }
    });
  }

  logout() {
    localStorage.removeItem('authToken');
    this.router.navigate(['/login']);
  }

  updateCommentId: number = 0;
  updateContent: string = '';
  updateMessage: string = '';
  updateError: string = '';

  startEdit(comment: Comment): void {
    this.updateCommentId = comment.id!;
    this.updateContent = comment.content;
  }


  onUpdateComment() {
    this.updateMessage = '';
    this.updateError = '';

    if (!this.updateCommentId || !this.updateContent.trim()) {
      this.updateError = 'Comment ID and new content are required.';
      return;
    }

    const updatedComment: Comment = {
      content: this.updateContent,
    };

    this.commentService.updateComment(this.updateCommentId, updatedComment).subscribe({
        next: () => {
          this.updateMessage = 'Comment updated successfully.';
          this.updateContent = '';
          this.updateCommentId = 0;

          if (this.tagId) {
            this.commentService.getCommentsByTagId(this.tagId).subscribe({
              next: (comments) => this.comments = comments,
              error: (err) => console.error('Failed to refresh comments:', err)
            });
          }
        },
        error: (err) => {
          console.error(err);
      
          if (err.status === 403) {
            alert('You do not have permission to update this comment.');
          } else if (err.status === 404) {
            alert('Comment not found.');
          } else {
            alert('An unexpected error occurred while updating the comment.');
          }
        }
    });
  }

  deleteComment(id: number): void {
    if (!confirm('Are you sure you want to delete this comment?')) {
      return;
    }

    this.commentService.deleteComment(id).subscribe({
      next: () => {
        this.comments = this.comments.filter(comment => comment.id !== id);
      },
      error: (err) => {
        console.error(err);

        if (err.status === 403) {
          alert('You do not have permission to delete this comment.');
        } else if (err.status === 404) {
          alert('Comment not found.');
        } else {
          alert('An unexpected error occurred while deleting the comment.');
        }
      }
    });
  }

}
