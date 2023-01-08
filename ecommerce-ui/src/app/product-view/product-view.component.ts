import { Component } from '@angular/core';
import { ProductsService } from '../services/products/products.service';
import { CartService } from '../services/cart/cart.service';
import { AuthenticationService } from '../services/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-view',
  templateUrl: './product-view.component.html',
  styleUrls: ['./product-view.component.css']
})
export class ProductViewComponent {

  itemAddedMsg:String = '';
  errorMsg : String = "";
  isAdmin : boolean = false;
  isEdit:boolean = false;

  products:any = [];
  product:any;

  constructor(private productService:ProductsService, private cartService:CartService, private authService:AuthenticationService, private router:Router) {}

  ngOnInit(): void {
    this.getProducts();
    this.productService.products.subscribe((data) => this.products = data);
    this.authService.userRole.subscribe(data=> this.isAdmin = (data == "ROLE_ADMIN"))
  } 

  addToCart(product:any)  {
    this.cartService.addProductToCart(product).subscribe({
      next : data => {
        console.log(data); 
        this.itemAddedMsg = "Item Added";
        this.errorMsg = '';
      },
      error : err =>{
        this.errorMsg = "Please Login To Add to The Cart";
        this.itemAddedMsg = '';
      }
    })
  }


  getProducts(): void {
    this.productService.getAllProducts().subscribe({
      next: (data) => {
        this.productService.products.next(data);
      },
      error: err => alert(err.message)
    })
  }

  onEdit(product:any) : void {
    this.productService.product.next(product);
    this.product = product;
    this.isEdit = true;

    this.router.navigateByUrl('/edit-product');
  }

  onDelete(id:String) : void {
    this.productService.deleteProduct(id).subscribe({
      next : data => {
        console.log(data);
        let newProducts = this.products.filter((item:any) => item.productId != id)
        this.productService.products.next(newProducts);
      }
    })
  }

}
