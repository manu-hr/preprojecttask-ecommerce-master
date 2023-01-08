import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {
  
  authAppUrl:string = 'http://localhost:8888';
  productAppUrl:string = 'http://localhost:9999';

  constructor(private http:HttpClient) { }

  registerUser(data : any) {
    return this.http.post(`${this.productAppUrl}/register`,data);
  }

  checkLogin(data:any) {
    return this.http.post(`${this.authAppUrl}/login`,data);
  }
}
