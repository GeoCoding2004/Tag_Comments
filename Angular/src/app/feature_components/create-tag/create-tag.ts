import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { TagService } from '../../services/tag.service';

@Component({
  selector: 'app-create-tag',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './create-tag.html',
  styleUrls: ['./create-tag.css']
})
export class CreateTag {
  tagForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private tagService: TagService,
    private router: Router
  ) {
    this.tagForm = this.fb.group({
      name: ['', Validators.required]
    });
  }

  logout() {
    localStorage.removeItem('authToken');
    this.router.navigate(['/login']);
  }

  onSubmit() {
    if (this.tagForm.invalid) return;

    const tagData = this.tagForm.value;

    this.tagService.createTag(tagData).subscribe({
      next: () => {
        alert('Tag created successfully!');
        this.router.navigate(['/dashboard']);
      },
      error: (err) => {
        console.error('Failed to create tag:', err);
        alert('Failed to create tag. You may not have the required permissions.');
      }
    });
  }
}
