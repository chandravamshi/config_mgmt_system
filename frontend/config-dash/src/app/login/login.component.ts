import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule, // Add Material Button Module
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(private router: Router) {}

  login(): void {
    // Dummy credentials for demonstration
    const dummyUsername = 'user';
    const dummyPassword = 'password';

    if (this.username === dummyUsername && this.password === dummyPassword) {
      // Simulate receiving a JWT token from the server
      const token = 'dummy.jwt.token'; // Replace with actual JWT from server
      localStorage.setItem('token', token); // Store JWT in localStorage
      this.router.navigate(['/dashboard']); // Navigate to dashboard
    } else {
      alert('Invalid username or password');
    }
  }
}
