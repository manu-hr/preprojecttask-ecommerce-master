import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ProductsService } from '../services/products/products.service';

@Component({
  selector: 'app-product-edit-form',
  templateUrl: './product-edit-form.component.html',
  styleUrls: ['./product-edit-form.component.css']
})
export class ProductEditFormComponent {
  itemAddedMsg:String = '';

  @Input()
  product:any;

  @Output()
  onclose:EventEmitter<boolean> = new EventEmitter();

  productForm = this.fb.group({
    'productId':'',
    'productName' : ['', Validators.required],
    'description' : ['', Validators.required],
    'price' : [ '' , Validators.required]
  })

  constructor(private fb:FormBuilder, private productService:ProductsService) {
    
  }

  ngOnInit(): void {
    this.productService.product.subscribe(data => this.productForm.setValue(data))
  } 

  get productName() { return this.productForm.get('productName')}
  get description() { return this.productForm.get('description')}
  get price() { return this.productForm.get('price')}

  onEdit() : void {
    this.productService.editProduct(this.productForm.value).subscribe({
      next :( data:any) => {
        this.itemAddedMsg = "Item Edited Successfully"
        this.productForm.setValue(data);
        this.onclose.emit(true);
      }
    })
  }

}
