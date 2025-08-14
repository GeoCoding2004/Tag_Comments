import { Component, OnInit } from '@angular/core';
import { TagService } from '../../services/tag.service'; 
import { Tag } from '../../models/tag.model';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-manage-tags',
  standalone:true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule, FormsModule, HttpClientModule],
  templateUrl: './manage-tags.html',
  styleUrl: './manage-tags.css'
})
export class ManageTags implements OnInit {
  
  tags: Tag[] = [];
  constructor(private tagService: TagService, private router: Router) {}

  ngOnInit(): void {
    this.getAllTags();
  }

  getAllTags(): void {
    this.tagService.getAllTags().subscribe({
      next: (data) => {
        this.tags = data;
      },
      error: (err) => {
        console.error('Failed to fetch tags', err);
      }
    });
  }

  logout() {
    localStorage.removeItem('authToken');
    this.router.navigate(['/login']);
  }


  selectedTag: Tag | null = null;
  tagId = 0;

  getTagById(): void {
    if (!this.tagId) return;

    this.tagService.getTagById(this.tagId).subscribe({
      next: (tag) => {
        this.selectedTag = tag;
      },
      error: (err) => {
        console.error('Failed to fetch tag by ID', err);
        this.selectedTag = null;
      }
    });
  }

  updateTagId: number | null = null;
  updateTagNameField = '';
  updateError = '';
  updateMessage = '';

  startEdit(tag: Tag): void {
    this.updateTagId = tag.id!;
    this.updateTagNameField = tag.name;
    this.updateMessage = '';
    this.updateError = '';
  }

  onUpdateTag(): void {
    if (!this.updateTagId || !this.updateTagNameField.trim()) return;

    const updatedTag: Tag = { id: this.updateTagId, name: this.updateTagNameField };

    this.tagService.updateTag(this.updateTagId, updatedTag).subscribe({
      next: () => {
        this.updateMessage = 'Tag updated successfully!';
        this.updateError = '';
        this.getAllTags(); 
        this.updateTagId = null; 
      },
      error: (err) => {
        console.error(err);  
        alert('You do not have permission to update this tag.'); 
        
      }
    });
  }

  cancelEdit(): void {
    this.updateTagId = null;
  }


  deleteTagId = 0;
  deleteMessage = '';

  deleteTag(id: number): void {
    if (!id) return;

    this.tagService.deleteTag(id).subscribe({
      next: (message) => {
        this.deleteMessage = message;
        this.tags = this.tags.filter(tag => tag.id !== id);    },
      error: err => {
        console.error(err);
          
        if (err.status === 403) {
          alert('You do not have permission to delete this tag.');
        } else if (err.status === 404) {
          alert('Tag not found.');
        } else {
          alert('An unexpected error occurred while deleting the tag.');
        }
      }
    });
  }

}


