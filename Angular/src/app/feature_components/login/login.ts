import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { RouterModule, Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class LoginPage {
  form: FormGroup;
  loginError: string | null = null;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.form = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  onSubmit() {
    if (this.form.invalid) {
      return;
    }

    this.loginError = null;

    const data = this.form.value; // { username, password }

    this.authService.login(data).subscribe({
      next: (response) => {
        localStorage.removeItem('token');
        localStorage.setItem('token', response.token);
        // Assuming token is stored inside AuthService.login via tap()
        this.router.navigate(['/dashboard']);
      },
      error: (err) => {
        this.loginError = 'Invalid username or password';
        console.error('Login failed:', err);
      }
    });
  }
}
