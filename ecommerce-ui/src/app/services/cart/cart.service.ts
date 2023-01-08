import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  productAppUrl:string = 'http://localhost:9999';


  constructor(private http:HttpClient) { }

  getCartItems() {
    let httpHeaders = new HttpHeaders({
      'Authorization' : 'Bearer '+ localStorage.getItem('jwt')
    })
    return this.http.get<any[]>(`${this.productAppUrl}/api/cart/get`,{
      headers : httpHeaders
    });
  }

  addProductToCart(data:any) {
    let httpHeaders = new HttpHeaders({
      'Authorization' : 'Bearer '+ localStorage.getItem('jwt')
    })
    return this.http.post(`${this.productAppUrl}/api/cart/add`,data, {
      headers : httpHeaders
    })
  }

  deleteProduct(id:String) {
    let httpHeaders = new HttpHeaders({
      'Authorization' : 'Bearer '+ localStorage.getItem('jwt')
    })
    return this.http.delete(`${this.productAppUrl}/api/cart/delete/${id}`,{
      headers : httpHeaders
    })
  }
}
