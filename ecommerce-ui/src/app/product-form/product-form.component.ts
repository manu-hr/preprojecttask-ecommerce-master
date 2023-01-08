import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ProductsService } from '../services/products/products.service';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent {
  itemAddedMsg:String = '';

  productForm = this.fb.group({
    'productName' : ['', Validators.required],
    'description' : ['', Validators.required],
    'price' : [ '' , Validators.required]
  })
  constructor(private fb:FormBuilder, private productService:ProductsService) {}

  get productName() { return this.productForm.get('productName')}
  get description() { return this.productForm.get('description')}
  get price() { return this.productForm.get('price')}

  onSubmit() {
    console.log(this.productForm.value);
    this.productService.addProduct(this.productForm.value).subscribe({
      next : data =>{
        let previousData = this.productService.products.getValue();
        previousData.push(data);
        this.productService.products.next(previousData);
        this.itemAddedMsg = "ItemAdded";
        this.productForm.reset();
      },
      error : err => {
        if(err.status == 401){
          alert("Invalid Request! Please Login as Admin")
        } else {
          alert("Invalid Request! ")

        }
        
      } 
    })

  }
}
