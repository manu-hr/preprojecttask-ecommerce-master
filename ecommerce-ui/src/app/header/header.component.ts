import { Component } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  isLoggedIn:boolean = false;
  constructor(private authService:AuthenticationService, private router:Router) {}
  
  ngOnInit(): void {
    this.authService.isLoggedIn.subscribe({next: data=> this.isLoggedIn = data})
  } 

  onLogOut() {
    this.authService.logout();
    this.router.navigateByUrl('');
  }
}
