import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'; 
import { RouterModule } from '@angular/router';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-dashboard',
  standalone:true,
  imports: [RouterModule],
  templateUrl: './dashboard.html',
  styleUrls: ['./dashboard.css'] 
})
export class Dashboard implements OnInit {

  constructor(private router: Router, private userService: UserService) {}

  logout() {
    localStorage.removeItem('authToken');
    this.router.navigate(['/login']);
  }

  username = '';

  ngOnInit(): void {
    this.userService.getCurrentUser().subscribe({
      next: (data) => {
        this.username = data.username;
      },
      error: (err) => {
        console.error('Error fetching username', err);
      }
    });
  }

}
