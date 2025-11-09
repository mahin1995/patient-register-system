
import { Component, signal } from '@angular/core';
import { Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterLink, RouterLinkActive, FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  [x: string]: any;
  protected readonly title = signal('prescription-management-service');
  sidebarOpen = signal(true);

  toggleSidebar() {
    this.sidebarOpen.update((open) => !open);
  }
  isLoginPageHave() {
    return this.router.url === '/login';
  }
  constructor(private router: Router) {}
}
