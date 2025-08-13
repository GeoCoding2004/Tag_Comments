import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CommentService } from '../../services/comment.service';
import { Router, RouterModule } from '@angular/router';
import { TagService } from '../../services/tag.service';
import { Tag } from '../../models/tag.model';

@Component({
  selector: 'app-create-comment',
  standalone:true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './create-comment.html',
  styleUrl: './create-comment.css'
})
export class CreateCommentComponent implements OnInit {
  commentForm: FormGroup;
  tags: Tag[] = [];

  constructor(
    private fb: FormBuilder,
    private commentService: CommentService,
    private router: Router,
    private tagService:TagService) {
    this.commentForm = this.fb.group({
      content: ['', Validators.required],       
      tagId: [null, Validators.required]         
    });
  }

  ngOnInit(): void {
    this.tagService.getAllTags().subscribe({
      next: (data) => this.tags = data,
      error: (err) => {
        console.error('Failed to load tags:', err);
        alert('Failed to load tags');
      }
    });
  }

  logout() {
  localStorage.removeItem('authToken');
  this.router.navigate(['/login']);
  }

  onSubmit() {
    if (this.commentForm.invalid) {
      return;
    }

    const newComment = this.commentForm.value;

    this.commentService.createComment(newComment).subscribe({ 
      next: () => {
        alert('Comment created successfully!');
        this.router.navigate(['/create-comment']); 
      },
      error: (err) => {
        console.error('Failed to create comment:', err);
        alert('Failed to create comment. Please try again.');
      }
    });
  }
}