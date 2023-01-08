import { Component } from '@angular/core';
import { CartService } from '../services/cart/cart.service';

@Component({
  selector: 'app-cart-view',
  templateUrl: './cart-view.component.html',
  styleUrls: ['./cart-view.component.css']
})
export class CartViewComponent {
   cartData:any | undefined;
   msg='';
   totalPrice:Number = 0;

   constructor(private cartService:CartService) {}

   ngOnInit(): void {
     this.getCartItems();
   }

   getCartItems() {
    this.cartService.getCartItems().subscribe({
      next: (data : any) => {
        console.log(data.products);
        this.cartData = data;
        
        this.totalPrice = this.cartData.products.reduce((value:number, item:any)=> value + item.price,0);
        
      },
      error: err=>{
        console.log(err.error);
          
      }
    })
   }

   onDelete(id:any){
    console.log(id);
    this.cartService.deleteProduct(id).subscribe({
      next: data =>{
        this.msg = "Item Deleted"
        this.cartData = undefined;
      }
    })
   }
}
