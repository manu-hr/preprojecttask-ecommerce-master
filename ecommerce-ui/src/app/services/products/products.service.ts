import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {
  productAppUrl:string = 'http://localhost:9999';
  products = new BehaviorSubject<any[]>([]);
  product = new BehaviorSubject<any>(null);

  constructor(private http:HttpClient) { }

  getAllProducts() {
    return this.http.get<any[]>(`${this.productAppUrl}/get-product`);
  }

  addProduct(data:any) {
    let httpHeaders = new HttpHeaders({
      'Authorization' : 'Bearer '+ localStorage.getItem('jwt')
    })
    return this.http.post(`${this.productAppUrl}/api/product/add`,data, {
      headers : httpHeaders
    })
  }

  editProduct(data:any) {
    let httpHeaders = new HttpHeaders({
      'Authorization' : 'Bearer '+ localStorage.getItem('jwt')
    })
    return this.http.put(`${this.productAppUrl}/api/product/edit`,data, {
      headers : httpHeaders
    })
  }

  deleteProduct(id:String) {
    let httpHeaders = new HttpHeaders({
      'Authorization' : 'Bearer '+ localStorage.getItem('jwt')
    })
    return this.http.delete(`${this.productAppUrl}/api/product/delete/${id}`,{
      headers : httpHeaders
    })
  }
}
