import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { database } from 'firebase-admin';
import { AuthenticationService } from '../services/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  error = '';

  loginForm = this.fb.group({
    email : ['',Validators.required],
    password : ['',Validators.required]
  })

   constructor(private fb:FormBuilder, private authService:AuthenticationService, private router:Router) {}

   get email() { return this.loginForm.get('email')}
   get password() { return this.loginForm.get('password')}

   onSubmit():void {
    console.log(this.loginForm.value);
    this.authService.login(this.loginForm.value).subscribe({
      next : data => {
        console.log(localStorage.getItem("jwt"));
        console.log("User Logged In Status" , data)
        if(data == false){
          this.error = "Authentication Failed! Please Try Again"
          return
        }
        this.router.navigateByUrl('home')
      }, 
      error : err => {
        this.error = "Authentication Failed!Please Try Again"
      }
    })
   }
}
