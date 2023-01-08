import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { UserServiceService } from './user-service.service';
import jwt_decode from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  isLoggedIn = new BehaviorSubject<boolean>((localStorage.getItem('isLoggedIn') == "true") ?? false);
  userRole = new BehaviorSubject<String>(localStorage.getItem('userRole')??'');

  constructor(private http:HttpClient, private userService : UserServiceService) { }

  login(data:any) : Observable<boolean> {
    var subject = new Subject<boolean>();
    this.userService.checkLogin(data).subscribe({
      next : (data : any) => {
        console.log(data);  
        this.isLoggedIn.next(true);
        subject.next(true)
        let token:string = data.token.substring(7);
        let decoded:any = jwt_decode(token);
        console.log(decoded?.user_role);
        this.userRole.next(decoded?.user_role);
        localStorage.setItem("jwt",data?.token);
        localStorage.setItem("isLoggedIn", "true");
        localStorage.setItem("userRole" , decoded?.user_role)
      },
      error : err => {
        // console.log(err);
        subject.next(false);        
      }     
    })
    return subject.asObservable();
  }


  logout() {
    this.isLoggedIn.next(false);
    localStorage.clear();
  }



}
