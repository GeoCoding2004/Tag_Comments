import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service'; 
import { RegisterRequest } from '../../models/auth.models'; 
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule], 
  templateUrl: './register.html',
  styleUrl: './register.css'
})
export class RegisterPage implements OnInit {
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.form = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      role: ['USER', Validators.required], 
    });
  }

  onSubmit() {
    if (this.form.invalid) return;

    const data: RegisterRequest = this.form.value;

    this.authService.register(data).subscribe({
      next: (response) => {
        console.log('Registration successful:', response);
        this.router.navigate(['/login']);
      },
      error: (err) => {
        console.error('Registration failed:', err);

        if (err.status === 400) {
          alert("Username is already taken");
        } 
        else {
          alert("An unexpected error occurred. Please try again later.");
        }
      },
    });
  }

  ngOnInit() {
    localStorage.removeItem('authToken');
  }
}
